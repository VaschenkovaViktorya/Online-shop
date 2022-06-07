package com.example.onlineshop;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestDBConnectionPostgres {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/testdb";
    static final String USER = "postgres";
    static final String PASS = "1234";
    public static void main(String[] args) {
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }
    }
   /* public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Connection connection = null;
        try{
            connection = getConnection();
            Statement st=  connection.createStatement();
                   // addColumnSQL(st);
           // insertToTable(st);
        } finally{
            if (connection!=null)
            connection.close();
        }

    }

    private static void addColumnSQL(Statement st) throws SQLException {
        st.execute("ALTER TABLE product\n" +
                "ADD  quantity varchar(255)");
    }

    private static void insertToTable(Statement st) throws SQLException {
        st.executeUpdate("INSERT product \nVALUES\n(6,\"bread\", 50)");
    }

    private static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            Properties property = getProperties();
            String url = property.getProperty("url");
            String username = property.getProperty("username");
            String password = property.getProperty("password");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful");
            return connection;
        } catch (Exception e) {
            System.out.println("connection failed...");
            System.out.println(e);
        }
        return null;

    }

    private static Properties getProperties() {
        Properties property = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src/resourses/database.properties"))) {
            property.load(in);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return property;
    }*/

