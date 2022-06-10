package com.example.onlineshop.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UsersService {

    public static Map<Integer, User> loadUsers() {
        Map<Integer, User> users = new HashMap<>();
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testdb", "postgres", "1234")) {

                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("select id, customer_name, email,country, money , manager from users where  manager is null ");
                rs = ps.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        //  products.add(new Product(rs.getString("product_name"), rs.getString("category"), rs.getInt("id"), rs.getInt("price"), rs.getInt("quantity")));
                        users.put(rs.getInt("id"), new User(rs.getString("customer_name"),
                                rs.getString("email"), rs.getString("country"), rs.getInt("id"), rs.getInt("money")));
                    }

                }
                return users;
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
