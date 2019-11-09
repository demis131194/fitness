<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>

<fmt:message key="admin.trainers.title" bundle="${rb}" var="trainers"/>
<fmt:message key="trainer.id" bundle="${rb}" var="trainerId"/>
<fmt:message key="user.box.fio" bundle="${rb}" var="fio"/>
<fmt:message key="last.name" bundle="${rb}" var="lastName"/>
<fmt:message key="register.date" bundle="${rb}" var="regDate"/>
<fmt:message key="phone" bundle="${rb}" var="phone"/>

<html>
<head>
    <title>${trainers}</title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<section>
    <h3><a href="${pageContext.request.contextPath}">${home}</a></h3>
    <hr/>
    <h2>${trainers}</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>${trainerId}</th>
            <th>${fio}</th>
            <th>${lastName}</th>
            <th>${regDate}</th>
            <th>${phone}</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.trainers}" var="trainer">
            <jsp:useBean id="trainer" type="by.epam.fitness.model.user.Trainer"/>
            <tr bgcolor="${trainer.active ? "green": "red"}">
                <td>${trainer.id}</td>
                <td>${trainer.name}</td>
                <td>${trainer.lastName}</td>
                <td>${trainer.registerDateTime}</td>
                <td>${trainer.phone}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
