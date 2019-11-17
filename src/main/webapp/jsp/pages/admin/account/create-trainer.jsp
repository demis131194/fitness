<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="create.trainer.form.title" bundle="${rb}" var="formTitle"/>
<fmt:message key="create.trainer.form.name" bundle="${rb}" var="formName"/>
<fmt:message key="create.trainer.form.last.name" bundle="${rb}" var="formLastName"/>
<fmt:message key="create.trainer.form.phone" bundle="${rb}" var="formPhone"/>
<fmt:message key="create.trainer.form.submit" bundle="${rb}" var="formSubmit"/>
<fmt:message key="form.login" bundle="${rb}" var="formLogin"/>
<fmt:message key="form.login.placeholder" bundle="${rb}" var="loginPlaceholder"/>
<fmt:message key="form.password" bundle="${rb}" var="formPassword"/>
<fmt:message key="form.password.placeholder" bundle="${rb}" var="passwordPlaceholder"/>
<fmt:message key="form.repeat.password.placeholder" bundle="${rb}" var="passwordRepeatPlaceholder"/>


<html>
<head>
    <title>${title}</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>
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
                    <div class="create-order-title">
                        <h2>${formTitle}</h2>
                    </div>
                    <div class="create-trainer-form">
                        <form name="createTrainerForm" action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" value="CREATE_NEW_TRAINER">
                            <div class="form-group row">
                                <label for="input-login" class="col-lg-2 col-form-label">${formLogin}</label>
                                <div class="col-lg-10">
                                    <input type="text" name="userLogin" class="form-control" id="input-login" placeholder="${loginPlaceholder}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="input-name" class="col-lg-2 col-form-label">${formName}</label>
                                <div class="col-lg-10">
                                    <input type="text" name="userName" class="form-control" id="input-name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="input-last-name" class="col-lg-2 col-form-label">${formLastName}</label>
                                <div class="col-lg-10">
                                    <input type="text" name="userLastName" class="form-control" id="input-last-name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="input-phone" class="col-lg-2 col-form-label">${formPhone}</label>
                                <div class="col-lg-10">
                                    <input type="text" name="userPhone" class="form-control" id="input-phone">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="input-password" class="col-lg-2 col-form-label">${formPassword}</label>
                                <div class="col-lg-10">
                                    <input type="password" name="userPassword" class="form-control" id="input-password" placeholder="${passwordPlaceholder}">
                                </div>
                            </div>
                            <div class="form-group row in-line">
                                <label for="input-repeat-password" class="col-lg-2 col-form-label">${formPassword}</label>
                                <div class="col-lg-10">
                                    <input type="password" name="repeatPassword" class="form-control" id="input-repeat-password" placeholder="${passwordRepeatPlaceholder}">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">${formSubmit}</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
