<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="create.deposit.form.title" bundle="${rb}" var="formTitle"/>
<fmt:message key="deposit.form.card.name" bundle="${rb}" var="formCard"/>
<fmt:message key="deposit.form.cash.amount" bundle="${rb}" var="formAmount"/>
<fmt:message key="create.deposit.form.submit" bundle="${rb}" var="formSubmit"/>


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
                    <div class="deposit-title">
                        <h2>${formTitle}</h2>
                    </div>
                    <div class="deposit-form">
                        <form name="depositForm" action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" value="deposit_by_client">
                            <div class="form-group row">
                                <label for="inputCardNumber" class="col-form-label col-lg-2">${formCard}</label>
                                <div class="col-lg-3">
                                    <input type="text" id="inputCardNumber" name="cardNumber" class="form-control">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputAmount" class="col-form-label col-lg-2">${formAmount}</label>
                                <div class="col-lg-3">
                                    <input type="number" id="inputAmount" name="cashAmount" class="form-control" step=".01">
                                </div>
                            </div>
                            <c:if test="${requestScope.errMessage != null}">
                                <div class="alert alert-danger col-lg-7">
                                    <span><fmt:message key="${requestScope.errMessage}" bundle="${err_rb}"/></span>
                                </div>
                            </c:if>
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
