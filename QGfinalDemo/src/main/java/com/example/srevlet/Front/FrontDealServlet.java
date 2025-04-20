package com.example.srevlet.Front;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.DealStatusEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Deal;
import com.example.service.DealService;
import com.example.srevlet.BaseServlet;
import com.example.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Front/Deal")
public class FrontDealServlet extends BaseServlet {


    public void loadDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String payMethod=request.getParameter("payMethod");
//        Integer productNum=Integer.valueOf(request.getParameter("productNum"));
//        Integer productId=Integer.valueOf(request.getParameter("productId"));
//        Integer userId=Integer.valueOf(request.getParameter("userId"));
//        Deal deal=new Deal();
//        deal.setUserId(userId);
//        deal.setProductId(productId);
//        deal.setProductNum(productNum);
//        deal.setPayMethod(payMethod);
//        deal.setDealStatus(DealStatusEnum.BUY.getValue());
//        deal.setDealTime(TimeUtil.getTime());
//        Result result =null;
//        DealService dealService=new DealService();
//        if(dealService.add(deal)>0){
//            result=Result.success();
//        }else{
//            result=Result.error(ResultCodeEnum.PRODUCT_BUY_ERROR);
//        }
//        String jsonStr= JSON.toJSONString(result);
//        response.getWriter().write(jsonStr);
    }


}
