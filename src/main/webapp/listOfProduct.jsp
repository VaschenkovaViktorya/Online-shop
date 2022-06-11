<%--import java.util.List;--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.onlineshop.utils.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Товары</title>
</head>
<body>
<%--<%
    List<Product> listOfProduct = (List<Product>) session.getAttribute("ListOf_product");
    out.println("<ui>");
    for (Product s : listOfProduct) {
        out.println("<li>" + s.getName() + s.getPrice()+"<a href =./addToBasket?id=" + s.getId() + ">add to basket</a>" +"</li>");
        out.println("</ui>");
    }
%>--%>
<p><b>Список товаров</b></p> <br/>
<a href="./viewBasket">Корзина</a>
<a href="index.html">Домой</a><br>

<%--<%--%>
<%--  Map<Integer, Product> mapOfProduct = (Map<Integer, Product>) session.getAttribute("Map_of_product");--%>


<%--рабочая
    out.println("<ui>");--%>
<%--    for (Map.Entry <Integer, Product> entry : mapOfProduct.entrySet()){--%>
<%--        out.println("<li>" + entry.getValue().getName() +" " +entry.getValue().getCategory()+" "+ entry.getValue().getPrice()+" "+"<a href =./addToBasket?id=" + entry.getKey() + "&uri=listOfProduct>Добавить в корзину</a>" +"</li>");--%>
<%--        out.println("</ui>");--%>
<%--    }--%>
<%--%>--%>

<%--<%
    Map<Integer, Product> mapOfProduct = (Map<Integer, Product>) session.getAttribute("Map_of_product");


    out.println("<ui>");
    for (Map.Entry <Integer, Product> entry : mapOfProduct.entrySet()){
        out.println("<li>" + entry.getValue().getName() +" " +entry.getValue().getCategory()+" "+ entry.getValue().getPrice()+" "+"<a href =./addToBasket?id=" + entry.getKey() + "&uri=listOfProduct>Добавить в корзину</a>" +"</li>");
        out.println("</ui>");
    }
%>--%>
<%
    Map<Integer, Product> mapOfProduct1 = (Map<Integer, Product>) session.getAttribute("Map_of_product");
    out.println("<table>  <tr>\n" +
            "    <th>Название</th>\n" +
            "    <th>Категория</th>\n" +
            "    <th>Цена</th>\n" +
            "    <th></th>\n" +
            "  </tr>");
    for (Map.Entry <Integer, Product> entry : mapOfProduct1.entrySet()){
        out.println("<tr><td>"+entry.getValue().getName()+"</td><td>"+entry.getValue().getCategory()+"</td><td>"+entry.getValue().getPrice()+"</td><td><a href =./addToBasket?id=" + entry.getKey() + "&uri=listOfProduct>Добавить в корзину</a></td></tr>");
    }
    out.println("</table>");
%>



<%--<a href=\"./addToBasket?name="--%>
</body>
</html>

