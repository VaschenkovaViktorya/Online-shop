<%@ page import="com.example.onlineshop.utils.User" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Управление магазином ${User.name}</title>
</head>
<body>
<%User user = (User) session.getAttribute("User"); %>
<h3>Добро пожаловать, <%=user.getName() %> </h3>
<%--<h3>Hi ${User.name}</h3>--%>
<%--<p><strong>Your Name</strong>: <%=user.getName() %> </p><br>
<p><strong>Your Email</strong>: <%=user.getEmail() %></p><br>
<p><strong>Your Country</strong>: <%=user.getCountry() %></p><br>
<p><strong>money</strong>: <%=user.getMoney()%></p><br>
<div><a href="edit?id=<%=user.getId()%>">Edit personal info</a></div>--%>
<a href="getUsers">Редактирование пользователей </a><br>
<a href="index.html">Редактирование товаров</a><br>
<br>
<%--<form action="Logout" method="post">--%>
<%--    <input type="submit" value="Logout">--%>
<%--</form>--%>
<a href="index.html">home</a><br>

<%--<a href="viewBasket">you basket</a></br>--%>
<%--<form action="viewOrder" method="post">
    <input type="submit" value="viewOrder" >
</form>--%>


<%--<a href="viewBasket">basket</a>--%>
</body>
</html>