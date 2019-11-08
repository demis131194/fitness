<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="index.welcome" bundle="${rb}" var="welcome"/>
<fmt:message key="heder.sign.in" bundle="${rb}" var="login"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.home" bundle="${rb}" var="home"/>
<fmt:message key="project.contacts" bundle="${rb}" var="contacts"/>
<fmt:message key="project.about" bundle="${rb}" var="about"/>
<fmt:message key="project.coments" bundle="${rb}" var="coments"/>
<fmt:message key="main.welcome" bundle="${rb}" var="mainWelcome"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-lg-10 offset-lg-1">
                <div class="main-navigation">
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">${home}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">${contacts}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">${about}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">${coments}</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="main-section">
            <div class="row">
                <div class="col-lg-10 offset-lg-1">
                    <h2>${mainWelcome}</h2>
                    <p>Пострадавшего зовут Антон, a он работает в системе образования — заместителем директора в доме творчества. В День народного единства, 4 ноября 2019 г. Антон с товарищем, который работает преподавателем, возвращался домой. Мужчины шли из парка ДК Алюминщик, где проходил праздник, в организации которого они принимали участие.</p>
                    <p>Уже возле подъезда их нагнал некий мужчина и стал утверждать, что они ему повредили автомобиль, указывая в сторону своего авто. Поскольку товарищи шли совсем с другой стороны, Антон сказал незнакомцу, что тот обознался, и повернулся, чтобы идти к двери подъезда. В это время автовладелец неожиданно нанёс Антону удар в лицо, и он потерял сознание. Примечательно, что агрессор не позволил товарищу пострадавшего оказать ему первую помощь и увёл его, оставив человека без сознания лежать на земле.</p>
                </div>
            </div>
        </div>

    </div>

</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
