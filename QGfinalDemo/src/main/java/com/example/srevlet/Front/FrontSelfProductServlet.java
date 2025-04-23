package com.example.srevlet.Front;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.DealStatusEnum;
import com.example.common.enums.LogsTypeEnum;
import com.example.common.enums.ProductStatusEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.entity.Comments;
import com.example.entity.Deal;
import com.example.entity.Product;
import com.example.service.*;
import com.example.srevlet.BaseServlet;
import com.example.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Front/SelfProduct")
public class FrontSelfProductServlet extends BaseServlet {

    public void UpdateDealStatus (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.valueOf(request.getParameter("id"));
        Result result=null;
        DealService dealService = new DealService();
        Deal deal=dealService.selectById(id);
        if(deal!=null){
            String status=deal.getDealStatus();
            if(DealStatusEnum.BUY.getValue().equals(status)){
                deal.setDealStatus(DealStatusEnum.PACK.getValue());
                if(dealService.ChangeDealStatusToSend(deal)>0){
                    result=Result.success();
                }else result=Result.error();
            }else if(DealStatusEnum.PACK.getValue().equals(status)){
                deal.setDealStatus(DealStatusEnum.SEND.getValue());
                if(dealService.ChangeDealStatusToSend(deal)>0){
                    result=Result.success();
                }else result=Result.error();
            }else{
                result = Result.error();
            }
        }else{
            result = Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }


    public void SaveAddProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price=Double.parseDouble(request.getParameter("price"));
        Integer stock=Integer.parseInt(request.getParameter("stock"));
        String category=request.getParameter("category");
        String isPublish=request.getParameter("isPublish");
        String publishTime=request.getParameter("publishTime");
        Integer userId=Integer.parseInt(request.getParameter("userId"));
        String avatar=request.getParameter("avatar");
        Product product=new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);
        if("NO".equals(isPublish)){
            product.setPublishTime(TimeUtil.getTime());
            product.setPublishStatus(ProductStatusEnum.PUBLISHED.getValue());
        }else if("YES".equals(isPublish)){
            product.setPublishTime(publishTime);
            product.setPublishStatus(ProductStatusEnum.NOT_PUBLISHED.getValue());
        }
        product.setUserId(userId);
        product.setAvatar(avatar);
        Result result=null;
        ProductService productService=new ProductService();
        if(productService.add(product)>0){
            result=Result.success();
        }else{
            result=Result.error();
        }

        if("200".equals(result.getCode())){
            LogService logService=new LogService();
            logService.recordLog(username, LogsTypeEnum.PUBLISH_PRODUCT.getValue(),"发布商品成功",request);
        }

        String jsonStr=JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

    public void SaveUpdateProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        Integer id=Integer.parseInt(request.getParameter("id"));
        ProductService productService=new ProductService();
        Product product = new Product();
        product.setId(id);
        product=productService.selectById(product);
        String description=request.getParameter("description");
//        Double price=Double.parseDouble(request.getParameter("price"));
        Integer stock=Integer.parseInt(request.getParameter("stock"));
        String category=request.getParameter("category");
        String avatar=request.getParameter("avatar");
        product.setDescription(description);
        product.setStock(stock);
        product.setCategory(category);
        product.setAvatar(avatar);
        Result result = null;
        if(productService.update(product)>0){
            result=Result.success();
        }else{
            result=Result.error();
        }
        if("200".equals(result.getCode())){
            LogService logService=new LogService();
            logService.recordLog(username, LogsTypeEnum.UPDATE_PRODUCT.getValue(),"更新商品成功",request);
        }

        String jsonStr=JSON.toJSONString(result);
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

    public void loadProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        ProductService productService = new ProductService();
        Product product = new Product();
        product.setUserId(userId);
        Result result=null;
        List<Product> products=productService.selectByUser(product);
        if(products!=null&&!products.isEmpty())
            result=Result.success(products);
        else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

}
