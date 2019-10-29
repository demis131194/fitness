<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="orders.order.id" bundle="${rb}" var="orderId"/>
<fmt:message key="orders.client.id" bundle="${rb}" var="userId"/>
<fmt:message key="orders.trainer.id" bundle="${rb}" var="trainerId"/>
<fmt:message key="orders.register.date" bundle="${rb}" var="registerDate"/>
<fmt:message key="orders.description" bundle="${rb}" var="description"/>

<html>
<head>
    <title><fmt:message key="orders.title" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<section>
    <h3><a href="${pageContext.request.contextPath}/jsp/pages/main.jsp"><fmt:message key="project.home" bundle="${rb}"/></a></h3>
    <hr/>
    <h2><fmt:message key="orders.orders" bundle="${rb}"/></h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>${orderId}</th>
            <th>${userId}</th>
            <th>${trainerId}</th>
            <th>${registerDate}</th>
            <th>${description}</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.orders}" var="order">
            <jsp:useBean id="order" type="by.epam.fitness.model.Order"/>
            <tr bgcolor="${order.active ? "green": "red"}">
                <td>${order.id}</td>
                <td>${order.clientId}</td>
                <td>${order.trainerId}</td>
                <td>${order.registerDate}</td>
                <td>${order.description}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
