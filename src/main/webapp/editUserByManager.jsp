<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
</head>
<body>
<h3>Менеджер редактирует пользователя</h3>
<form action="managerEditUser" method="post">
    <input type="hidden" value="${editedUserByManager.id}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${editedUserByManager.name}" /><br><br>
    <label>Email</label><br>
    <input name="email" value="${editedUserByManager.email}"  /><br><br>
    <label>Country</label><br>
    <input name="country" value="${editedUserByManager.country}"  /><br><br>
    <label>Money</label><br>
    <input name="money" value="${editedUserByManager.money}"  /><br><br>
    <input type="submit" value="Отправить " />


</form>
<div>
<a href="index.html">Домой</a><br>
<a href="customerManagement.jsp">Обратно в меню пользователя </a></br>
</div>
</body>
</html>