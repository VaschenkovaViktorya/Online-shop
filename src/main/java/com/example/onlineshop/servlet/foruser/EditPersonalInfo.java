package com.example.onlineshop.servlet.foruser;

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

@WebServlet(name = "edit", urlPatterns = {"/edit"})
public class EditPersonalInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try {
            Integer id = Integer.valueOf(req.getParameter("id"));
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("User");
            Integer i = user.getId();
            resp.getWriter().append("(char) user.getId()" + i);
            if (user != null) {
                req.setAttribute("editUser", user);
                getServletContext().getRequestDispatcher("/editUser.jsp").forward(req, resp);
            } else {
                // resp.getWriter().append("Not found!");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        //для сохранения при релогине
        User user = new User();
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        Integer money = Integer.valueOf(req.getParameter("money"));
        //resp.getWriter().append("name " +name+ "email"+ email + "country"+country +"money"+money);
        {
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement ps = null;
            try {
                changeinfo(id, name, email, country, money, con);
                ps = null;
                ResultSet rs = null;
                try {
                    ps = con.prepareStatement("select id, customer_name, email,country, money,manager from users where id=? ");
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    if (rs != null && rs.next()) {

                        // User user = new User(rs.getString("customer_name"), rs.getString("email"), rs.getString("country"), rs.getInt("id"));
                        user = new User(rs.getString("customer_name"),
                                rs.getString("email"), rs.getString("country"), rs.getInt("id"), rs.getInt("money"));

                        HttpSession session = req.getSession();
                        session.setAttribute("User", user);

                    }

                    if ((rs.getString("manager") != null) && (rs.getString("manager").equals("manager"))) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/homeManager.jsp");
                        PrintWriter out = resp.getWriter();
                        out.println("<font color=green>Данные успешно изменены</font>");
                        rd.include(req, resp);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                        PrintWriter out = resp.getWriter();
                        out.println("<font color=green>Данные успешно изменены</font>");
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


/*                User user = (User) req.getSession().getAttribute("User");
                if(money.equals(user.getMoney())) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=green>Update successful, please relogin below if you want change .</font>");
                     relogin =true;
                    req.getSession().setAttribute("Relogin",relogin);
                    rd.include(req, resp);
                } else {
                    user.setMoney(money);
                    req.getSession().setAttribute("User",user);

                    //forward to login page to login
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=green>Update successful, please relogin below.</font>");
                    rd.include(req, resp);
                }*/

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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
