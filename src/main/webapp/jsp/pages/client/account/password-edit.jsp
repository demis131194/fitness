<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="account.profile.change.password.title" bundle="${rb}" var="formTitle"/>
<fmt:message key="account.profile.current.password" bundle="${rb}" var="currentPassword"/>
<fmt:message key="account.profile.new.password" bundle="${rb}" var="newPassword"/>
<fmt:message key="account.profile.repeat.password" bundle="${rb}" var="repeatPassword"/>

<fmt:message key="update.user.form.submit" bundle="${rb}" var="submit"/>


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
        <div class="row">
            <div class="col-lg-2">
                <c:import url="/jsp/fragment/account-menu.jsp"/>
            </div>
            <div class="col-lg-10">
                <div class="main-section">
                    <div class="create-order-title">
                        <h2>${formTitle}</h2>
                    </div>
                    <div class="create-order-form">
                        <form name="passwordEditForm" action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" value="EDIT_PASSWORD_BY_CLIENT">
                            <input type="hidden" name="userId" value="${sessionScope.userId}">
                            <div class="form-group row">
                                <label for="input-current-password" class="col-lg-2 col-form-label">${currentPassword}</label>
                                <div class="col-lg-4">
                                    <input type="password" name="currentPassword" class="form-control" id="input-current-password" pattern="^[\w]{5,18}$">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="input-new-password" class="col-lg-2 col-form-label">${newPassword}</label>
                                <div class="col-lg-4">
                                    <input type="password" name="newPassword" class="form-control" id="input-new-password" pattern="^[\w]{5,18}$">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="input-repeat-password" class="col-lg-2 col-form-label">${repeatPassword}</label>
                                <div class="col-lg-4">
                                    <input type="password" name="repeatPassword" class="form-control" id="input-repeat-password" pattern="^[\w]{5,18}$">
                                </div>
                            </div>
                            <c:if test="${requestScope.errMessage != null}">
                                <div class="alert alert-danger">
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
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
