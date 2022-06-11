<%@ page import="com.example.onlineshop.utils.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.example.onlineshop.utils.Product" %><%--

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

<p><a href="CreateNewProduct">Создать новый продукт</a></p>
<h2>Список продуктов</h2>
<form action="GetProductByManager" method="get">
    <input type="submit" value="Обновить">
</form>
<%--<%
    Map<Integer, Product> mapOfProduct = (Map<Integer, Product>) session.getAttribute("ListproductsForManager");


    out.println("<ui>");
    for (Map.Entry <Integer, Product> entry : mapOfProduct.entrySet()){
        out.println("<li>" + entry.getValue().getName() +" "+entry.getValue().getCategory()+" "+ entry.getValue().getPrice()+" " +entry.getValue().getQuantity()+"<a href =managerEditProduct?id=" + entry.getKey() + ">Изменить</a>" +"<form method=\"post\" action=\"deleteProduct\" style=\"display:inline;\">\n" +
                "    <input type=\"hidden\" name=\"id\" value="+entry.getKey()+">\n" +
                "    <input type=\"submit\" value=\"Удалить\">\n" +
                "</form></li>");

        out.println("</ui>");
    }
%>--%>
<%
    Map<Integer, Product> mapOfProduct1 = (Map<Integer, Product>) session.getAttribute("ListproductsForManager");
    out.println("<table>  <tr>\n" +
            "    <th>Название</th>\n" +
            "    <th>Категория</th>\n" +
            "    <th>Цена</th>\n" +
            "    <th>Остаток</th>\n" +
            "    <th></th>\n" +
            "    <th></th>\n" +
            "  </tr>");
    for (Map.Entry <Integer, Product> entry : mapOfProduct1.entrySet()){
        out.println("<tr><td>"+entry.getValue().getName()+"</td><td>"+entry.getValue().getCategory()+"</td><td>"+entry.getValue().getPrice()+"</td><td>"+entry.getValue().getQuantity()+"</td><td><a href =managerEditProduct?id=" + entry.getKey() + ">Изменить</a></td><td><form method=\"post\" action=\"deleteProduct\" style=\"display:inline;\">" +
                               "    <input type=\"hidden\" name=\"id\" value="+entry.getKey()+">" +
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