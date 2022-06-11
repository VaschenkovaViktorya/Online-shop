package com.example.onlineshop.servlet;

import com.example.onlineshop.utils.Basket;
import com.example.onlineshop.utils.Product;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BuyProduct", urlPatterns = {"/BuyProduct"})
public class BuyProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        Integer totalPrice = (Integer) session.getAttribute("TotalPrice");
        List<Product> basket = (List<Product>) session.getAttribute("myBasket");
        Integer newMoney = null;

        if (user != null && basket.size() > 0) {
            if (totalPrice > user.getMoney()) {
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewBasket.jsp");
                PrintWriter out = resp.getWriter();
                out.println("<font color=red>Недостаточно средств на Вашем счете,  <a href=\"home.jsp\">пополнить</a><br></font>");
                rd.include(req, resp);
            }else{
                // resp.getWriter().append("You buy" + user.getName() + basket.toString());
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                System.out.println("Buying DBConnection>>>>>>>");
                System.out.println("BASKET " + basket.toString());
                PreparedStatement ps = null;
                try {
                    for (Product p : basket) {
                        ps = con.prepareStatement("insert into  orders(id_user,id_product) values (?,?)");
                        ps.setInt(1, user.getId());
                        ps.setInt(2, p.getId());
                        System.out.println("p.getId()" + p.getId());
                        System.out.println("user.getId()" + user.getId());
                        ps.execute();
                    }
                    ps = null;
                    newMoney = user.getMoney() - totalPrice;
                    try {
                        ps = con.prepareStatement("update users set money =? where id=?");
                        ps.setInt(1, newMoney);
                        ps.setInt(2, user.getId());

                        ps.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewBasket.jsp");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=green>Покупка совершена</font>");
                    rd.include(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }



        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewBasket.jsp");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Вам нужно авторизоваться, перейдите на страницу <a href=\"login.html\">авторизации</a></font>");
            rd.include(req, resp);
        }
    }
}
