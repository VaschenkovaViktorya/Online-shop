package com.example.onlineshop.servlet.formanager;

import com.example.onlineshop.utils.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static com.example.onlineshop.utils.UsersService.loadUsers;

@WebServlet(name = "getUsers", urlPatterns = {"/getUsers"})
public class GetUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, User> users = loadUsers();
        HttpSession session = req.getSession();
        session.setAttribute("ListUserForManager",users);
        resp.sendRedirect("customerManagement.jsp");
        
    }
}
    


