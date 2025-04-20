package com.example.srevlet.Manager;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.common.enums.DealStatusEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Deal;
import com.example.entity.Log;
import com.example.service.DealService;
import com.example.service.LogService;
import com.example.srevlet.BaseServlet;
import com.example.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Manager/Log")
public class ManagerLogServlet extends BaseServlet {

    public void LoadLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectWord = request.getParameter("selectWord");
        LogService logService = new LogService();
        List<Log> logs = null;
        Result result=null;
        if(selectWord != null&& !selectWord.isEmpty()){
            logs=logService.selectList(selectWord);
        }else{
            logs=logService.selectAll();
        }
        if(logs!=null){
            result=Result.success(logs);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }



}
