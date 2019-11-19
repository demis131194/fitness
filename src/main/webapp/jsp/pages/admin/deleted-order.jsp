<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="order.deleted.title" bundle="${rb}" var="orderDeletedTitle"/>
<fmt:message key="order.deleted.text" bundle="${rb}" var="orderDeletedText"/>
<fmt:message key="account.menu.orders" bundle="${rb}" var="orders"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <meta http-equiv="refresh" content="5;${pageContext.request.contextPath}/controller?command=find_all_orders">
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <div class="main-section">
            <div class="order-deleted">
                <div class="col-lg-10 offset-lg-1">
                    <h2 class="order-deleted-title">${orderDeletedTitle}</h2>
                    <p>${orderDeletedText} ${requestScope.orderId}</p>
                    <a href="${pageContext.request.contextPath}/controller?command=find_all_orders">${orders}</a>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
