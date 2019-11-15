<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="account.menu.profile" bundle="${rb}" var="profile"/>
<fmt:message key="account.menu.orders" bundle="${rb}" var="orders"/>
<fmt:message key="account.menu.create.order" bundle="${rb}" var="newOrder"/>
<fmt:message key="account.menu.create.comment" bundle="${rb}" var="createComment"/>
<fmt:message key="account.menu.deposit" bundle="${rb}" var="deposit"/>

<html>
<head>
    <title>Account menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="menu-bar">
    <ul>
        <c:choose>
            <c:when test="${sessionScope.userRole == 'ADMIN'}">
                <li><a href="${pageContext.request.contextPath}/jsp/pages/admin/account/profile.jsp">${profile}</a></li>
                <li><a href="#">Новости</a></li>
                <li><a href="#">Контакты</a></li>
                <li><a href="#">О наc</a></li>
            </c:when>
            <c:when test="${sessionScope.userRole == 'TRAINER'}">
                <li><a href="${pageContext.request.contextPath}/jsp/pages/trainer/account/profile.jsp">${profile}</a></li>
                <li><a href="#">Новости</a></li>
                <li><a href="#">Контакты</a></li>
                <li><a href="#">О наc</a></li>
            </c:when>
            <c:when test="${sessionScope.userRole == 'CLIENT'}">
                <li><a href="${pageContext.request.contextPath}/jsp/pages/client/account/profile.jsp">${profile}</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=find_all_orders_by_client">${orders}</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=show_new_order_page">${newOrder}</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=show_new_comment_page">${createComment}</a></li>
                <li><a href="#">${deposit}</a></li>
            </c:when>
        </c:choose>
    </ul>
</div>

</body>
</html>
