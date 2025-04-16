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
        int count = dealMapper.insert(deal);
        if (count > 0) {
            Systeminfo systeminfo = Systeminfo.getInstance();
            systeminfo.setDealNum(systeminfo.getDealNum()+1);
            SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
            systeminfoMapper.update(systeminfo);

            ProductMapper productMapper = new ProductMapper();
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
