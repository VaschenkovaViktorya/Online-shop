package com.example.onlineshop.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "test", urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Test is working");
        List<String > list = null;
        list = new ArrayList<>();
        for (int i=0 ;i<5;i++){

            list.add("user "+i);
        }
        System.out.println(">>>>>>Test Servlet is working "+list.toString());
        HttpSession session = req.getSession();
        session.setAttribute("List", list);

        resp.sendRedirect("listOfProduct.jsp");

    }
}
