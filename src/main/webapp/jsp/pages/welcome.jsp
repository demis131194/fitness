<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="welcome.title" bundle="${rb}" var="userCreatedTitle"/>
<fmt:message key="welcome.text.registered" bundle="${rb}" var="welcomTextReg"/>
<fmt:message key="welcome.text.role" bundle="${rb}" var="welcomTextRole"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
    <meta http-equiv="refresh" content="6;${pageContext.request.contextPath}/index.jsp">
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <div class="main-section">
            <div class="welcome">
                <div class="col-lg-10 offset-lg-1">
                    <h2 class="main-section-title">${userCreatedTitle}</h2>
                    <p>${welcomTextReg} ${sessionScope.userName} ${sessionScope.userLastName}</p>
                    <p>${welcomTextRole} ${sessionScope.userRole}</p>
                    <a href="${pageContext.request.contextPath}/index.jsp">${home}</a>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>