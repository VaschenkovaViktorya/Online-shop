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

<p><a href="createNewUser">Создать нового пользователя</a></p>
<h2>Список пользователей</h2>
<form action="getUsers" method="get">
    <input type="submit" value="Обновить">
</form>
<%
    Map<Integer, User> mapOfUsers = (Map<Integer, User>) session.getAttribute("ListUserForManager");


    out.println("<ui>");
    for (Map.Entry <Integer, User> entry : mapOfUsers.entrySet()){
        out.println("<li>" + entry.getValue().getName() +" "+ entry.getValue().getEmail()+" "+entry.getValue().getCountry()+" " +entry.getValue().getMoney()+" "+"<a href =managerEditUser?id=" + entry.getKey() + ">Изменить</a>" +"<form method=\"post\" action=\"DeleteUser\" style=\"display:inline;\">\n" +
                "    <input type=\"hidden\" name=\"id\" value="+entry.getKey()+">\n" +
                "    <input type=\"submit\" value=\"Удалить\">\n" +
                "</form></li>");

        out.println("</ui>");
    }
%>
<a href="workManager.jsp">Назад в управление магазином</a><br>
<a href="homeManager.jsp">Перейти в личный профиль</a><br>
<a href="index.html">Домой</a>
</body>
</html>
