package com.example.service;

import com.example.common.enums.DealStatusEnum;
import com.example.entity.*;
import com.example.mapper.DealMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.SysteminfoMapper;
import com.example.mapper.UserMapper;
import com.example.util.TimeUtil;

import java.util.List;

public class DealService {
    DealMapper dealMapper = new DealMapper();
    public int add(Deal deal) {
        Systeminfo systeminfo = Systeminfo.getInstance();
        synchronized (systeminfo) {
            ProductMapper productMapper = new ProductMapper();
            Product product = new Product();
            product.setId(deal.getProductId());
            product = productMapper.selectById(product);
            if(product!=null&&product.getStock()>=deal.getProductNum())
                product.setStock(product.getStock()-deal.getProductNum());
            else return 0;
            int count = 0;
            if(productMapper.update(product)>0)
                count = dealMapper.insert(deal);
            else return count;
            if (count > 0) {
                systeminfo.setDealNum(systeminfo.getDealNum()+1);
                systeminfo.setTotalMoney(systeminfo.getTotalMoney()+(deal.getProductNum()*product.getPrice())/10000.0);
                SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
                systeminfoMapper.update(systeminfo);
                UserMapper userMapper = new UserMapper();
                Product dbProduct = new Product();
                dbProduct.setId(deal.getProductId());
                dbProduct = productMapper.selectById(dbProduct);
                Account dbaccount = new Account();
                dbaccount.setId(dbProduct.getUserId());
                dbaccount = userMapper.selectSingle(dbaccount);
                count = productMapper.whileDealing(dbProduct,dbaccount);
                if(count>0){
                    SentenceService sentenceService = new SentenceService();
                    String content="谢谢惠顾于["+deal.getDealTime()+"]购买商品["+product.getName()+"]";
                    count = sentenceService.addSystemToUser(deal.getUserId(),content);
                }
            }
            return count;
        }

    }
    public List<Deal> selectByUser(int id){
        return dealMapper.selectByUser(id);
    }
    public List<Deal> selectByProduct(int id){
        return dealMapper.selectByProduct(id);
    }
    public List<Deal> selectAll(){
        return dealMapper.selectAll();
    }
    public Deal selectById(int id){
        return dealMapper.selectById(id);
    }
    public int ChangeDealStatusToSend(Deal deal){
        ProductMapper productMapper = new ProductMapper();
        Product product = new Product();
        product.setId(deal.getProductId());
        product = productMapper.selectById(product);

        SentenceService sentenceService = new SentenceService();
        String content = null;


        if(DealStatusEnum.PACK.getValue().equals(deal.getDealStatus())){
            content = "您的商品["+product.getName()+"]在["+TimeUtil.getTime() +"]开始发货";
            sentenceService.addSystemToUser(deal.getUserId(),content);
        }else if(DealStatusEnum.SEND.getValue().equals(deal.getDealStatus())){
            content = "您的商品["+product.getName()+"]在["+TimeUtil.getTime() +"]已经到达,请接收~";
            sentenceService.addSystemToUser(deal.getUserId(),content);
        }else if(DealStatusEnum.RECEIVE.getValue().equals(deal.getDealStatus())){
            UserMapper userMapper = new UserMapper();
            Account dbaccount = new Account();
            dbaccount.setId(deal.getUserId());
            dbaccount = userMapper.selectSingle(dbaccount);
            content="您的商品["+product.getName()+"]"+"在["+TimeUtil.getTime()+"]时被用户["+dbaccount.getName()+"]接收了";
            sentenceService.addSystemToUser(product.getUserId(),content);
        }



        return dealMapper.ChangeDealStatus(deal);

    }
    public int ChangeDealStatusToCancel(Deal deal){

        ProductMapper productMapper = new ProductMapper();
        Product product = new Product();
        product.setId(deal.getProductId());
        product = productMapper.selectById(product);
        product.setStock(product.getStock()+deal.getProductNum());
        productMapper.update(product);

        Systeminfo systeminfo = Systeminfo.getInstance();
        systeminfo.setTotalMoney(systeminfo.getTotalMoney()-(deal.getProductNum()*product.getPrice())/10000.0);
        SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
        systeminfoMapper.update(systeminfo);

        CommentsService commentsService = new CommentsService();
        Comments comment = commentsService.selectByDeal(deal.getId());
        commentsService.deleteById(comment.getId());

        SentenceService sentenceService = new SentenceService();
        String content1="您于["+ TimeUtil.getTime() +"]成功退订商品["+product.getName()+"]";
        sentenceService.addSystemToUser(deal.getUserId(),content1);
        UserMapper userMapper = new UserMapper();
        Account dbaccount = new Account();
        dbaccount.setId(deal.getUserId());
        dbaccount = userMapper.selectSingle(dbaccount);
        String content2="很遗憾您的商品["+product.getName()+"]"+"在["+TimeUtil.getTime()+"]时被用户["+dbaccount.getName()+"]退订";
        sentenceService.addSystemToUser(product.getUserId(),content2);

        return dealMapper.ChangeDealStatus(deal);
    }
}
