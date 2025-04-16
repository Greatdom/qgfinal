package com.example.Mapper;

import com.example.entity.Comments;
import com.example.mapper.CommentsMapper;
import com.example.util.TimeUtil;
import org.junit.Test;

import java.util.List;

public class CommentsMapperTest {
    @Test
    public void insert() {
        CommentsMapper commentsMapper = new CommentsMapper();
        Comments comments = new Comments();
        comments.setScore(1);
        comments.setCommentTime(TimeUtil.getTime());
        comments.setContent("HHH");
        comments.setDealId(1);
        int count = commentsMapper.insert(comments);
        System.out.println(count);

    }
    @Test
    public void selectByDeal() {
        CommentsMapper commentsMapper = new CommentsMapper();
        List<Comments> comments = commentsMapper.selectByDeal(1);
        System.out.println(comments);
    }
    @Test
    public void deleteById(){
        CommentsMapper commentsMapper = new CommentsMapper();
        int count = commentsMapper.deleteById(2);
        System.out.println(count);
    }
}
