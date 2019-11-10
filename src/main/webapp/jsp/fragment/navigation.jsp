<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.contacts" bundle="${rb}" var="projectContacts"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>
<fmt:message key="project.navigation.contacts" bundle="${rb}" var="contacts"/>
<fmt:message key="project.navigation.about" bundle="${rb}" var="about"/>
<fmt:message key="project.navigation.comments" bundle="${rb}" var="coments"/>
<fmt:message key="project.navigation.account" bundle="${rb}" var="account"/>
<fmt:message key="user.box.fio" bundle="${rb}" var="fio"/>
<fmt:message key="user.box.role" bundle="${rb}" var="role"/>
<fmt:message key="user.box.sign.in" bundle="${rb}" var="signIn"/>
<fmt:message key="user.box.sign.up" bundle="${rb}" var="signUp"/>
<fmt:message key="user.box.logout" bundle="${rb}" var="logOut"/>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
</head>
<body>

<div class="row align-items-center">
    <%--            Navigation--%>
    <div class="col-lg-6">
        <div class="main-navigation">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}">${home}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/jsp/pages/contacts.jsp">${contacts}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/jsp/pages/about.jsp">${about}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=find_all_comments">${coments}</a>
                </li>
                <c:if test="${sessionScope.authorization}">
                    <li class="nav-item">
                        <c:choose>
                            <c:when test="${sessionScope.userRole == 'CLIENT'}">
                                <a class="nav-link" href="${pageContext.request.contextPath}/jsp/pages/client/account/profile.jsp">${account}</a>
                            </c:when>
                            <c:when test="${sessionScope.userRole == 'TRAINER'}">
                                <a class="nav-link" href="${pageContext.request.contextPath}/jsp/pages/trainer/account/profile.jsp">${account}</a>
                            </c:when>
                            <c:when test="${sessionScope.userRole == 'ADMIN'}">
                                <a class="nav-link" href="${pageContext.request.contextPath}/jsp/pages/admin/account/profile.jsp">${account}</a>
                            </c:when>
                        </c:choose>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
    <%--            User windows --%>
    <div class="col-lg-4 offset-lg-2">
        <div class="main-box">
            <c:choose >
                <c:when test="${sessionScope.authorization}">
                    <div>
                        <strong>${fio} : ${sessionScope.userName} ${sessionScope.userLastName}</strong>
                    </div>
                    <div>
                        <strong>${role} : ${sessionScope.userRole}</strong>
                    </div>
                    <div>
                        <strong><a href="${pageContext.request.contextPath}/controller?command=Logout">${logOut}</a></strong>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <strong>${role} : Guest</strong>
                    </div>
                    <div>
                        <strong>
                            <a href="${pageContext.request.contextPath}/jsp/pages/sign-in.jsp">${signIn}</a>
                            <a href="${pageContext.request.contextPath}/jsp/pages/sign-up.jsp">${signUp}</a>
                        </strong>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>
