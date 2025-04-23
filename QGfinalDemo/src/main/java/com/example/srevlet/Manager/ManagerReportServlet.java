package com.example.srevlet.Manager;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.AccountStatusEnum;
import com.example.common.enums.ProductStatusEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.entity.Product;
import com.example.entity.Report;
import com.example.service.ProductService;
import com.example.service.ReportService;
import com.example.service.SentenceService;
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

    public void DealWithReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportResult =request.getParameter("result");
        Integer id = Integer.valueOf(request.getParameter("id"));
        Result result = null;
        ReportService reportService = new ReportService();
        SentenceService sentenceService = new SentenceService();

        Report report = reportService.selectById(id);
        if(report!=null){
            report.setResult(reportResult);
            if(reportService.changeResult(report)>0){
                if("反对".equals(report.getResult())){
                    result=Result.success();
                    String content="您于["+report.getReportTime()+"]提交的举报不被批准";
                    sentenceService.addSystemToUser(report.getUserId(),content);
                }else{
                    if("用户".equals(report.getReportType())){
                        UserService userService = new UserService();
                        Account account = new Account();
                        account.setId(report.getPointerId());
                        account =userService.selectSingle(account);
                        if(account!=null){
                            account.setStatus(AccountStatusEnum.BAN.getValue());
                            userService.ChangeStatus(account);
                            result=Result.success();

                        }
                    }else{
                        ProductService productService = new ProductService();
                        Product product = new Product();
                        product.setId(report.getPointerId());
                        product=productService.selectById(product);
                        if(product!=null){
                            product.setPublishStatus(ProductStatusEnum.BAN.getValue());
                            productService.ChangePublishStatus(product);
                            result=Result.success();
                        }
                    }
                    String content="您于["+report.getReportTime()+"]提交的举报已批准";
                    sentenceService.addSystemToUser(report.getUserId(),content);
                }
            }else result = Result.error();

        }else{
            result = Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

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
