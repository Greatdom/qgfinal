package com.example.srevlet.Manager;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.entity.Deal;
import com.example.entity.Product;
import com.example.entity.Report;
import com.example.service.DealService;
import com.example.service.ProductService;
import com.example.service.ReportService;
import com.example.service.UserService;
import com.example.srevlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Manager/Product")
public class ManagerProductServlet extends BaseServlet {

    public void ChangeProductStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.valueOf(request.getParameter("id"));
        String status=request.getParameter("status");
        ProductService productService=new ProductService();
        Product product =new Product();
        product.setId(id);
        product = productService.selectById(product);
        Result result=null;
        if(product!=null){
            product.setPublishStatus(status);
            if(productService.ChangePublishStatus(product)>0){
                result=Result.success();
            }else{
                result=Result.error();
            }
        }else{
            result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

    public void FocusOnProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        DealService dealService = new DealService();
        List<Deal> deals=dealService.selectByProduct(productId);
        Result result = null;
        if(deals!=null){
            result=Result.success(deals);
        }else{
            result = Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

    public void LoadProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();
        Result result = null;
        List<Product> products =productService.selectAll();
        if(products!=null){
            result=Result.success(products);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
}
