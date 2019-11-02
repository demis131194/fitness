<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="index.welcome" bundle="${rb}" var="welcome"/>
<fmt:message key="heder.sign.in" bundle="${rb}" var="login"/>
<fmt:message key="index.project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="main.orders" bundle="${rb}" var="orders"/>
<fmt:message key="main.users" bundle="${rb}" var="users"/>

<html>
<head>
    <title>${projectName}</title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<h1>ADMIN MAIN</h1>
<ul>
    <li><a href="${pageContext.request.contextPath}/jsp/pages/admin/welcome.jsp">${welcome}</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=Find_All_Orders">${orders}</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=Find_All_Trainers">${users}</a></li>
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
