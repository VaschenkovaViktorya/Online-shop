package com.example.onlineshop.servlet;

import com.example.onlineshop.utils.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet(name= "remove" ,urlPatterns = {"/remove"})
public class RemoveFromBasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Integer id = Integer.valueOf(req.getParameter("id"));
        Object myBasket = req.getSession().getAttribute("myBasket");
        if (myBasket!=null){
            List<Product> basket = (List<Product>)myBasket;
            basket.remove((int)id);
        }

        RequestDispatcher rd = req.getRequestDispatcher("./viewBasket");
        rd.forward(req,resp);
    }
}
