package com.example.onlineshop.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ProductService {

    public static Map<Integer, Product> loadProduct() {
        Map<Integer, Product> product = new HashMap<>();
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testdb", "postgres", "1234")) {

                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("select id, product_name, category,price,quantity  from product ");
                //ps.setString(1, category);
                rs = ps.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        //  products.add(new Product(rs.getString("product_name"), rs.getString("category"), rs.getInt("id"), rs.getInt("price"), rs.getInt("quantity")));
                        product.put(rs.getInt("id"), new Product(rs.getString("product_name"),
                                rs.getString("category"), rs.getInt("id"),
                                rs.getInt("price"), rs.getInt("quantity")));
                    }

                }
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
