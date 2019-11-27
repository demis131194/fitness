<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="header.header" bundle="${rb}" var="header"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="header.locale" bundle="${rb}" var="locale"/>


<html>
<head>
    <title>${header}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
</head>
<body>
<%-- Header--%>
<div class="header">
    <div class="row">
        <%--Locale--%>
        <div class="col-lg-2">
            <div class="dropdown header-locale">
                <button class="btn dropdown-toggle btn-sm header-locale-button btn-light" type="button" id="dropdown-menu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}/image/icons/icon-locale.png" alt="Locale">
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdown-menu">
                    <a class="dropdown-item btn-sm" href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_EN">EN</a>
                    <a class="dropdown-item btn-sm" href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU">RU</a>
                </div>
            </div>
        </div>
<%--            Project Name--%>
        <div class="col-lg-2 offset-lg-3">
            <div class="header-title">
                <h1>${projectName}</h1>
            </div>

        </div>
    </div>
</div>

</body>
</html>

