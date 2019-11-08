<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="login.login" bundle="${rb}" var="login"/>
<fmt:message key="login.password" bundle="${rb}" var="password"/>
<fmt:message key="form.login" bundle="${rb}" var="formLogin"/>
<fmt:message key="form.password" bundle="${rb}" var="formPassword"/>
<fmt:message key="form.name" bundle="${rb}" var="formName"/>
<fmt:message key="form.last.name" bundle="${rb}" var="formLastName"/>
<fmt:message key="form.phone" bundle="${rb}" var="formPhone"/>
<fmt:message key="login.button.login" bundle="${rb}" var="buttonLogin"/>
<fmt:message key="project.home" bundle="${rb}" var="home"/>
<fmt:message key="form.login.placeholder" bundle="${rb}" var="loginPlaceholder"/>
<fmt:message key="form.name.placeholder" bundle="${rb}" var="namePlaceholder"/>
<fmt:message key="form.last.name.placeholder" bundle="${rb}" var="lastNamePlaceholder"/>
<fmt:message key="form.password.placeholder" bundle="${rb}" var="passwordPlaceholder"/>
<fmt:message key="form.repeat.password.placeholder" bundle="${rb}" var="passwordRepeatPlaceholder"/>
<fmt:message key="form.phone.placeholder" bundle="${rb}" var="phonePlaceholder"/>
<fmt:message key="form.submit" bundle="${rb}" var="submit"/>


<html>
<head>
    <title>${title}</title>
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
            <div class="col-lg-8 offset-lg-2">
<%--                Form --%>
                <div class="main-form">
                    <div class="main-form-header">
                        <h4><a href="${pageContext.request.contextPath}">${home}</a></h4>
                    </div>
                    <form name="signUpForm" action="${pageContext.request.contextPath}/controller" method="POST">
                        <input type="hidden" name="command" value="sign_up">
                        <div class="form-group row">
                            <label for="input-login" class="col-lg-2 col-form-label">${formLogin}</label>
                            <div class="col-lg-10">
                                <input type="text" name="Login" class="form-control" id="input-login" placeholder="${loginPlaceholder}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="input-name" class="col-lg-2 col-form-label">${formName}</label>
                            <div class="col-lg-10">
                                <input type="text" name="Name" class="form-control" id="input-name" placeholder="${namePlaceholder}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="input-last-name" class="col-lg-2 col-form-label">${formLastName}</label>
                            <div class="col-lg-10">
                                <input type="text" name="LastName" class="form-control" id="input-last-name" placeholder="${lastNamePlaceholder}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="input-phone" class="col-lg-2 col-form-label">${formPhone}</label>
                            <div class="col-lg-10">
                                <input type="text" name="Phone" class="form-control" id="input-phone" placeholder="${phonePlaceholder}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="input-password" class="col-lg-2 col-form-label">${formPassword}</label>
                            <div class="col-lg-10">
                                <input type="password" name="Password" class="form-control" id="input-password" placeholder="${passwordPlaceholder}">
                            </div>
                        </div>
                        <div class="form-group row in-line">
                            <label for="input-repeat-password" class="col-lg-2 col-form-label">${formPassword}</label>
                            <div class="col-lg-10">
                                <input type="password" name="RepeatPassword" class="form-control" id="input-repeat-password" placeholder="${passwordRepeatPlaceholder}">
                            </div>
                        </div>
                        <c:if test="${requestScope.errMessage != null}">
                            <div class="alert alert-danger ">
                                <span><fmt:message key="${requestScope.errMessage}" bundle="${err_rb}"/></span>
                            </div>
                        </c:if>
                        <button type="submit" class="btn btn-primary">${submit}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

<c:import url="/jsp/fragment/footer.jsp"/>

<%--<c:import url="/jsp/fragment/header.jsp"/>--%>
<%--<a href="${pageContext.request.contextPath}"><fmt:message key="project.home" bundle="${rb}"/></a>--%>
<%--<br/>--%>
<%--<br/>--%>
<%--<form name="signUpForm" method="POST" action="${pageContext.request.contextPath}/controller">--%>
<%--    <input type="hidden" name="command" value="sign_up"/>--%>
<%--    <label for="login">${login}</label>--%>
<%--    <br/>--%>
<%--    <input id="login" type="text" name="Login" value="" pattern="\w{4,}"/>--%>
<%--    <br/>--%>
<%--    ${password}--%>
<%--    <br/>--%>
<%--    <input type="password" name="Password" value="" pattern="\w{4,}"/>--%>
<%--    <br/>--%>
<%--    Name--%>
<%--    <br/>--%>
<%--    <input type="text" name="Name" value=""/>--%>
<%--    <br/>--%>
<%--    LastName--%>
<%--    <br/>--%>
<%--    <input type="text" name="LastName" value=""/>--%>
<%--    <br/>--%>
<%--    <c:if test="${requestScope.wrongLogin}">Invalid Login!</c:if>--%>
<%--    <br/>--%>
<%--    <input type="submit" value="Sign up">--%>
<%--</form>--%>