package com.example.mapper;

import com.example.entity.Comments;
import com.example.util.CRUDUtils;

import java.util.List;

public class CommentsMapper {
    public int insert(Comments comments) {
        Integer score = comments.getScore();
        String commentTime = comments.getCommentTime();
        String content = comments.getContent();
        Integer dealId = comments.getDealId();
        String insertSql="insert into comments(score,comment_time,content,deal_id) values(?,?,?,?)";
        return CRUDUtils.insert(insertSql, score, commentTime, content, dealId);
    }
    public List<Comments> selectByDeal(Integer id) {
        String selectSql="select * from comments where deal_id=?";
        return CRUDUtils.queryForList(Comments.class,selectSql,id);
    }
    public List<Comments> selectAll(){
        String selectSql="select * from comments";
        return CRUDUtils.queryForList(Comments.class,selectSql);
    }
    public Comments selectById(Integer id) {
        String selectSql="select * from comments where id=?";
        return CRUDUtils.queryForObject(Comments.class,selectSql,id);
    }
    public Comments selectByDeal(Comments comments) {
        if(comments==null||comments.getDealId()==null){
            return null;
        }
        String selectSql="select * from comments where deal_id=?";
        return CRUDUtils.queryForObject(Comments.class,selectSql,comments.getDealId());
    }
    public int deleteById(Integer id) {
        String deleteSql="delete from comments where id=?";
        return CRUDUtils.delete(deleteSql,id);
    }
}
