<%--import java.util.List;--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.onlineshop.utils.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>View basket</title>
</head>
<body>


<p><b>Просмотр корзины </b></p>

<%--<%
    Map<Integer, Product> mapOfProduct = (Map<Integer, Product>) session.getAttribute("findedMapProduct");
    out.println("<ui>");
    for (Map.Entry <Integer, Product> entry : mapOfProduct.entrySet()){
        out.println("<li>" + entry.getValue().getName() + entry.getValue().getPrice()+"<a href =./addToBasket?id=" + entry.getKey() + ">add to basket</a>" +"</li>");
        out.println("</ui>");
    }
%>--%>
<%--<a href="index.html">home</a><br>--%>


<%--<a href=\"./addToBasket?name="--%>

<%
    List<Product> listOfProduct = (List<Product>) session.getAttribute("myBasket");
    int total = 0;
    out.println("<table>  <tr>\n" +
            "    <th>Название</th>\n" +
            "    <th>Категория</th>\n" +
            "    <th>Цена</th>\n" +
            "  </tr>");
    for (int i=0; i<listOfProduct.size();i++){
        Product p = listOfProduct.get(i);
        total=p.getPrice()+total;
        out.println("  <tr>\n" +
                "    <td>"+p.getName()+"</td>\n" +
                "    <td>"+p.getCategory()+"</td>\n" +
                "    <td>"+p.getPrice()+"</td>\n" +
                "    <td> <p><a href=\"./remove?id="+i +"\">"+"X"+"</a></p></td>\n" +
                "  </tr>");

    }
    session.setAttribute("TotalPrice", total);
    out.println("<tr><b>Итого = </b>"+total+"</tr></table>");
%>
<div>
    <form action="BuyProduct" method="get">
        <br>
        <input type="submit" value="Купить">
    </form>
</div></br>
<a href="index.html">Домой</a></br>
<a href="Login">Личная страница</a>
</body>
</html>

