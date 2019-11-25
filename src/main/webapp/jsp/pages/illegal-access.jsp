<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="security.title" bundle="${rb}" var="securityTitle"/>
<fmt:message key="security.text" bundle="${rb}" var="securityText"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <c:import url="/jsp/fragment/navigation.jsp"/>
        <div class="main-section">
            <div class="row">
                <div class="col-lg-10 offset-lg-1">
                    <h2 class="main-section-title">${securityTitle}</h2>
                    <p class="main-section-text">${securityText}</p>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
