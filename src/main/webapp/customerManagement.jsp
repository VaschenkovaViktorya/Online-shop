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
<%--<%
    Map<Integer, User> mapOfUsers = (Map<Integer, User>) session.getAttribute("ListUserForManager");


    out.println("<ui>");
    for (Map.Entry<Integer, User> entry : mapOfUsers.entrySet()) {
        out.println("<li>" + entry.getValue().getName() + " " + entry.getValue().getEmail() + " " + entry.getValue().getCountry() + " " + entry.getValue().getMoney() + " " + "<a href =managerEditUser?id=" + entry.getKey() + ">Изменить</a>" + "<form method=\"post\" action=\"DeleteUser\" style=\"display:inline;\">\n" +
                "    <input type=\"hidden\" name=\"id\" value=" + entry.getKey() + ">\n" +
                "    <input type=\"submit\" value=\"Удалить\">\n" +
                "</form></li>");

        out.println("</ui>");
    }
%>--%>
<%
    Map<Integer, User> mapOfUsers1 = (Map<Integer, User>) session.getAttribute("ListUserForManager");
    out.println("<table>  <tr>\n" +
            "    <th>Имя</th>\n" +
            "    <th>Email</th>\n" +
            "    <th>Страна</th>\n" +
            "    <th>Баланс</th>\n" +
            "    <th></th>\n" +
            "    <th></th>\n" +
            "  </tr>");
    for (Map.Entry<Integer, User> entry : mapOfUsers1.entrySet()) {
        out.println("<tr><td>"+entry.getValue().getName()+"</td><td>"+entry.getValue().getEmail()+"</td><td>"+entry.getValue().getCountry()+"</td><td>"+entry.getValue().getMoney()+"</td><td><a href =managerEditUser?id=" + entry.getKey() + ">Изменить</a></td><td><form method=\"post\" action=\"DeleteUser\" style=\"display:inline;\">" +
    " <input type=\"hidden\" name=\"id\" value="+entry.getKey()+">"+
                "    <input type=\"submit\" value=\"Удалить\">" +
                "</form></td></tr>");
    }
    out.println("</table>");
%>
<a href="workManager.jsp">Назад в управление магазином</a><br>
<a href="homeManager.jsp">Перейти в личный профиль</a><br>
<a href="index.html">Домой</a>
</body>
</html>
