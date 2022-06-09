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
    <label>Price</label><br>
    <input name="email" value="${editUser.email}"  /><br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>