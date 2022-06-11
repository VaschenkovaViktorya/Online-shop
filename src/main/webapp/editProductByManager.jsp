<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование продукта</title>
</head>
<body>
<h3>Менеджер редактирует продукты</h3>
<form action="managerEditProduct" method="post">
    <input type="hidden" value="${editedProductByManager.id}" name="id" />
    <label>Название</label><br>
    <input name="name" value="${editedProductByManager.name}" /><br><br>
    <label>Категория</label><br>
    <input name="category" value="${editedProductByManager.category}"  /><br><br>
    <label>Цена</label><br>
    <input name="price" value="${editedProductByManager.price}"  /><br><br>
    <label>Количество на скдладе</label><br>
    <input name="quantity" value="${editedProductByManager.quantity}"  /><br><br>
    <input type="submit" value="Отправить " />


</form>
<div>
    <a href="productManagement.jsp">Назад к товарам</a><br>
    <a href="index.html">Домой</a><br>
    <a href="workManager.jsp">Перейти в меню управления магазином</a><br>
    <a href="homeManager.jsp">Перейти в личный профиль</a><br>

</div>
</body>
</html>