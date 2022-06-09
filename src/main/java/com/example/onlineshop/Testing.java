package com.example.onlineshop;

import com.example.onlineshop.utils.Product;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static com.example.onlineshop.servlet.GetProductServlet.products;
import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public class Testing {
    public static void main(String[] args) {
        //System.out.println(products.toString());
        Map<Integer, Product> product = new HashMap<>();
        product.put(1, new Product("kefir", "diary", 1, 50, 100 ));
        product.put(2, new Product("milk", "diary", 1, 50, 100 ));
        product.put(3, new Product("cheese", "diary", 1, 50, 100 ));
        product.put(4, new Product("yogurt", "diary", 1, 50, 100 ));
        System.out.println(product.get(1));
        for (Map.Entry <Integer, Product> entry : product.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue().getName());
        }
        String test ="";
        if (test.equals("")==true)
        System.out.println("empry");
        else System.out.println("not empry");

    }

}
