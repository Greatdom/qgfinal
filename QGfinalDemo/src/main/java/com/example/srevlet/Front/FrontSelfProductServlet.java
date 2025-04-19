package com.example.srevlet.Front;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Comments;
import com.example.entity.Deal;
import com.example.entity.Product;
import com.example.service.CommentsService;
import com.example.service.DealService;
import com.example.service.ProductService;
import com.example.srevlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Front/SelfProduct")
public class FrontSelfProductServlet extends BaseServlet {
    public void FocusOnDeal (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{



        Integer dealId = Integer.valueOf(request.getParameter("dealId"));
        CommentsService commentsService = new CommentsService();
        Result result=null;
        System.out.println("1"+dealId);
        Comments comment =commentsService.selectByDeal(dealId);
        System.out.println("2"+comment);
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
