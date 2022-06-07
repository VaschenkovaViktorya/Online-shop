package com.example.onlineshop.servlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/register" )
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("email");
        resp.getWriter().append(mail);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        String errorMsg = null;
        if(email == null || email.equals("")){
            errorMsg = "Email ID can't be null or empty.";
        }
        if(password == null || password.equals("")){
            errorMsg = "Password can't be null or empty.";
        }
        if(name == null || name.equals("")){
            errorMsg = "Name can't be null or empty.";
        }
        if(country == null || country.equals("")){
            errorMsg = "Country can't be null or empty.";
        }
        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
            PrintWriter out= resp.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(req, resp);
        }

    }
}
