<%@ page import="com.example.onlineshop.utils.User" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Личный профиль Менеджера</title>
</head>
<body>
<%User user = (User) session.getAttribute("User"); %>
<h3>Добро пожаловать,  <%=user.getName() %></h3>
<h4>Личный профиль Менеджера</h4>
</br>
<p><strong>Ваше имя</strong>: <%=user.getName() %> </p>
<p><strong>Ваш Email</strong>: <%=user.getEmail() %></p>
<p><strong>Ваша страна</strong>: <%=user.getCountry() %></p>
<p><strong>Баланс</strong>: <%=user.getMoney()%></p>
<div><a href="edit?id=<%=user.getId()%>">Изменить данные о себе</a></div>
<br>
<form action="Logout" method="post">
    <input type="submit" value="Выйти">
</form>
<form action="viewOrder" method="post">
    <input type="submit" value="Список заказов" >
</form> </br>
<a href="index.html">Домой</a><br>
<a href="viewBasket">Корзина</a></br>
<a href="workManager.jsp">Администрирование магазина</a></br>

<%--<a href="viewBasket">basket</a>--%>
</body>
</html>