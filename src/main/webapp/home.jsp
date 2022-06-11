<%@ page import="com.example.onlineshop.utils.User" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Страница покупателя</title>
</head>
<body>
<h3>Страница покупателя</h3>
<%User user = (User) session.getAttribute("User"); %>
<h3>Привет,  <%=user.getName() %></h3>

<p><strong>Ваше имя</strong>: <%=user.getName() %> </p><br>
<p><strong>Ваша электронная почта</strong>: <%=user.getEmail() %></p><br>
<p><strong>Ваша страна</strong>: <%=user.getCountry() %></p><br>
<p><strong>Баланс</strong>: <%=user.getMoney()%></p><br>
<div><a href="edit?id=<%=user.getId()%>">Редактировать профиль</a></div>
<br></br>
<form action="Logout" method="post">
    <input type="submit" value="Выйти">
</form></br>
<a href="index.html">Домой</a><br>
<a href="viewBasket">Просмотр корзины</a></br>
<form action="viewOrder" method="post">
    <input type="submit" value="Просмотр заказов" >
</form>


<%--<a href="viewBasket">basket</a>--%>
</body>
</html>
