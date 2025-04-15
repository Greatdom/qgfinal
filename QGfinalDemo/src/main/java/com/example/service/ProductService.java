package com.example.service;

import com.example.entity.Account;
import com.example.entity.Product;
import com.example.mapper.ProductMapper;
import com.example.mapper.UserMapper;

import java.util.List;

public class ProductService {
    ProductMapper productMapper = new ProductMapper();

    public int add(Product product) {
        return productMapper.insert(product);
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
