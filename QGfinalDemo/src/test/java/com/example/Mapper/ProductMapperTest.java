package com.example.Mapper;

import com.example.entity.Account;
import com.example.entity.Product;
import com.example.mapper.ProductMapper;
import com.example.util.TimeUtil;
import org.junit.Test;

import java.util.List;

public class ProductMapperTest {
    @Test
    public void insert() {
        ProductMapper productMapper = new ProductMapper();
        Product product = new Product();
        product.setName("好人卡");
        product.setDescription("这是好人卡，为小丑准备的");
        product.setPrice(55.5);
        product.setStock(12);
        product.setCategory("文具");
        product.setPublishTime(TimeUtil.getTime());
        product.setPublishStatus("已发布");
        product.setUserId(1);
        int count = productMapper.insert(product);
        System.out.println(count);
    }
    @Test
    public void selectList() {
        ProductMapper productMapper = new ProductMapper();

        List<Product> productList = productMapper.selectList("好人","已发布");
        System.out.println(productList);
    }
    @Test
    public void selectById() {
        ProductMapper productMapper = new ProductMapper();
        Product product=new Product();
        product.setId(1);
        product= productMapper.selectById(product);
        System.out.println(product);
    }
    @Test
    public void selectAll(){
        ProductMapper productMapper = new ProductMapper();
        List<Product> productList = productMapper.selectAll();
        System.out.println(productList);
    }
    @Test
    public void update() {
        ProductMapper productMapper = new ProductMapper();
        Product product=new Product();
        product.setId(1);
        product= productMapper.selectById(product);
        product.setDescription("好人要当小丑");
        int count = productMapper.update(product);
        System.out.println(product);
    }
    @Test
    public void selectByUser() {
        ProductMapper productMapper = new ProductMapper();
        Product product=new Product();
        product.setUserId(1);
        List<Product> productList = productMapper.selectByUser(product);
        System.out.println(productList);
    }

}
