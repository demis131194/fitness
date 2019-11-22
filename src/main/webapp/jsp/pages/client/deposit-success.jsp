<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="account.menu.profile" bundle="${rb}" var="profile"/>
<fmt:message key="deposit.success.title" bundle="${rb}" var="depositSuccessTitle"/>
<fmt:message key="deposit.success.text" bundle="${rb}" var="depositSuccessText"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <meta http-equiv="refresh" content="5;${pageContext.request.contextPath}/jsp/pages/client/account/profile.jsp">
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <div class="main-section">
            <div class="order-created">
                <div class="col-lg-10 offset-lg-1">
                    <h2 class="order-created-title">${depositSuccessTitle}</h2>
                    <p>${depositSuccessText} ${requestScope.cashAmount}</p>
                    <a href="${pageContext.request.contextPath}/jsp/pages/client/account/profile.jsp">${profile}</a>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
