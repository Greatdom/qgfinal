package com.example.service;

import com.example.entity.*;
import com.example.mapper.*;

import java.util.List;

public class CommentsService {
    CommentsMapper commentsMapper = new CommentsMapper();
    public int add(Comments comments) {
        Comments dbComment =commentsMapper.selectByDeal(comments);
        if(dbComment != null){
            return 0;
        }
        int count = commentsMapper.insert(comments);
        if (count > 0) {
            //记录系统信息
            Systeminfo systeminfo = Systeminfo.getInstance();
            systeminfo.setCommentNum(systeminfo.getCommentNum()+1);
            SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
            systeminfoMapper.update(systeminfo);
            //使评分更新
            ProductMapper productMapper = new ProductMapper();
            DealMapper dealMapper = new DealMapper();
            UserMapper userMapper = new UserMapper();
            Deal dbDeal = dealMapper.selectById(comments.getDealId());
            Product dbProduct = new Product();
            dbProduct.setId(dbDeal.getProductId());
            dbProduct = productMapper.selectById(dbProduct);
            Account dbaccount = new Account();
            dbaccount.setId(dbDeal.getUserId());
            dbaccount = userMapper.selectSingle(dbaccount);
            count = productMapper.whileCommenting(dbProduct,comments,dbaccount);
        }
        return count;
    }
    public Comments selectByDeal(int id){
        Comments comment = new Comments();
        comment.setDealId(id);
        return commentsMapper.selectByDeal(comment);
    }
    public List<Comments> selectAll(){
        return commentsMapper.selectAll();
    }
    public Comments selectById(int id){
        return commentsMapper.selectById(id);
    }
    public int deleteById(int id){
        Comments dbcomments = commentsMapper.selectById(id);
        if (dbcomments != null) {
            Systeminfo systeminfo = Systeminfo.getInstance();
            systeminfo.setCommentNum(systeminfo.getCommentNum()-1);
            SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
            systeminfoMapper.update(systeminfo);

            ProductMapper productMapper = new ProductMapper();
            DealMapper dealMapper = new DealMapper();
            UserMapper userMapper = new UserMapper();

            Deal dbDeal = dealMapper.selectById(dbcomments.getDealId());
            Product dbProduct = new Product();
            dbProduct.setId(dbDeal.getProductId());
            dbProduct = productMapper.selectById(dbProduct);
            Account dbaccount = new Account();
            dbaccount.setId(dbDeal.getUserId());
            dbaccount = userMapper.selectSingle(dbaccount);
            int count = productMapper.whileCancelCommenting(dbProduct,dbcomments,dbaccount);
            if(count>0){
                return commentsMapper.deleteById(id);
            }else{
                return 0;
            }
        }
        return 0;
    }
}
