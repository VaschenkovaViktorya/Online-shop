package com.example.onlineshop.servlet.foruser;

import com.example.onlineshop.utils.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "edit", urlPatterns = {"/edit"})
public class EditPersonalInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Integer id = Integer.valueOf(req.getParameter("id"));
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("User");
            Integer i = user.getId();
            resp.getWriter().append("(char) user.getId()" + i);
            if (user != null) {
                req.setAttribute("editUser", user);
                getServletContext().getRequestDispatcher("/editUser.jsp").forward(req, resp);
            }else{
               // resp.getWriter().append("Not found!");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("");

    }
}
