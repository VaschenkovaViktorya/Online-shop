package com.example.onlineshop.servlet;

import com.example.onlineshop.utils.Product;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(name = "getProduct", urlPatterns = {"/getProduct"})
public class GetProductServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(String.valueOf(LoginServlet.class));
    public static List<Product> products= new ArrayList<>();
    public static Map<Integer,Product> mapProduct = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            System.out.println(">>>>>>>>>Connection");
            PreparedStatement ps = null;
            ResultSet rs = null;
            try{
                ps = con.prepareStatement("select id, product_name, category,price,quantity  from product ");
                //ps.setString(1, category);
                rs = ps.executeQuery();
                products.clear();
                if (rs != null){
                    while ( rs.next()) {
                        //  products.add(new Product(rs.getString("product_name"), rs.getString("category"), rs.getInt("id"), rs.getInt("price"), rs.getInt("quantity")));
                        mapProduct.put(rs.getInt("id"),new Product(rs.getString("product_name"),
                                rs.getString("category"), rs.getInt("id"),
                                rs.getInt("price"), rs.getInt("quantity")));
                    }
                    HttpSession session = req.getSession();
                    session.setAttribute("ListOf_product", products);
                    session.setAttribute("Map_of_product",mapProduct);
                    resp.sendRedirect("listOfProduct.jsp");
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


    }
}
