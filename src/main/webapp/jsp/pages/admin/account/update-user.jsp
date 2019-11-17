<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="update.user.title" bundle="${rb}" var="title"/>
<fmt:message key="update.user.form.trainer.name" bundle="${rb}" var="trainerName"/>
<fmt:message key="update.user.form.trainer.last.name" bundle="${rb}" var="trainerLastName"/>
<fmt:message key="update.user.form.client.name" bundle="${rb}" var="clientName"/>
<fmt:message key="update.user.form.client.last.name" bundle="${rb}" var="clientLastName"/>
<fmt:message key="update.user.form.phone" bundle="${rb}" var="phone"/>
<fmt:message key="update.user.form.submit" bundle="${rb}" var="formSubmit"/>
<fmt:message key="update.user.form.discount" bundle="${rb}" var="discount"/>
<fmt:message key="update.user.form.discount.level" bundle="${rb}" var="discountLevel"/>

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
        <div class="row">
            <div class="col-lg-2">
                <c:import url="/jsp/fragment/account-menu.jsp"/>
            </div>

            <div class="col-lg-10">
                <div class="main-section">
                    <div class="order-title">
                        <h2>${title}</h2>
                    </div>

                    <c:choose>
                        <c:when test="${requestScope.user.role == 'TRAINER'}">
                            <div class="update-user">
                                <c:set var="trainer" value="${requestScope.user}"/>
                                <jsp:useBean id="trainer" type="by.epam.fitness.model.user.Trainer"/>

                                <form name="updateTrainerForm" action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="UPDATE_TRAINER_BY_ADMIN">
                                    <input type="hidden" name="userId" value="${trainer.id}">

                                    <div class="form-group">
                                        <label for="input-trainer-name">${trainerName}</label>
                                        <input name="userName" class="form-control" id="input-trainer-name" value="${trainer.name}"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="input-trainer-last-name">${trainerLastName}</label>
                                        <input name="userLastName" class="form-control" id="input-trainer-last-name" value="${trainer.lastName}"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="input-trainer-phone">${phone}</label>
                                        <input name="userPhone" class="form-control" id="input-trainer-phone" value="${trainer.phone}"/>
                                    </div>

                                    <button type="submit" class="btn btn-primary">${formSubmit}</button>
                                </form>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.user.role == 'CLIENT'}">
                            <div class="update-user">
                                <c:set var="client" value="${requestScope.user}"/>
                                <jsp:useBean id="client" type="by.epam.fitness.model.user.Client"/>

                                <form name="updateTrainerForm" action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="UPDATE_CLIENT_BY_ADMIN">
                                    <input type="hidden" name="userId" value="${client.id}">

                                    <div class="form-group">
                                        <label for="input-client-name">${clientName}</label>
                                        <input name="userName" class="form-control" id="input-client-name" value="${client.name}"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="input-client-last-name">${clientLastName}</label>
                                        <input name="userLastName" class="form-control" id="input-client-last-name" value="${client.lastName}"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="input-client-phone">${phone}</label>
                                        <input name="userPhone" class="form-control" id="input-client-phone" value="${client.phone}"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="input-client-discount">${discount}</label>
                                        <input name="userDiscount" class="form-control" id="input-client-discount" value="${client.discount}"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="input-client-discount-level">${discountLevel}</label>
                                        <input name="userDiscountLevel" class="form-control" id="input-client-discount-level" value="${client.discountLevel}"/>
                                    </div>

                                    <button type="submit" class="btn btn-primary">${formSubmit}</button>
                                </form>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
