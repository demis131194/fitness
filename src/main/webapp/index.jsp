<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.get('locale')}"/>
<fmt:setBundle basename="message" var="rb"/>
<c:set var="user" value="${sessionScope.get('user')}"/>

<html>
<head>
    <title><fmt:message key="index.project.name" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<ul>
    <c:if test="${user!=null}" >
        <li><a href="jsp/pages/welcome.jsp"><fmt:message key="index.welcome" bundle="${rb}"/></a></li>
    </c:if>
    <c:if test="${user==null}" >
        <li><a href="jsp/pages/login.jsp"><fmt:message key="index.login" bundle="${rb}"/></a></li>
    </c:if>
    <li><a href="test?command=Find_All_Orders"><fmt:message key="index.orders" bundle="${rb}"/></a></li>
    <c:if test="${user!=null}" >
        <li><a href="${pageContext.request.contextPath}/test?command=Logout"><fmt:message key="index.logout" bundle="${rb}"/></a></li>
    </c:if>
</ul>
</body>
</html>