package com.example.onlineshop.servlet.listener;

import com.example.onlineshop.utils.DBConnectionManager;
import com.example.onlineshop.utils.Product;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.example.onlineshop.utils.ProductService.loadProduct;

@WebListener
public class ProductServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /////Сюда загрузку из базы продуктов
        ServletContext sc =  sce.getServletContext();
        Map<Integer,Product> mProduct = loadProduct();
        sc.setAttribute("MapaProductov", mProduct);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
