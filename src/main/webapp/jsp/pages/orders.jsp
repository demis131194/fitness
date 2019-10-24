<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.get('locale')}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<html>
<head>
    <title><fmt:message key="orders.title" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<section>
    <h3><a href="${pageContext.request.contextPath}"><fmt:message key="project.home" bundle="${rb}"/></a></h3>
    <hr/>
    <h2><fmt:message key="orders.orders" bundle="${rb}"/></h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="orders.order.id" bundle="${rb}"/></th>
            <th><fmt:message key="orders.user.id" bundle="${rb}"/></th>
            <th><fmt:message key="orders.startDate" bundle="${rb}"/></th>
            <th><fmt:message key="orders.endDate" bundle="${rb}"/></th>
            <th><fmt:message key="orders.price" bundle="${rb}"/></th>
            <th><fmt:message key="orders.isPayed" bundle="${rb}"/></th>
            <th><fmt:message key="orders.description" bundle="${rb}"/></th>
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
