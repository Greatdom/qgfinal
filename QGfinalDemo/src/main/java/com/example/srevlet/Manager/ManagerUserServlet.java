package com.example.srevlet.Manager;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.Log;
import com.example.service.LogService;
import com.example.service.UserService;
import com.example.srevlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Manager/User")
public class ManagerUserServlet extends BaseServlet {
    public void LoadUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        List<Account> users=userService.selectAll();
        Result result=null;
        if(users!=null){
            result=Result.success(users);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
}
