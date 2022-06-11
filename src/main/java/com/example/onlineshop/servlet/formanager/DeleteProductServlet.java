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
@WebServlet(name="deleteProduct",urlPatterns = {"deleteProduct"})
public class DeleteProductServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Integer id = Integer.valueOf(req.getParameter("id"));
        // resp.getWriter().append(" "+id);
        //delete from users where id = '6'
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("delete from product where id = ?");
            ps.setInt(1, id);
            ps.execute();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/productManagement.jsp");
            PrintWriter out= resp.getWriter();
            out.println("<font color=green>продукт удален </font>");
            rd.include(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
