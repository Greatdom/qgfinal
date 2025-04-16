package com.example.service;

import com.example.entity.Account;
import com.example.entity.Product;
import com.example.entity.Systeminfo;
import com.example.mapper.ProductMapper;
import com.example.mapper.SysteminfoMapper;
import com.example.mapper.UserMapper;

import java.util.List;

public class ProductService {
    ProductMapper productMapper = new ProductMapper();

    public int add(Product product) {

        int count = productMapper.insert(product);
        if (count > 0) {
            Systeminfo systeminfo = Systeminfo.getInstance();
            systeminfo.setProductNum(systeminfo.getProductNum()+1);
            SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
            systeminfoMapper.update(systeminfo);
        }
        return count;
    }
    public List<Product> selectAll() {
        return productMapper.selectAll();
    }
    public Product selectById(Product product) {
        return productMapper.selectById(product);
    }
    public List<Product> selectList(String searchWord,String publishStatus){
        return productMapper.selectList(searchWord,publishStatus);
    }
    public int update(Product product) {
        return productMapper.update(product);
    }

}
