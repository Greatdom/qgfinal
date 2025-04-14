package com.example.mapper;


import com.example.entity.Product;
import com.example.util.CRUDUtils;

import java.util.List;

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
    public List<Product> selectList(String searchWord,String publishStatus) {
        if(searchWord==null|| searchWord.isEmpty()){
            return null;
        }
        if(publishStatus==null|| publishStatus.isEmpty()){
            return null;
        }
        searchWord = "%" + searchWord + "%";
        String selectSql="select * from product"
                + " where (name like ?"
                + " or description like ?"
                + " or category like ?"
                + " or user_id like ?)"
                + " and publish_status = ?";
        return CRUDUtils.queryForList(Product.class,selectSql,searchWord,searchWord,searchWord,searchWord,publishStatus);

    }
    public Product selectById(Product product) {
        Integer id = product.getId();
        String selectSql="select * from product where id = ?";
        return CRUDUtils.queryForObject(Product.class,selectSql,id);
    }
    public List<Product> selectAll() {
        String selectSql="select * from product";
        return CRUDUtils.queryForList(Product.class,selectSql);
    }
    public int update(Product product) {
        if(product==null){
            return 0;
        }
        Integer id = product.getId();
        String name = product.getName();
        String description = product.getDescription();
        Double price = product.getPrice();
        Integer stock = product.getStock();
        String category = product.getCategory();
        String avatar = product.getAvatar();
        String updateSql = "UPDATE product SET name=?,description=?,price=?,stock=?,category=?,avatar=? WHERE id = ?";
        return CRUDUtils.update(updateSql,name,description,price,stock,category,avatar,id);
    }
    public int ChangePublishStatus(Product product) {
        if(product==null){
            return 0;
        }
        Integer id = product.getId();
        String publishStatus = product.getPublishStatus();
        String updateSql = "UPDATE product SET publish_status=? WHERE id = ?";
        return CRUDUtils.update(updateSql,publishStatus,id);
    }
    public int decreaseStock(Product product) {
        if(product==null){
            return 0;
        }
        Integer id = product.getId();
        Integer stock = product.getStock();
        String updateSql = "UPDATE product SET stock=? WHERE id = ?";
        return CRUDUtils.update(updateSql,stock,id);
    }
}
