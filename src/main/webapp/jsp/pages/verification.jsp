<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.verification.title" bundle="${rb}" var="verificationTitle"/>
<fmt:message key="project.verification.text.success" bundle="${rb}" var="verificationTextSuccess"/>
<fmt:message key="project.verification.text.fail" bundle="${rb}" var="verificationTextFail"/>

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
                        <h2>${verificationTitle}</h2>
                    </div>
                    <c:choose>
                        <c:when test="${requestScope.verification}">
                            <p>${verificationTextSuccess}</p>
                        </c:when>
                        <c:otherwise>
                            <p>${verificationTextFail}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
