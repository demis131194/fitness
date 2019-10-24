<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>

<html>
<head>
    <title><fmt:message key="index.project.name" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<ul>
    <c:if test="${authorization==true}" >
        <li><a href="${pageContext.request.contextPath}/jsp/pages/welcome.jsp"><fmt:message key="index.welcome" bundle="${rb}"/></a></li>
    </c:if>
    <c:if test="${authorization!=true}" >
        <li><a href="${pageContext.request.contextPath}/jsp/pages/login.jsp"><fmt:message key="index.login" bundle="${rb}"/></a></li>
    </c:if>
    <li><a href="${pageContext.request.contextPath}/controller?command=Find_All_Orders"><fmt:message key="index.orders" bundle="${rb}"/></a></li>
    <c:if test="${authorization==true}" >
        <li><a href="${pageContext.request.contextPath}/controller?command=Logout"><fmt:message key="index.logout" bundle="${rb}"/></a></li>
    </c:if>
</ul>
</body>
</html>
