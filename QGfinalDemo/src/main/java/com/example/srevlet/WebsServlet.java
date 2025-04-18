package com.example.srevlet;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.service.AdminService;
import com.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/WebsServlet")
public class WebsServlet extends BaseServlet {

    public void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String avatar=request.getParameter("avatar");
        String role=request.getParameter("role");
        //avatar
        Account account=new Account();
        account.setUsername(username);
        account.setRole(role);
        Result result = null;
        if("ADMIN".equals(role)){
            AdminService adminService=new AdminService();
            account=adminService.selectSingle(account);
            if(account!=null){
                account.setPassword(password);
                account.setName(name);
                account.setEmail(email);
                account.setPhone(phone);
                account.setAvatar(avatar);
                if(adminService.update(account)>0) result = Result.success(account);
                else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }else{
                result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }
        }else if("USER".equals(role)){
            UserService userService=new UserService();
            account=userService.selectSingle(account);
            if(account!=null){
                account.setPassword(password);
                account.setName(name);
                account.setEmail(email);
                account.setPhone(phone);
                account.setAvatar(avatar);
                if(userService.update(account)>0) result = Result.success(account);
                else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }else{
                result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }
        }else{
            result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        String jsonStr=JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void loadAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        String role=(String) session.getAttribute("role");
        Account account=new Account();
        Result result=null;
        account.setUsername(username);
        account.setRole(role);

        if("ADMIN".equals(role)){
            AdminService adminService=new AdminService();
            account=adminService.selectSingle(account);
            if(account!=null){
                result=Result.success(account);
            }else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }else if("USER".equals(role)){
            UserService userService=new UserService();
            account=userService.selectSingle(account);
            if(account!=null){
                result=Result.success(account);
            }else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }else{
            result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        String jsonStr=JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void getRememberCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Cookie[] cookies = request.getCookies();
        String username=null;
        String role=null;
        for(Cookie cookie : cookies){
            String name=cookie.getName();
            if("username".equals(name)){
                username=cookie.getValue();
            }
            if("role".equals(name)){
                role=cookie.getValue();
            }
            if(username!=null&&role!=null){
                break;
            }
        }
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        if(username!=null&&role!=null){
            Result result=null;
            Account account=new Account();
            account.setUsername(username);
            account.setRole(role);
            if("USER".equals(role)){
                UserService userService=new UserService();
                account = userService.selectSingle(account);
                if(account!=null) result=Result.success(account);
                else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }else if("ADMIN".equals(role)){
                AdminService adminService=new AdminService();
                account = adminService.selectSingle(account);
                if(account!=null) result=Result.success(account);
                else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }else{
                result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }
            String jsonStr=JSON.toJSONString(result);
            response.getWriter().write(jsonStr);

        }


    }

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
            else result=Result.error(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }else if("USER".equals(role)){
            UserService userService=new UserService();
            account=userService.login(account);
            if(account!=null)result=Result.success(account);
            else result=Result.error(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }else result=Result.error();
        //如果result成功而且remember就加Cookie
        //登录成功后将用户加进session作为登录凭证

        if("true".equals(remember)&&"200".equals(result.getCode())){
            Cookie c_username=new Cookie("username",username);
            Cookie c_role=new Cookie("role",role);

            c_username.setMaxAge(60*60*24*7);
            c_role.setMaxAge(60*60*24*7);

            response.addCookie(c_username);
            response.addCookie(c_role);

        }

        if("200".equals(result.getCode())){
            HttpSession session=request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("role",role);
        }


        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前会话
        HttpSession session = request.getSession(false); // 使用 false 参数，避免创建新的会话
        if (session != null) {
            // 清除会话中的所有属性
            session.invalidate(); // 销毁当前会话
        } else {
            System.out.println("No active session found. User already logged out.");
        }
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
//        System.out.println(checkCodeGen);



        Result result =null;
//        if(checkCodeGen==null||!(checkCodeGen.equals(checkcode))){
//            result=Result.error(ResultCodeEnum.CHECK_CODE_ERROR);
//        }else{
            if("ADMIN".equals(role)){
                AdminService adminService=new AdminService();
                if(adminService.register(account)!=0)result=Result.success(account);
                else result=Result.error(ResultCodeEnum.USER_EXIST_ERROR);
            }else if("USER".equals(role)){
                UserService userService=new UserService();
                if(userService.register(account)!=0)result=Result.success(account);
                else result=Result.error(ResultCodeEnum.USER_EXIST_ERROR);
            }else{
                result=Result.error();
            }
    //    }

        String jsonStr= JSON.toJSONString(result);

        response.getWriter().write(jsonStr);
    }
}
