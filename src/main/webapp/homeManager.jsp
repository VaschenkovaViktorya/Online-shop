<%@ page import="com.example.onlineshop.utils.User" %>

<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Home Page</title>
</head>
<body>
<%User user = (User) session.getAttribute("User"); %>
<h3>Hi <%=user.getName() %></h3>
<h3>Personal manager's page</h3>
<p><strong>Your Name</strong>: <%=user.getName() %> </p><br>
<p><strong>Your Email</strong>: <%=user.getEmail() %></p><br>
<p><strong>Your Country</strong>: <%=user.getCountry() %></p><br>
<p><strong>money</strong>: <%=user.getMoney()%></p><br>
<div><a href="edit?id=<%=user.getId()%>">Edit personal info</a></div>
<br>
<form action="Logout" method="post">
    <input type="submit" value="Logout">
</form>
<a href="index.html">home</a><br>
<a href="viewBasket">basket</a></br>
<form action="viewOrder" method="post">
    <input type="submit" value="viewOrder" >
</form>
<a href="workManager.jsp">Edit shop</a></br>

<%--<a href="viewBasket">basket</a>--%>
</body>
</html>