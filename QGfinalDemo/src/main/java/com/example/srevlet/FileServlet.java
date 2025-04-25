package com.example.srevlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

@WebServlet("/File")
@MultipartConfig // 添加此注解以支持多部分请求
public class FileServlet extends HttpServlet {

    private String filePath;

    @Override
    public void init() throws ServletException {
        // 获取项目根目录

        filePath = "E:\\程序代码\\GitHubWork\\qgfinal\\QGfinalDemo\\src\\main\\webapp\\file\\";
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("doPost called");
        System.out.println("File path: " + filePath);

        String method = request.getParameter("method");
        System.out.println("Method: " + method);
        if ("upload".equals(method)) {
            upload(request, response);
        } else {
            response.getWriter().write("error:known");
        }
    }

    private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Part filePart = null;
        try {
            filePart = request.getPart("file");
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        String fileName = filePart.getSubmittedFileName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

        if (!fileExtension.equals(".jpg") && !fileExtension.equals(".jpeg") &&
                !fileExtension.equals(".png") && !fileExtension.equals(".gif") &&
                !fileExtension.equals(".bmp")&&!fileExtension.equals(".jfif")) {
            response.getWriter().write("error:只支持图片文件");
            return;
        }

        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        try {
            filePart.write(filePath + uniqueFileName);
            System.out.println("File written successfully: " + filePath + uniqueFileName);
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
            response.getWriter().write("error:文件写入失败");
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write( uniqueFileName );
    }
}