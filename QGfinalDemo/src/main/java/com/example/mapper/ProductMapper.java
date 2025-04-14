package com.example.mapper;

import com.example.entity.Product;
import com.example.util.CRUDUtils;

public class ProductMapper {
    public int insert(Product product) {
        String name = product.getName();
        String description = product.getDescription();
        Double price = product.getPrice();
        Integer stock = product.getStock();
        String category = product.getCategory();
        String avatar = product.getAvatar();
        String publishTime = product.getPublishTime();
        String publishStatus = product.getPublishStatus();
        Integer userId = product.getUserId();
        String insertSql=
                "INSERT INTO product (name,description,price,stock,category,avatar,publish_time,publish_status,user_id)" +
                        " VALUES (?, ? ,?,?,?,?,?,?,?)";
        return CRUDUtils.insert(insertSql, name, description, price, stock, category, avatar, publishTime, publishStatus, userId);
    }
}
