package com.example.onlineshop.servlet.formanager;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name="managerEditUser", urlPatterns = {"managerEditUser"})
public class EditManagerUserSerlvlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Map<Integer,User> users = (Map<Integer, User>) req.getSession().getAttribute("ListUserForManager");
        Integer id = Integer.valueOf(req.getParameter("id"));
        if (users!=null ){
            User editedUserByManager = users.get(id);
            if (editedUserByManager!=null){}
            else {//написать что произошла ошибка, выберите пользоателя снова и переход на customerManagement.jsp
                 }
            req.setAttribute("editedUserByManager", editedUserByManager);
            getServletContext().getRequestDispatcher("/editUserByManager.jsp").forward(req, resp);

//resp.getWriter().append(editedUserByManager.toString());
        }else {
            //написать что произошла ошибка, выберите пользоателя снова и переход на customerManagement.jsp
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        //resp.getWriter().append("редактирвание пользователя в БД");
        User user = new User();
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        Integer money = Integer.valueOf(req.getParameter("money"));
        //resp.getWriter().append("name " +name+ "email"+ email + "country"+country +"money"+money);

        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        try {
            changeinfo(id, name, email, country, money, con);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/customerManagement.jsp");
            PrintWriter out = resp.getWriter();
            out.println("<font color=green>Update successful.</font>");
            rd.include(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void changeinfo(Integer id, String name, String email, String country, Integer money, Connection con) throws SQLException {
        PreparedStatement ps;
        ps = con.prepareStatement("update users set money = ?, country=?, customer_name=?,email=?  where id = ?");
        ps.setInt(1, money);
        ps.setString(2, country);
        ps.setString(3, name);
        ps.setString(4, email);
        ps.setInt(5, id);
        ps.execute();
    }
}




