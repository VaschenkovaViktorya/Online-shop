<%@ page import="java.util.Date" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование профиля</title>
</head>
<body>
<h3>Внести изменения</h3>
<form action="edit" method="post">
    <input type="hidden" value="${editUser.id}" name="id" />
    <label>Имя</label><br>
    <input name="name" value="${editUser.name}" /><br><br>
    <label>Email</label><br>
    <input name="email" value="${editUser.email}"  /><br><br>
    <label>Страна</label><br>
    <input name="country" value="${editUser.country}"  /><br><br>
    <label>Баланс</label><br>
    <input name="money" value="${editUser.money}"  /><br><br>
    <input type="submit" value="Изменить" />


</form>
<div>
<a href="index.html">Домой</a><br>
<a href="viewBasket">Корзина</a></br>
</div>
</body>
</html>