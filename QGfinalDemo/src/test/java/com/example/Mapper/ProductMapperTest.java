package com.example.Mapper;

import com.example.entity.Account;
import com.example.entity.Product;
import com.example.mapper.ProductMapper;
import org.junit.Test;

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
        product.setPublishTime("尚未制作时间生成模块");
        product.setPublishStatus("已发布");
        product.setUserId(1);
        int count = productMapper.insert(product);
        System.out.println(count);
    }
}
