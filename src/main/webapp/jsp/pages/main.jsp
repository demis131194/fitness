<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="index.welcome" bundle="${rb}" var="welcome"/>
<fmt:message key="index.login" bundle="${rb}" var="login"/>

<html>
<head>
    <title><fmt:message key="index.project.name" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<ul>
    <c:if test="${authorization==true}" >
        <li><a href="${pageContext.request.contextPath}/jsp/pages/welcome.jsp">${welcome}</a></li>
    </c:if>
    <c:if test="${authorization!=true}" >
        <li><a href="${pageContext.request.contextPath}/jsp/pages/login.jsp">${login}</a></li>
    </c:if>
    <li><a href="${pageContext.request.contextPath}/controller?command=Find_All_Orders"><fmt:message key="index.orders" bundle="${rb}"/></a></li>
    <c:if test="${authorization==true}" >
        <li><a href="${pageContext.request.contextPath}/controller?command=Logout"><fmt:message key="index.logout" bundle="${rb}"/></a></li>
    </c:if>
</ul>
<hr/>
<hr/>
<hr/>
${pageContext.request.contextPath}
<hr/>
${cookie}
<hr/>
<c:forEach var="key" items="${header.keySet()}">
    -->${key} : ${header.get(key)}
    <br/>
</c:forEach>

<hr/>
${requestScope}
<hr/>
ID сессии : ${pageContext.request.session.id}<br/>
<hr/>
<c:forEach var="skey" items="${sessionScope.keySet()}">
    -->${skey} : ${sessionScope.get(skey)}
    <br/>
</c:forEach>
<hr/>
Время создания сессии в мсек : ${pageContext.request.session.creationTime}<br/>
<hr/>
Время последнего доступа к сессии : ${pageContext.request.session.lastAccessedTime}<br/>
<hr/>
Имя сервлета : ${pageContext.servletConfig.servletName}
</body>
</html>
