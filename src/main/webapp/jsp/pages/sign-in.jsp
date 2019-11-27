<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="login.login" bundle="${rb}" var="login"/>
<fmt:message key="login.password" bundle="${rb}" var="password"/>
<fmt:message key="login.button.login" bundle="${rb}" var="buttonLogin"/>
<fmt:message key="login.title.login" bundle="${rb}" var="login"/>
<fmt:message key="project.navigation.home" bundle="${rb}" var="home"/>
<fmt:message key="form.login" bundle="${rb}" var="formLogin"/>
<fmt:message key="form.password" bundle="${rb}" var="formPassword"/>
<fmt:message key="form.login.placeholder" bundle="${rb}" var="loginPlaceholder"/>
<fmt:message key="form.password.placeholder" bundle="${rb}" var="passwordPlaceholder"/>
<fmt:message key="form.submit" bundle="${rb}" var="submit"/>


<html>
<head>
    <title>${login}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/js/validate.js"></script>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 offset-lg-4">
                <div class="login-form">
                    <div class="main-form-header">
                        <h4><a href="${pageContext.request.contextPath}">${home}</a></h4>
                    </div>
                    <form name="loginForm" action="${pageContext.request.contextPath}/controller" method="POST">
                        <input type="hidden" name="command" value="login">
                        <div class="form-group">
                            <label for="input-login">${formLogin}</label>
                            <input type="text" name="userLogin" class="form-control" id="input-login" placeholder="${loginPlaceholder}">
                        </div>
                        <div class="form-group">
                            <label for="input-password">${formPassword}</label>
                            <input type="password" name="userPassword" class="form-control" id="input-password" placeholder="${passwordPlaceholder}">
                        </div>
                        <c:if test="${requestScope.errMessage != null}">
                        <div class="alert alert-danger">
                            <span><fmt:message key="${requestScope.errMessage}" bundle="${err_rb}"/></span>
                        </div>
                        </c:if>
                        <button type="submit" class="btn btn-primary" id="submit" disabled>${submit}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
