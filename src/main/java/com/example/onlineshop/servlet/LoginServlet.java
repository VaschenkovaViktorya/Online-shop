package com.example.onlineshop.servlet;

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
import java.util.logging.Logger;


@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(String.valueOf(LoginServlet.class));
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String errorMsg = null;
        if(email == null || email.equals("")){
            errorMsg ="User Email can't be null or empty";
        }
        if(password == null || password.equals("")){
            errorMsg = "Password can't be null or empty";
        }

        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= resp.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(req, resp);
        }else {

            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            System.out.println(">>>>>>>>>Connection");
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement("select id, customer_name, email,country from users where email=? and customer_password=? limit 1");
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs != null && rs.next()) {

                    User user = new User(rs.getString("customer_name"), rs.getString("email"), rs.getString("country"), rs.getInt("id"));
                    HttpSession session = req.getSession();
                    session.setAttribute("User", user);

                    resp.sendRedirect("home.jsp");
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out = resp.getWriter();
                    out.println("<font color=red>No user found with given email id, please register first.</font>");
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

        }}
}
