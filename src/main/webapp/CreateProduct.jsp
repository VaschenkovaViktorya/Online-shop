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
<form action="CreateNewProduct" method="post">
    <strong>Название продукта</strong>:<input type="text" name="product_name"><br>
    <strong>Категория продукта</strong>:<input type="text" name="category"><br>
    <strong>Цена</strong>:<input type="text" name="price"><br>
    <strong>Количество</strong>:<input type="text" name="quantity"><br>
    <input type="submit" value="Создать">
</form>

<a href="index.html">Домой</a><br>
<a href="workManager.jsp">Управление магазином</a><br>
<a href="productManagement.jsp">Назад в управление продуктами</a><br>

</body>
</html>
