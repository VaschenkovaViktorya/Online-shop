package com.example.onlineshop.servlet;

import com.example.onlineshop.util.Product;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "getProduct", urlPatterns = {"/getProduct"})
public class GetProductServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(String.valueOf(LoginServlet.class));
    List<Product> products= new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        resp.getWriter().append("tovary kategorii " + req.getParameter("category"));
        String errorMsg = null;
        if (category == null || category.equals("")) {
            errorMsg = "Categoty can't be null or empty";
        }
        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out= resp.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(req, resp);
        } else{
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            System.out.println(">>>>>>>>>Connection");
            PreparedStatement ps = null;
            ResultSet rs = null;
            try{
                ps = con.prepareStatement("select id, product_name, category,price,quantity  from product where category=? ");
                ps.setString(1, category);
                rs = ps.executeQuery();
                products.clear();
                if (rs != null){
                    while ( rs.next()) {
//
//                    Product product = new Product(rs.getString("product_name"), rs.getString("category"), rs.getInt("id"), rs.getInt("price"), rs.getInt("quantity"));
//                    HttpSession session = req.getSession();
//                    session.setAttribute("Product", product);
//                    RequestDispatcher rd = req.getRequestDispatcher("/index.html");
//                    rd.forward(req,resp);
                        products.add(new Product(rs.getString("product_name"), rs.getString("category"), rs.getInt("id"), rs.getInt("price"), rs.getInt("quantity")));
                    }
                    resp.getWriter().append(products.toString());
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
}
