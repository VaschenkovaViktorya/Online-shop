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
import java.util.List;

@WebServlet(name = "BuyProduct", urlPatterns = {"/BuyProduct"})
public class BuyProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        Integer totalPrice = (Integer) session.getAttribute("TotalPrice");
        List<Product> basket = (List<Product>) session.getAttribute("myBasket");

        if (user != null && basket.size() > 0) {
            if (totalPrice > user.getMoney()) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewBasket.jsp");
                PrintWriter out = resp.getWriter();
                out.println("<font color=red>You need more money <a href=\"home.jsp\">Personal account</a><br></font>");
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
//                insert into  orders(id_user,id_product)
//                values ('1', '4'),


                    //forward to login page to login
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewBasket.jsp");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=green>Buying is successful.</font>");
                    rd.include(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }



        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewBasket.jsp");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>you must register in system If you are registered user, please <a href=\"login.html\">login</a></font>");
            rd.include(req, resp);
        }
    }
}
