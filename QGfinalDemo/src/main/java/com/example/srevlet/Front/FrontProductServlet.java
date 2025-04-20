package com.example.srevlet.Front;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.DealStatusEnum;
import com.example.common.enums.LogsTypeEnum;
import com.example.common.enums.ProductStatusEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Comments;
import com.example.entity.Deal;
import com.example.entity.Product;
import com.example.service.CommentsService;
import com.example.service.DealService;
import com.example.service.LogService;
import com.example.service.ProductService;
import com.example.srevlet.BaseServlet;
import com.example.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Front/Product")
public class FrontProductServlet extends BaseServlet {

    public void SaveBuyForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username=request.getParameter("username");
        String payMethod=request.getParameter("payMethod");
        Integer productNum=Integer.valueOf(request.getParameter("productNum"));
        Integer productId=Integer.valueOf(request.getParameter("productId"));
        Integer userId=Integer.valueOf(request.getParameter("userId"));
        Deal deal=new Deal();
        deal.setUserId(userId);
        deal.setProductId(productId);
        deal.setProductNum(productNum);
        deal.setPayMethod(payMethod);
        deal.setDealStatus(DealStatusEnum.BUY.getValue());
        deal.setDealTime(TimeUtil.getTime());
        Result result =null;
        DealService dealService=new DealService();
        if(dealService.add(deal)>0){
            result=Result.success();
        }else{
            result=Result.error(ResultCodeEnum.PRODUCT_BUY_ERROR);
        }

        if("200".equals(result.getCode())){
            LogService logService=new LogService();
            logService.recordLog(username, LogsTypeEnum.DEAL.getValue(),"购买商品成功",request);
        }

        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

    public void loadProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String selectWord = request.getParameter("selectWord");
        String publishStatus = ProductStatusEnum.PUBLISHED.getValue();
        Result result =null;
        ProductService productService = new ProductService();
        List<Product> products =productService.selectList(selectWord,publishStatus);
        if(products!=null){
            result=Result.success(products);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void FocusOnProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        DealService dealService = new DealService();
        Result result=null;
        List<Deal> deals =dealService.selectByProduct(productId);
        if(deals!=null&&!deals.isEmpty())
            result=Result.success(deals);
        else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void FocusOnDeal (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer dealId = Integer.valueOf(request.getParameter("dealId"));
        CommentsService commentsService = new CommentsService();
        Result result=null;
        Comments comment =commentsService.selectByDeal(dealId);
        if(comment!=null)
            result=Result.success(comment);
        else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

}
