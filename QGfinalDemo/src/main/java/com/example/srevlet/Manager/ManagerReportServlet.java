package com.example.srevlet.Manager;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.entity.Product;
import com.example.entity.Report;
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

@WebServlet("/Manager/Report")
public class ManagerReportServlet extends BaseServlet {
    public void FocusOnRePort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pointerId=Integer.valueOf(request.getParameter("pointerId"));
        String reportType=request.getParameter("reportType");
        Result result=null;
        if("用户".equals(reportType)){
            UserService userService=new UserService();
            Account account = new Account();
            account.setId(pointerId);
            account = userService.selectSingle(account);
            if(account!=null){
                result = Result.success(account);
            }else{
                result = Result.error();
            }
        }else{
            ProductService productService=new ProductService();
            Product product = new Product();
            product.setId(pointerId);
            product = productService.selectById(product);
            if(product!=null){
                result = Result.success(product);
            }else{
                result = Result.error();
            }
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void LoadReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportService reportService = new ReportService();
        List<Report> reports = reportService.selectAll();
        Result result=null;
        if(reports!=null){
            result=Result.success(reports);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
}
