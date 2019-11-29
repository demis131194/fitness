<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="header.header" bundle="${rb}" var="header"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.copyright" bundle="${rb}" var="copyright"/>
<fmt:message key="header.locale" bundle="${rb}" var="locale"/>
<fmt:message key="user.box.fio" bundle="${rb}" var="fio"/>
<fmt:message key="last.name" bundle="${rb}" var="lastName"/>
<fmt:message key="user.box.role" bundle="${rb}" var="role"/>
<fmt:message key="user.box.sign.in" bundle="${rb}" var="signIn"/>
<fmt:message key="user.box.sign.up" bundle="${rb}" var="signUp"/>
<fmt:message key="user.box.logout" bundle="${rb}" var="logOut"/>

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
<%-- Footer--%>
<div class="footer">
    <div class="h-auto">
        <div class="col-lg-12">
            <div class="footer-copyrights">
                <span class="footer-copyrights-text">${copyright}</span>
            </div>
        </div>
    </div>
</div>

</body>
</html>
