package com.example.srevlet;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.AccountStatusEnum;
import com.example.common.enums.LogsTypeEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.entity.Log;
import com.example.entity.Session;
import com.example.entity.Systeminfo;
import com.example.service.AdminService;
import com.example.service.LogService;
import com.example.service.SessionService;
import com.example.service.UserService;
import com.example.util.CheckCodeUtil;
import com.example.util.IpUtils;
import com.example.util.SendEmailUtil;
import com.example.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/WebsServlet")
public class WebsServlet extends BaseServlet {

    public void LoadSystem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result=null;
        Systeminfo systeminfo =Systeminfo.getInstance();
        if(systeminfo!=null){
            result=Result.success(systeminfo);
        }else{
            result=Result.error(ResultCodeEnum.SYSTEM_ERROR);
        }
        String jsonStr=JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }


    public void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String avatar=request.getParameter("avatar");
        String role=request.getParameter("role");
        String payPassword=request.getParameter("payPassword");
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
                account.setPayPassword(payPassword);
                if(userService.update(account)>0) result = Result.success(account);
                else result=Result.error(ResultCodeEnum.PARAM_EXIST_ERROR);
            }else{
                result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }
        }else{
            result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        if("200".equals(result.getCode())){
            LogService logService=new LogService();
            logService.recordLog(username,LogsTypeEnum.UPDATE_ACCOUNT.getValue(),"更新账户成功",request);
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
            if(account!=null&&account.getStatus().equals(AccountStatusEnum.NORMAL.getValue()))result=Result.success(account);
            else if(account==null)
                result=Result.error(ResultCodeEnum.USER_ACCOUNT_ERROR);
            else result=Result.error(ResultCodeEnum.BAN_ERROR);

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

            LogService logService=new LogService();
            logService.recordLog(username,LogsTypeEnum.LOGIN.getValue(),"登录成功",request);
            HttpSession session=request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("role",role);
            if (account != null) {
                session.setAttribute("id",account.getId());
                if(account.getName()!=null)
                    session.setAttribute("name",account.getName());
            }
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
        UserService userService=new UserService();

        HttpSession session=request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");



        Result result =null;
        if(checkCodeGen==null||!(checkCodeGen.equals(checkcode))){
            result=Result.error(ResultCodeEnum.CODE_ERROR);
        }else{
            if("ADMIN".equals(role)){
                AdminService adminService=new AdminService();
                if(adminService.register(account)!=0)result=Result.success(account);
                else result=Result.error(ResultCodeEnum.USER_EXIST_ERROR);
            }else if("USER".equals(role)){
                if(userService.register(account)!=0)result=Result.success(account);
                else result=Result.error(ResultCodeEnum.USER_EXIST_ERROR);
            }else{
                result=Result.error();
            }
        }

        if("200".equals(result.getCode())){
            LogService logService=new LogService();
            logService.recordLog(username,LogsTypeEnum.REGISTER.getValue(),"注册成功",request);

            if("USER".equals(role)){
                account = userService.selectSingle(account);
                Session sess = new Session();
                sess.setHeadId(1);
                sess.setHeadRole("SYSTEM");
                sess.setHindId(account.getId());
                sess.setHindRole(account.getRole());
                sess.setSessionTime(TimeUtil.getTime());
                SessionService sessionService=new SessionService();
                sessionService.add(sess);
            }

        }
        String jsonStr= JSON.toJSONString(result);

        response.getWriter().write(jsonStr);
    }
    public void CheckCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream os=response.getOutputStream();
        try {
            String checkCode = CheckCodeUtil.outputVerifyImage(80, 35, os, 4);
            HttpSession session = request.getSession();
            System.out.println("Original_C:"+checkCode);
            session.setAttribute("checkCodeGen", checkCode);
        } finally {
            os.close(); // 确保关闭输出流
        }
    }
    public void SendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户输入的邮箱地址
        String email = request.getParameter("email");
        SendEmailUtil sendEmailUtil=new SendEmailUtil();
        // 生成验证码
        String verificationCode = sendEmailUtil.generateVerificationCode();

        // 发送邮件
        boolean isSent = sendEmailUtil.sendEmail(email, verificationCode);

        if (isSent) {
            response.getWriter().write("验证码已发送到您的邮箱: " + email);
            HttpSession session=request.getSession();
            session.setAttribute("verificationCodeGen",verificationCode);
            System.out.println("verificationCode:"+verificationCode);
            response.getWriter().write("发送验证码成功");
        } else {
            response.getWriter().write("发送验证码失败，请稍后再试");
        }
    }
    public void SaveChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String code=request.getParameter("code");
        String role=request.getParameter("role");
        Account account=new Account();
        account.setEmail(email);
        Result result = null;
        HttpSession session=request.getSession();
        String codeGen = (String) session.getAttribute("verificationCodeGen");
        if(!(code.equals(codeGen))){
            result = Result.error(ResultCodeEnum.CODE_ERROR);
        }else{
            if("ADMIN".equals(role)){
                AdminService adminService=new AdminService();
                account=adminService.selectSingle(account);
                if(account!=null){
                    account.setPassword(password);
                    adminService.update(account);
                    result=Result.success(account);
                }else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }else if("USER".equals(role)){
                UserService userService=new UserService();
                account=userService.selectSingle(account);
                if(account!=null){
                    account.setPassword(password);
                    userService.update(account);
                    result=Result.success(account);
                }else result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }else{
                result=Result.error(ResultCodeEnum.USER_NOT_EXIST_ERROR);
            }
        }


        String jsonStr=JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
}


