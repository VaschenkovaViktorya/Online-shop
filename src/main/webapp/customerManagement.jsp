<%@ page import="com.example.onlineshop.utils.User" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: gorvi
  Date: 11.06.2022
  Time: 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Map<Integer, User> mapOfUsers = (Map<Integer, User>) session.getAttribute("ListUserForManager");


    out.println("<ui>");
    for (Map.Entry <Integer, User> entry : mapOfUsers.entrySet()){
        out.println("<li>" + entry.getValue().getName() + entry.getValue().getEmail()+"<a href =#" + entry.getKey() + ">Edit</a>" +"</li>");
        out.println("</ui>");
    }
%>
</body>
</html>
