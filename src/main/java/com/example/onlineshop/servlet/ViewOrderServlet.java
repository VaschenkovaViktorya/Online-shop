package com.example.onlineshop.servlet;

import com.example.onlineshop.utils.Product;
import com.example.onlineshop.utils.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "viewOrder", urlPatterns = {"viewOrder"})
public class ViewOrderServlet extends HttpServlet {
    private List<Product> orderProducts = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        if (user != null) {
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            System.out.println("viewOrder>>>>>>>>>Connection DBConnection");
            PreparedStatement ps = null;
            ResultSet rs = null;
            Integer i = 1;
            try {
                ps = con.prepareStatement("select product.id, product_name, category,price,quantity  from orders join users on orders.id_user= users.id join product on orders.id_product=product.id where orders.id_user=? ");
                ps.setInt(1, user.getId());
                rs = ps.executeQuery();
                System.out.println("ps.executeQuery();>>>>>>>>>Connection DBConnection");

                orderProducts.clear();
                if (rs != null) {
                    System.out.println((rs != null) + ">>>>>>>>>Connection DBConnection");
                    while (rs.next()) {
                        orderProducts.add(new Product(rs.getString("product_name"), rs.getString("category"), rs.getInt("id"), rs.getInt("price"), rs.getInt("quantity")));
                    }
                    session.setAttribute("listOfOrderProduct", orderProducts);
                    resp.sendRedirect("listOfOrderUser.jsp");
                    //                  resp.getWriter().append(products.toString());
                }


            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("DB Connection problem.");
            } finally {
                try {
                    rs.close();
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("SQLException in closing PreparedStatement or ResultSet");
                }

            }
        } else {
            resp.getWriter().append("you must authrization");
        }

    }
}
