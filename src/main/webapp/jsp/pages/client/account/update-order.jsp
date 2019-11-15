<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="account.order.title" bundle="${rb}" var="title"/>
<fmt:message key="account.order.id" bundle="${rb}" var="id"/>
<fmt:message key="account.order.trainer.fio" bundle="${rb}" var="trainerFio"/>
<fmt:message key="account.order.register.date" bundle="${rb}" var="registrDate"/>
<fmt:message key="account.order.exercises" bundle="${rb}" var="exercises"/>
<fmt:message key="account.order.nutrition" bundle="${rb}" var="nutrition"/>
<fmt:message key="account.order.start.date" bundle="${rb}" var="startDate"/>
<fmt:message key="account.order.end.date" bundle="${rb}" var="endDate"/>
<fmt:message key="account.order.client.comment" bundle="${rb}" var="clientComment"/>
<fmt:message key="account.order.price" bundle="${rb}" var="price"/>
<fmt:message key="account.order.status" bundle="${rb}" var="status"/>
<fmt:message key="create.order.form.comment" bundle="${rb}" var="formComment"/>
<fmt:message key="create.order.form.start.date" bundle="${rb}" var="formStartDate"/>

<fmt:message key="create.order.form.duration" bundle="${rb}" var="formDuration"/>
<fmt:message key="create.order.form.training.duration.day" bundle="${rb}" var="trainingDurationDay"/>
<fmt:message key="create.order.form.training.duration.week" bundle="${rb}" var="trainingDurationWeek"/>
<fmt:message key="create.order.form.training.duration.month" bundle="${rb}" var="trainingDurationMonth"/>
<fmt:message key="create.order.form.training.duration.three.months" bundle="${rb}" var="trainingDurationThreeMonths"/>
<fmt:message key="create.order.form.training.duration.half.year" bundle="${rb}" var="trainingDurationHalfYear"/>
<fmt:message key="orders.status.accept" bundle="${rb}" var="statusAccept"/>
<fmt:message key="orders.status.reject" bundle="${rb}" var="statusReject"/>
<fmt:message key="update.order.form.submit" bundle="${rb}" var="formSubmit"/>

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
                    <div class="order-table">
                        <c:set var="order" value="${requestScope.order}"/>
                        <jsp:useBean id="order" type="by.epam.fitness.model.Order"/>
                        <table>
                            <tr>
                                <td class="order-table-td-key">${id}</td>
                                <td class="order-table-td-value">${order.id}</td>
                            </tr>
                            <tr>
                                <td class="order-table-td-key">${trainerFio}</td>
                                <td class="order-table-td-value">${order.trainerName} ${order.trainerLastName}</td>
                            </tr>
                            <tr>
                                <td class="order-table-td-key">${registrDate}</td>
                                <td class="order-table-td-value">
                                    <fmt:parseDate  value="${order.registerDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                                    <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy HH:mm:ss" var="regDate" />
                                    ${regDate}
                                </td>
                            </tr>
                            <tr>
                                <td class="order-table-td-key">${exercises}</td>
                                <td class="order-table-td-value">${order.exercises}</td>
                            </tr>
                            <tr>
                                <td class="order-table-td-key">${nutrition}</td>
                                <td class="order-table-td-value">${order.nutrition}</td>
                            </tr>
                            <tr>
                                <td class="order-table-td-key">${startDate}</td>
                                <td class="order-table-td-value">${order.startDate}</td>
                            </tr>
                            <tr>
                                <td class="order-table-td-key">${endDate}</td>
                                <td class="order-table-td-value">${order.endDate}</td>
                            </tr>
                            <tr>
                                <td class="order-table-td-key">${price}</td>
                                <td class="order-table-td-value">${order.price}</td>
                            </tr>
                            <tr>
                                <td class="order-table-td-key">${status}</td>
                                <td class="order-table-td-value">${order.orderStatus.name()}</td>
                            </tr>
                        </table>

                        <form name="createOrderForm" action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" value="UPDATE_ORDER_BY_CLIENT">
                            <input type="hidden" name="orderId" value="${order.id}">

                            <div class="form-group">
                                <label for="input-comment">${formComment}</label>
                                <textarea name="comment" class="form-control" id="input-comment">${order.clientComment}</textarea>
                            </div>

                            <div class="form-group">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="status" id="status-accept" value="3" checked>
                                    <label class="form-check-label" for="status-accept">${statusAccept}</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="status" id="status-reject" value="2">
                                    <label class="form-check-label" for="status-reject">${statusReject}</label>
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
