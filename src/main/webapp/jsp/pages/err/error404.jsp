<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>

<html>
<head>
    <title>Error 404</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<div class="text-center">
    <div>
        <h1><a href="${pageContext.request.contextPath}/index.jsp">${home}</a></h1>
    </div>
    <div>
        <img src="${pageContext.request.contextPath}/image/error/404.jpg" alt="404 error image" class="error-image">
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
