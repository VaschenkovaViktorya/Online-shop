package com.example.onlineshop.servlet;

import com.example.onlineshop.utils.Product;
import com.example.onlineshop.utils.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.onlineshop.utils.ProductService.loadProduct;


@WebServlet(name = "addToBasket", urlPatterns = {"/addToBasket"})
public class AddToBasket extends HttpServlet {
    private Map<Integer,Product> mapProduct = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String uri = req.getParameter("uri");

//        products.get()
        // resp.getWriter().append(id);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        String name = "";
        if (user != null) {
            name = user.getName();
        }
        String Msg  = "Product added to basket  "+id ;
//формирование корзины
        //???? могу и из сессии получит????
       // здесь надо брать из Map_of_product
        mapProduct = loadProduct();
        Product selectedProduct = mapProduct.get(id);
        System.out.println(">>>>>> mapProduct" + mapProduct.get(id).toString());
       // System.out.println(">>>>>est' li product "+selectedProduct.toString());
        Object myBasket = req.getSession().getAttribute("myBasket");
        if (myBasket!=null){
            List<Product> list =(List<Product>)myBasket;
            list.add(selectedProduct);
        } else{
            List<Product> list = new ArrayList<>();
            list.add(selectedProduct);
            req.getSession().setAttribute("myBasket",list);
        }
        //System.out.println("<<<<<<<<<<<<"+myBasket.toString());

//        req.getContextPath() + "/index"
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/"+uri+".jsp");
        PrintWriter out= resp.getWriter();
/*        for (int j=0;j<5; j++ ) out.println("<a href = #><font color=blue>" + Msg + "</font>circle</a>");
        //out.println("<a href = #><font color=red>"+Msg+"</font></a>");*/
        out.println("<font color=red>"+Msg+"</font>");

        rd.include(req, resp);

    }
}
