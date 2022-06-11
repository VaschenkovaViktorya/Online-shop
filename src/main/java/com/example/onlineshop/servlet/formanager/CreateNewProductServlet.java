package com.example.onlineshop.servlet.formanager;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name="CreateNewProduct",urlPatterns = {"CreateNewProduct"})
public class CreateNewProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/CreateProduct.jsp").forward(req,resp);
        //resp.getWriter().append("Создание нового продукта");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String product_name = req.getParameter("product_name");
        String category = req.getParameter("category");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");
        String errorMsg = null;
        if(product_name == null || product_name.equals("")){
            errorMsg = "Введите имя продукта";
        }
        if(category == null || category.equals("")){
            errorMsg = "Веедите категорию продукта";
        }
        if(price == null || price.equals("")){
            errorMsg = "Введите цену";
        }
        if(quantity == null || quantity.equals("")){
            errorMsg = "Введите количество";
        }
        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/CreateProduct.jsp");
            PrintWriter out= resp.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(req, resp);
        }else {
            Integer prices = Integer.valueOf(price);
            createNewProduct(req, resp, product_name, category, Integer.valueOf(price), Integer.valueOf(quantity));
        }
    }
    private void createNewProduct(HttpServletRequest req, HttpServletResponse resp, String product_name, String category, Integer price, Integer quantity) {
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into  product(product_name ,category, price,quantity)\n" +
                    "values (?,?,?,?)");
            ps.setString(1, product_name);
            ps.setString(2, category);
            ps.setInt(3, price);
            ps.setInt(4, quantity);

            ps.execute();


            //forward to login page to login
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/productManagement.jsp");
            PrintWriter out= resp.getWriter();
            out.println("<font color=green>!Товар успешно добавлен.</font>");
            rd.include(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
