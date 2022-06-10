<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
</head>
<body>
<h3>Edit User</h3>
<form action="edit" method="post">
    <input type="hidden" value="${editUser.id}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${editUser.name}" /><br><br>
    <label>Email</label><br>
    <input name="email" value="${editUser.email}"  /><br><br>
    <label>Country</label><br>
    <input name="country" value="${editUser.country}"  /><br><br>
    <label>Money</label><br>
    <input name="money" value="${editUser.money}"  /><br><br>
    <input type="submit" value="Send" />


</form>
<div>
<a href="index.html">home</a><br>
<a href="viewBasket">basket</a></br>
</div>
</body>
</html>