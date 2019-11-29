<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="account.profile.name" bundle="${rb}" var="userName"/>
<fmt:message key="account.profile.last.name" bundle="${rb}" var="userLastName"/>
<fmt:message key="account.profile.phone" bundle="${rb}" var="userPhone"/>
<fmt:message key="account.profile.edit.title" bundle="${rb}" var="formTitle"/>
<fmt:message key="account.profile.email" bundle="${rb}" var="userEmail"/>

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
                        <form name="profileEditForm" action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" value="EDIT_PROFILE_BY_ADMIN">
                            <input type="hidden" name="userId" value="${sessionScope.userId}">
                            <div class="form-group row">
                                <label for="input-name" class="col-lg-2 col-form-label">${userName}</label>
                                <div class="col-lg-4">
                                    <input type="text" name="userName" class="form-control" id="input-name" value="${sessionScope.userName}" pattern="^[\wа-яА-Я]{3,20}$">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="input-last-name" class="col-lg-2 col-form-label">${userLastName}</label>
                                <div class="col-lg-4">
                                    <input type="text" name="userLastName" class="form-control" id="input-last-name" value="${sessionScope.userLastName}" pattern="^[\wа-яА-Я]{3,20}$">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="input-email" class="col-lg-2 col-form-label">${userEmail}</label>
                                <div class="col-lg-4">
                                    <input type="email" name="userMail" class="form-control" id="input-email" value="${sessionScope.userMail}" pattern="^[\w]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$">
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