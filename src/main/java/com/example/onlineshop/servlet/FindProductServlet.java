package com.example.onlineshop.servlet;

import com.example.onlineshop.utils.Product;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "findProduct", urlPatterns = {"/findProduct"})
public class FindProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.setContentType("text/html; charset=UTF-8");
        Map<Integer,Product> findedMapProduct = new HashMap<>();
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");
        List<Product> findedProduct = new ArrayList<>();
        if (name == null || name.equals("") && (category == null || category.equals(""))
                && (price == null || price.equals("")) && (quantity == null || quantity.equals(""))) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/findProduct.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>\"Enter any request\".</font>");
            rd.include(req, resp);
        } else {
            //resp.getWriter().append("finding");
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            System.out.println(">>>>>>>>>Connection");
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                //проверка что не пустые
                if ((price == null || price.equals("")) && (quantity == null || quantity.equals(""))) {
                    System.out.println(">>>>>>begin");
                    ps = con.prepareStatement("select id, product_name, category,price, quantity from product where product_name=? or category=?");
                    System.out.println(">>>>>>con.prepareStatement");
                    ps.setString(1, name);
                    ps.setString(2, category);
                } else {
                    if ((price == null || price.equals(""))) {
                        Integer fquantity = Integer.valueOf(quantity);
                        System.out.println(">>>>>>begin SQL with quantity ");
                        ps = con.prepareStatement("select id, product_name, category,price, quantity from product where product_name=? or category=?  or quantity=?");
                        System.out.println(">>>>>>con.prepareStatement");
                        ps.setString(1, name);
                        ps.setString(2, category);
                        ps.setInt(3, fquantity);
                    } else {
                        Integer fprice = Integer.valueOf(price);
                        if ((quantity == null || quantity.equals(""))) {
                            System.out.println(">>>>>>begin SQL with price ");
                            ps = con.prepareStatement("select id, product_name, category,price, quantity from product where product_name=? or category=? or price=?");
                            System.out.println(">>>>>>con.prepareStatement");
                            ps.setString(1, name);
                            ps.setString(2, category);
                            ps.setInt(3, fprice);
                        } else {
                            Integer fquantity = Integer.valueOf(quantity);
                            System.out.println(">>>>>>begin SQL with ALL ");
                            ps = con.prepareStatement("select id, product_name, category,price, quantity from product where product_name=? or category=? and price=? and quantity=?");
                            System.out.println(">>>>>>con.prepareStatement");
                            ps.setString(1, name);
                            ps.setString(2, category);
                            ps.setInt(3, fprice);
                            ps.setInt(4, fquantity);
                        }
                    }
                }
                rs = ps.executeQuery();
                System.out.println(">>>>>>>>>>>>>>ps.executeQuery()");
                System.out.println(rs==null);
                if (rs != null) {
                    while (rs.next()) {
                        findedProduct.add(new Product(rs.getString("product_name"),
                                rs.getString("category"), rs.getInt("id"),
                                rs.getInt("price"), rs.getInt("quantity")));
                        findedMapProduct.put(rs.getInt("id"),new Product(rs.getString("product_name"),
                                rs.getString("category"), rs.getInt("id"),
                                rs.getInt("price"), rs.getInt("quantity")));
                        /*                    Product findProduct = new Product(rs.getString("product_name"),
                            rs.getString("category"), rs.getInt("id"),
                            rs.getInt("price"), rs.getInt("quantity"));*/
                    }
                    if (findedMapProduct.isEmpty()){
                        System.out.println(">>>>>>>map is empry");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/findProduct.html");
                        PrintWriter out = resp.getWriter();
                        out.println("<font color=red>product not found</font>");
                        rd.include(req, resp);
                    } else{
                                            HttpSession session = req.getSession();
                    session.setAttribute("findedMapProduct", findedMapProduct);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/listOfFindedProduct.jsp");
                    rd.forward(req, resp);
                      //  resp.getWriter().append("product is finded" + findedProduct.toString());
                    }




                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/findProduct.html");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=red>product not found</font>");
                    rd.include(req, resp);
                }
            } catch (SQLException e) {
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
