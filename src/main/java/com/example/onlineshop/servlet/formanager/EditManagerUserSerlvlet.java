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
                   // session.setAttribute("User", user);

                }

                if ((rs.getString("manager")!=null) &&(rs.getString("manager").equals("manager"))) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/homeManager.jsp");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=green>Update successful.</font>");
                    rd.include(req, resp);
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/customerManagement.jsp");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=green>Update successful.</font>");
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




