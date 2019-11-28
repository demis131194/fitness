<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.contacts" bundle="${rb}" var="projectContacts"/>
<fmt:message key="project.prices" bundle="${rb}" var="prices"/>
<fmt:message key="project.prices.day" bundle="${rb}" var="day"/>
<fmt:message key="project.prices.week" bundle="${rb}" var="week"/>
<fmt:message key="project.prices.month" bundle="${rb}" var="month"/>
<fmt:message key="project.prices.season" bundle="${rb}" var="season"/>
<fmt:message key="project.prices.half.year" bundle="${rb}" var="halfYear"/>
<fmt:message key="user.box.fio" bundle="${rb}" var="fio"/>
<fmt:message key="last.name" bundle="${rb}" var="lastName"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <c:import url="/jsp/fragment/navigation.jsp"/>

        <div class="main-section">
            <div class="row">
                <div class="col-lg-10 offset-lg-1">
                    <div class="main-title">
                        <h2>${prices}</h2>
                    </div>
                    <div>
                        <span class="d-block">${day}</span>
                        <span class="d-block">${week}</span>
                        <span class="d-block">${month}</span>
                        <span class="d-block">${season}</span>
                        <span class="d-block">${halfYear}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
