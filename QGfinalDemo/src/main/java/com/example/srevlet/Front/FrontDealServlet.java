package com.example.srevlet.Front;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.DealStatusEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.*;
import com.example.service.*;
import com.example.srevlet.BaseServlet;
import com.example.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Front/Deal")
public class FrontDealServlet extends BaseServlet {

    public void SaveAddReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportType=request.getParameter("reportType");
        String content=request.getParameter("content");
        Integer userId=Integer.valueOf(request.getParameter("userId"));
        Integer pointerId=Integer.valueOf(request.getParameter("pointerId"));
        Result result =null;
        ReportService reportService=new ReportService();
        SentenceService sentenceService=new SentenceService();


        if(reportService.selectByTypeAndPointerId(reportType,pointerId)!=null){
            Report report=new Report();
            report.setReportType(reportType);
            report.setContent(content);
            report.setUserId(userId);
            report.setPointerId(pointerId);
            report.setResult("待处理");
            report.setReportTime(TimeUtil.getTime());
            if(reportService.add(report)>0&&sentenceService.addUserToSystem(userId,content)>0){
                result=Result.success();
            }else{
                result=Result.error();
            }
        }else{
            result=Result.error(ResultCodeEnum.REPORT_ERROR);
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

    public void SaveDeleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.valueOf(request.getParameter("id"));
        Result result =null;
        CommentsService commentsService=new CommentsService();
        if(commentsService.deleteById(id)>0){
            result=Result.success();
        }else{
            result=Result.error();
        }

        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

    public void SaveAddComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer score=Integer.valueOf(request.getParameter("score"));
        Integer dealId=Integer.valueOf(request.getParameter("dealId"));
        String content=request.getParameter("content");
        Comments comment=new Comments();
        CommentsService commentsService=new CommentsService();
        comment.setScore(score);
        comment.setDealId(dealId);
        comment.setContent(content);
        comment.setCommentTime(TimeUtil.getTime());
        Result result = null;
        if(commentsService.add(comment)>0){
            result = Result.success();
        }else{
            result = Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

    public void loadDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Result result =null;
        DealService dealService = new DealService();
        List<Deal> deals=dealService.selectByUser(userId);
        if(deals!=null){
            result=Result.success(deals);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void FocusOnDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer dealId = Integer.valueOf(request.getParameter("dealId"));

        CommentsService commentsService = new CommentsService();
        Comments comment = commentsService.selectByDeal(dealId);
        Result result =null;
        if(comment!=null){
            result=Result.success(comment);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void FocusOnProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        Result result =null;
        ProductService productService = new ProductService();
        Product product=new Product();
        product.setId(productId);
        product = productService.selectById(product);
        if(product!=null){
            result=Result.success(product);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

}
