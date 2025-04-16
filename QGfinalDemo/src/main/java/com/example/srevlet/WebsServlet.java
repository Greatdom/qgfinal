package com.example.srevlet;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.service.AdminService;
import com.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/WebsServlet")
public class WebsServlet extends BaseServlet {

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String role=request.getParameter("role");
        String remember=request.getParameter("remember");
        Account account=new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        account.setPhone(phone);
        account.setRole(role);
        Result result = null;
        if("ADMIN".equals(role)){
            AdminService adminService=new AdminService();
            account=adminService.login(account);
            if(account!=null)result=Result.success(account);
            else result=Result.error();
        }else if("USER".equals(role)){
            UserService userService=new UserService();
            account=userService.login(account);
            if(account!=null)result=Result.success(account);
            else result=Result.error();
        }else result=Result.error();
        //如果result成功而且remember就加Cookie
        //登录成功后将用户加进session作为登录凭证
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("logout");
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String role=request.getParameter("role");
        String checkcode=request.getParameter("checkcode");
        Account account=new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);

//        HttpSession session=request.getSession();
//        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
//        Result result =null;
//        if(!(checkCodeGen.equals(checkcode))){
//            result=Result.error();
//            System.out.println("验证码错误");
//        }else{
//            WebController webController=new WebController();
//            result=webController.register(username,password,role);
//        }
        Result result=null;
        if("ADMIN".equals(role)){
            AdminService adminService=new AdminService();
            if(adminService.register(account)!=0)result=Result.success(account);
            else result=Result.error();
        }else if("USER".equals(role)){
            UserService userService=new UserService();
            if(userService.register(account)!=0)result=Result.success(account);
            else result=Result.error();
        }else{
            result=Result.error();
        }

        String jsonStr= JSON.toJSONString(result);

        response.getWriter().write(jsonStr);
    }
}
