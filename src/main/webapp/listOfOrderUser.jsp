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
    <title>List of ORDER</title>
</head>
<body>
<p><b>List of ORDER</b></p> <br/>
<%
    List<Product> listOfProduct = (List<Product>) session.getAttribute("listOfOrderProduct");
    out.println("<ui>");
    for (Product s : listOfProduct) {
        out.println("<li>" + s.getName() + s.getPrice()+"<a href =./addToBasket?id=" + s.getId() + ">add to basket</a>" +"</li>");
        out.println("</ui>");
    }
%>

<a href="./viewBasket">basket</a>
<%--<%--%>
<%--    Map<Integer, Product> mapOfProduct = (Map<Integer, Product>) session.getAttribute("Map_of_product");--%>
<%--    out.println("<ui>");--%>
<%--    for (Map.Entry <Integer, Product> entry : mapOfProduct.entrySet()){--%>
<%--        out.println("<li>" + entry.getValue().getName() + entry.getValue().getPrice()+"<a href =./addToBasket?id=" + entry.getKey() + ">add to basket</a>" +"</li>");--%>
<%--        out.println("</ui>");--%>
<%--    }--%>
<%--%>--%>
<a href="index.html">Домой</a><br>
<a href="home.jsp">Личный профиль</a><br>


<%--<a href=\"./addToBasket?name="--%>
</body>
</html>

