<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Orders List</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Orders</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Order id</th>
            <th>User id</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Price</th>
            <th>isPayed</th>
            <th>Description</th>
        </tr>
        </thead>
        <c:forEach items="${orders}" var="order">
            <jsp:useBean id="order" type="by.epam.fitness.model.Order"/>
            <tr>
                <td>${order.id}</td>
                <td>${order.userId}</td>
                <td>${order.startDate}</td>
                <td>${order.endDate}</td>
                <td>${order.price}</td>
                <td>${order.payed}</td>
                <td>${order.description}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
