package com.example.onlineshop.servlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/register" )
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String mail = req.getParameter("email");
        resp.getWriter().append(mail);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        String errorMsg = null;
        if(email == null || email.equals("")){
            errorMsg = "Введите почту.";
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
        }else {
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement("insert into users(customer_name,email,country, customer_password) values (?,?,?,?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, country);
                ps.setString(4, password);

                ps.execute();


                //forward to login page to login
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out= resp.getWriter();
                out.println("<font color=green>Registration successful, please login below.</font>");
                rd.include(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }
}
