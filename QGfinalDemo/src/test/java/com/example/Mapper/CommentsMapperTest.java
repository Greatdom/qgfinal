package com.example.Mapper;

import com.example.entity.Comments;
import com.example.mapper.CommentsMapper;
import org.junit.Test;

import java.util.List;

public class CommentsMapperTest {
    @Test
    public void insert() {
        CommentsMapper commentsMapper = new CommentsMapper();
        Comments comments = new Comments();
        comments.setScore(1);
        comments.setCommentTime("未制作该模块");
        comments.setContent("你才是好人呢你全家都是好人！");
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
}
