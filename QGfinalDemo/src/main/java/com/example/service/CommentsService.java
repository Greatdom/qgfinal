package com.example.service;

import com.example.entity.Comments;
import com.example.mapper.CommentsMapper;

import java.util.List;

public class CommentsService {
    CommentsMapper commentsMapper = new CommentsMapper();
    public int add(Comments comments) {
        return commentsMapper.insert(comments);
    }
    public List<Comments> selectByDeal(int id){
        return commentsMapper.selectByDeal(id);
    }
    public List<Comments> selectAll(){
        return commentsMapper.selectAll();
    }
    public Comments selectById(int id){
        return commentsMapper.selectById(id);
    }
    public int updateById(int id){
        return commentsMapper.deleteById(id);
    }
}
