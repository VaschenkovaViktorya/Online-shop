package com.example.onlineshop.servlet;

import com.example.onlineshop.utils.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addToBasket", urlPatterns = {"/addToBasket"})
public class AddToBasket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        // resp.getWriter().append(id);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        String name = "";
        if (user != null) {
            name = user.getName();
          //  resp.getWriter().append(id + " " + name);
        }
        String Msg  = "Product added to basket  "+name;
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/listOfProduct.jsp");
        PrintWriter out= resp.getWriter();
        out.println("<font color=red>"+Msg+"</font>");
        rd.include(req, resp);

    }
}
