<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="index.welcome" bundle="${rb}" var="welcome"/>
<fmt:message key="user.box.sign.in" bundle="${rb}" var="login"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>
<fmt:message key="project.navigation.contacts" bundle="${rb}" var="contacts"/>
<fmt:message key="project.navigation.about" bundle="${rb}" var="about"/>
<fmt:message key="project.navigation.comments" bundle="${rb}" var="coments"/>
<fmt:message key="main.welcome" bundle="${rb}" var="mainWelcome"/>
<fmt:message key="user.box.fio" bundle="${rb}" var="fio"/>
<fmt:message key="last.name" bundle="${rb}" var="lastName"/>
<fmt:message key="user.box.role" bundle="${rb}" var="role"/>
<fmt:message key="user.box.sign.in" bundle="${rb}" var="signIn"/>
<fmt:message key="user.box.sign.up" bundle="${rb}" var="signUp"/>
<fmt:message key="user.box.logout" bundle="${rb}" var="logOut"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <c:import url="/jsp/fragment/navigation.jsp"/>
        <div class="main-section">
            <div class="row">
                <div class="col-lg-10 offset-lg-1">
                    <h2 class="main-section-title">${mainWelcome}</h2>
                    <p class="main-section-text">Пострадавшего зовут Антон, a он работает в системе образования — заместителем директора в доме творчества. В День народного единства, 4 ноября 2019 г. Антон с товарищем, который работает преподавателем, возвращался домой. Мужчины шли из парка ДК Алюминщик, где проходил праздник, в организации которого они принимали участие.</p>
                    <p class="main-section-text">Уже возле подъезда их нагнал некий мужчина и стал утверждать, что они ему повредили автомобиль, указывая в сторону своего авто. Поскольку товарищи шли совсем с другой стороны, Антон сказал незнакомцу, что тот обознался, и повернулся, чтобы идти к двери подъезда. В это время автовладелец неожиданно нанёс Антону удар в лицо, и он потерял сознание. Примечательно, что агрессор не позволил товарищу пострадавшего оказать ему первую помощь и увёл его, оставив человека без сознания лежать на земле.</p>
                </div>
            </div>
        </div>

    </div>

</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
