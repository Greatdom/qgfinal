package com.example.Service;

import com.example.entity.Comments;
import com.example.service.CommentsService;
import com.example.util.TimeUtil;
import org.junit.Test;

public class CommentsServiceTest {
    @Test
    public void add() {
        Comments comments = new Comments();
        comments.setScore(2);
        comments.setCommentTime(TimeUtil.getTime());
        comments.setContent("咋了");
        comments.setDealId(1);
        CommentsService commentsService = new CommentsService();
        int count = commentsService.add(comments);
        System.out.println(count);
    }
    @Test
    public void delete(){
        CommentsService commentsService = new CommentsService();
        int count = commentsService.deleteById(2);
        System.out.println(count);
    }
}
