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
import java.util.List;
@WebServlet(name = "viewBasket", urlPatterns = {"/viewBasket"})
public class ViewBasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        List<Product> mybasket = (List<Product>) httpSession.getAttribute("myBasket");
        if (mybasket != null && mybasket.size()!=0)
        {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewBasket.jsp");
            rd.forward(req, resp);
        }/*{
            resp.getWriter().append("<table>  <tr>\n" +
                    "    <th>Name</th>\n" +
                    "    <th>Category</th>\n" +
                    "    <th>Price</th>\n" +
                    "  </tr>");
            for (int i=0;i< mybasket.size(); i++){
                Product p = mybasket.get(i);
                resp.getWriter().append("  <tr>\n" +
                        "    <td>"+ p.getName()+"</td>\n" +
                        "    <td>"+p.getCategory() +"</td>\n" +
                        "    <td>"+p.getPrice() +"</td>\n" +
                        "    <td> <p><a href=\"./remove?id="+i +"\">"+"X"+"</a></p></td>\n" +
                        "  </tr>");
//                response.getWriter().append("<p><a href=\"./basket?name=" + foundProduct.getName() + "\"> Добавить в корзину </a></p>");

            }

            resp.getWriter().append("</table>");
        }*/ else {
            resp.getWriter().append("Your basket is empty");
        }
    }
}
