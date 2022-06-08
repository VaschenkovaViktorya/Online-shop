<%--import java.util.List;--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.onlineshop.utils.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Testing</title>
</head>
<body>
<%
    List<Product> listOfProduct = (List<Product>) session.getAttribute("ListOf_product");
    out.println("<ui>");
    for (Product s : listOfProduct) {
        out.println("<li>" + s.getName() + s.getPrice()+"<a href =./addToBasket?id" + s.getId() + ">add to basket</a>" +"</li>");
        out.println("</ui>");
    }
%>

<%--<a href=\"./addToBasket?name="--%>
</body>
</html>

