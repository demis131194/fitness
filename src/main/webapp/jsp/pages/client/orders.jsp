<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="orders.order.id" bundle="${rb}" var="orderId"/>
<fmt:message key="client.id" bundle="${rb}" var="clientId"/>
<fmt:message key="trainer.id" bundle="${rb}" var="trainerId"/>
<fmt:message key="register.date" bundle="${rb}" var="registerDate"/>
<fmt:message key="orders.description" bundle="${rb}" var="description"/>
<fmt:message key="project.home" bundle="${rb}" var="home"/>
<fmt:message key="orders.title" bundle="${rb}" var="title"/>
<fmt:message key="orders" bundle="${rb}" var="orders"/>
<fmt:message key="exercises" bundle="${rb}" var="exercises"/>
<fmt:message key="nutrition" bundle="${rb}" var="nutrition"/>
<fmt:message key="start.date" bundle="${rb}" var="startDate"/>
<fmt:message key="end.date" bundle="${rb}" var="endDate"/>
<fmt:message key="price" bundle="${rb}" var="price"/>
<fmt:message key="client.comment" bundle="${rb}" var="clientComment"/>
<fmt:message key="status" bundle="${rb}" var="status"/>
<fmt:message key="accept" bundle="${rb}" var="accept"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<h1>CLIENT WELCOME</h1>
<section>
    <h3><a href="${pageContext.request.contextPath}">${home}</a></h3>
    <hr/>
    <h2>${orders}</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>${orderId}</th>
            <th>${clientId}</th>
            <th>${trainerId}</th>
            <th>${registerDate}</th>
            <th>${exercises}</th>
            <th>${nutrition}</th>
            <th>${startDate}</th>
            <th>${endDate}</th>
            <th>${price}</th>
            <th>${clientComment}</th>
            <th>${status}</th>
            <th>${accept}</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.orders}" var="order">
            <jsp:useBean id="order" type="by.epam.fitness.model.Order"/>
            <tr bgcolor="${order.isActive() ? "green": "red"}">
                <td>${order.id}</td>
                <td>${order.clientId}</td>
                <td>${order.trainerId}</td>
                <td>${order.registerDate}</td>
                <td>${order.exercises}</td>
                <td>${order.nutrition}</td>
                <td>${order.startDate}</td>
                <td>${order.endDate}</td>
                <td>${order.price}</td>
                <td>${order.clientComment}</td>
                <td>${order.orderStatus.name()}</td>
                <td>${order.isAccept()}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
