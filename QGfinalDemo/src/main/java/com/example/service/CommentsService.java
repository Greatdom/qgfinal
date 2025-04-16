package com.example.service;

import com.example.entity.Comments;
import com.example.entity.Systeminfo;
import com.example.mapper.CommentsMapper;
import com.example.mapper.SysteminfoMapper;

import java.util.List;

public class CommentsService {
    CommentsMapper commentsMapper = new CommentsMapper();
    public int add(Comments comments) {
        int count = commentsMapper.insert(comments);
        if (count > 0) {
            Systeminfo systeminfo = Systeminfo.getInstance();
            systeminfo.setCommentNum(systeminfo.getCommentNum()+1);
            SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
            systeminfoMapper.update(systeminfo);
        }
        return count;
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
