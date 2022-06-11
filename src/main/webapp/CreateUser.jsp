<%--
  Created by IntelliJ IDEA.
  User: gorvi
  Date: 11.06.2022
  Time: 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Создание нового пользователя</title>
</head>
<body>
<form action="createNewUser" method="post">
    <strong>Email ID</strong>:<input type="text" name="email"><br>
    <strong>Password</strong>:<input type="password" name="password"><br>
    <strong>Name</strong>:<input type="text" name="name"><br>
    <strong>Country</strong>:<input type="text" name="country"><br>
    <input type="submit" value="Создать">
</form>
<a href="index.html">Домой</a><br>
<a href="workManager.jsp">Управление магазином</a><br>
<a href="customerManagement.jsp">Назад в управление пользователями</a><br>

</body>
</html>
