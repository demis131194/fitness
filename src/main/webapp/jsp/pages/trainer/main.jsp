<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="index.welcome" bundle="${rb}" var="welcome"/>
<fmt:message key="user.box.sign.in" bundle="${rb}" var="login"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="main.orders" bundle="${rb}" var="orders"/>
<fmt:message key="main.users" bundle="${rb}" var="users"/>

<html>
<head>
    <title>${projectName}</title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<h1>TRAINER MAIN</h1>
<ul>
    <li><a href="${pageContext.request.contextPath}/jsp/pages/trainer/welcome.jsp">${welcome}</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=Find_All_Orders">${orders}</a></li>
</ul>
</body>
</html>
