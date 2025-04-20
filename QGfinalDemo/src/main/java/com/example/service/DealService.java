package com.example.service;

import com.example.entity.Account;
import com.example.entity.Deal;
import com.example.entity.Product;
import com.example.entity.Systeminfo;
import com.example.mapper.DealMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.SysteminfoMapper;
import com.example.mapper.UserMapper;

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
                dbaccount.setId(deal.getUserId());
                dbaccount = userMapper.selectSingle(dbaccount);
                count = productMapper.whileDealing(dbProduct,dbaccount);
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
}
