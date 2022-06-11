package com.example.onlineshop.servlet.formanager;

import com.example.onlineshop.utils.Product;
import com.example.onlineshop.utils.User;
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
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "managerEditProduct", urlPatterns = "/managerEditProduct")
public class EditManagerProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Map<Integer, Product> product = (Map<Integer, Product>) req.getSession().getAttribute("ListproductsForManager");
        Integer id = Integer.valueOf(req.getParameter("id"));
        if (product != null) {
            Product editedProductByManager = product.get(id);
            if (editedProductByManager != null) {
            } else {//написать что произошла ошибка, выберите пользоателя снова и переход на customerManagement.jsp
            }
            req.setAttribute("editedProductByManager", editedProductByManager);
            getServletContext().getRequestDispatcher("/editProductByManager.jsp").forward(req, resp);

//resp.getWriter().append(editedProductByManager.toString());
        } else {
            //написать что произошла ошибка, выберите пользоателя снова и переход на customerManagement.jsp
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        //resp.getWriter().append("редактирвание пользователя в БД");
        User user = new User();
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");
        String errorMsg = null;
        if (name == null || name.equals("")) {
            errorMsg = "Название не может быть пустным.";
        }
        if (category == null || category.equals("")) {
            errorMsg = "Категория не может быть пустой.";
        }
        if (price == null || price.equals("")) {
            errorMsg = "Цена не может быть пустой.";
        }
        if (quantity == null || quantity.equals("")) {
            errorMsg = "Количество не можут быть пустым.";
        }
        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/editProductByManager.jsp");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>" + errorMsg + "</font>");
            rd.include(req, resp);
        }
        //resp.getWriter().append("name " +name+ "email"+ email + "country"+country +"money"+money);

        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        try {
            changeinfo(id, name, category, Integer.valueOf(price), Integer.valueOf(quantity), con);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/productManagement.jsp");
            PrintWriter out = resp.getWriter();
            out.println("<font color=green>Update successful.</font>");
            rd.include(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void changeinfo(Integer id, String name, String category, Integer price, Integer quantity, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("update product set product_name=?, category = ?, price = ?,  quantity=? where id = ?");
        ps.setString(1, name);
        ps.setString(2, category);
        ps.setInt(3, price);
        ps.setInt(4, quantity);
        ps.setInt(5, id);
        ps.execute();
    }
}
