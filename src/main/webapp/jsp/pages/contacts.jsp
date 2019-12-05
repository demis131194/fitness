<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.contacts" bundle="${rb}" var="projectContacts"/>
<fmt:message key="user.box.fio" bundle="${rb}" var="fio"/>
<fmt:message key="last.name" bundle="${rb}" var="lastName"/>
<fmt:message key="phone" bundle="${rb}" var="phone"/>
<fmt:message key="project.place" bundle="${rb}" var="place"/>
<fmt:message key="project.place.first" bundle="${rb}" var="place1"/>
<fmt:message key="project.place.second" bundle="${rb}" var="place2"/>

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
                        <h2>${projectContacts}</h2>
                    </div>
                    <div>
                        <span>
                            ${place}
                        </span>
                        <ul>
                            <li>${place1}</li>
                            <li>${place2}</li>
                        </ul>
                    </div>
                    <div>
                        <span class="d-block">${phone} - 23234242</span>
                        <span class="d-block">${phone} - 23234242</span>
                        <span class="d-block">${phone} - 23234242</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
