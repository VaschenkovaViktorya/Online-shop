package com.example.onlineshop.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "Logout", urlPatterns = { "/Logout" })
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
Logger logger = Logger.getLogger(String.valueOf(LogoutServlet.class));
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setContentType("text/html");
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    logger.info("JSESSIONID="+cookie.getValue());
                    break;
                }
            }
        }
        //invalidate the session if exists

        HttpSession session = req.getSession(false);
/*        boolean relog = (boolean) session.getAttribute("Relogin");
        if ( !relog )*/

        if(session != null){
            session.invalidate();
           // session.removeAttribute("myBasket");
        }
        resp.sendRedirect("login.html");
    }
}
