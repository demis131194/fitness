<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="header.header" bundle="${rb}" var="header"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="header.locale" bundle="${rb}" var="locale"/>
<fmt:message key="name" bundle="${rb}" var="name"/>
<fmt:message key="last.name" bundle="${rb}" var="lastName"/>
<fmt:message key="header.role" bundle="${rb}" var="role"/>
<fmt:message key="heder.sign.in" bundle="${rb}" var="signIn"/>
<fmt:message key="heder.sign.up" bundle="${rb}" var="signUp"/>
<fmt:message key="header.logout" bundle="${rb}" var="logOut"/>

<html>
<head>
    <title>Footer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
</head>
<body>
<%-- Header--%>
<div class="footer">
    <div class="row">
        <div class="col-lg-12">

        </div>
    </div>
</div>

</body>
</html>
