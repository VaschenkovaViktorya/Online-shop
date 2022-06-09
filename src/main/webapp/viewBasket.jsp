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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View basket</title>
</head>
<body>


<p><b>view basket</b></p> <br/>
<a href="index.html">home</a>
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
<div>
    <form action="BuyProduct" method="get">
        <br>
        <input type="submit" value="Buy">
    </form>
</div>
<%
    List<Product> listOfProduct = (List<Product>) session.getAttribute("myBasket");
//    out.println("<ui>");
//    for (Product s : listOfProduct) {
//        out.println("<li>" + s.getName() + s.getPrice()+"<a href =./addToBasket?id=" + s.getId() + ">X</a>" +"</li>");
//        out.println("</ui>");
//    }
    int total = 0;
    out.println("<table>  <tr>\n" +
            "    <th>Name</th>\n" +
            "    <th>Category</th>\n" +
            "    <th>Price</th>\n" +
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
    out.println("<tr><b>Total price = </b>"+total+"</tr></table>");
%>
<p><b>view basket</b></p> <br/>
<a href="Login">Personal account</a>
</body>
</html>

