package com.example.srevlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/WebsServlet")
public class WebsServlet {

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login");
    }
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("logout");
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("register");
    }
}
