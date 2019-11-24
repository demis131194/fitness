<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<c:set var="authorization" value="${sessionScope.authorization}"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="account.user.title" bundle="${rb}" var="title"/>
<fmt:message key="account.user.id" bundle="${rb}" var="id"/>
<fmt:message key="account.order.trainer.fio" bundle="${rb}" var="trainerFio"/>
<fmt:message key="account.order.client.fio" bundle="${rb}" var="clientFio"/>
<fmt:message key="account.profile.reg.date" bundle="${rb}" var="registrDate"/>
<fmt:message key="phone" bundle="${rb}" var="phone"/>
<fmt:message key="account.user.active" bundle="${rb}" var="active"/>
<fmt:message key="account.profile.discount" bundle="${rb}" var="discount"/>
<fmt:message key="account.user.discount.level" bundle="${rb}" var="discountLevel"/>
<fmt:message key="account.user.cash" bundle="${rb}" var="cash"/>
<fmt:message key="account.user.email" bundle="${rb}" var="email"/>


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
                    <div class="user-title">
                        <h2>${title}</h2>
                    </div>

                    <c:choose>
                        <c:when test="${requestScope.user.role == 'TRAINER'}">
                            <div class="user-table">
                                <c:set var="trainer" value="${requestScope.user}"/>
                                <jsp:useBean id="trainer" type="by.epam.fitness.model.user.Trainer"/>
                                <table>
                                    <tr>
                                        <td class="user-table-td-key">${id}</td>
                                        <td class="user-table-td-value">${trainer.id}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${trainerFio}</td>
                                        <td class="user-table-td-value">${trainer.name} ${trainer.lastName}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${registrDate}</td>
                                        <td class="user-table-td-value">
                                            <fmt:parseDate value="${trainer.registerDateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                                            <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy HH:mm:ss" var="regDate" />
                                                ${regDate}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${phone}</td>
                                        <td class="user-table-td-value">${trainer.phone}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${email}</td>
                                        <td class="user-table-td-value">${trainer.mail}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${active}</td>
                                        <td class="user-table-td-value">${trainer.active}</td>
                                    </tr>
                                </table>
                            </div>
                        </c:when>

                        <c:when test="${requestScope.user.role == 'CLIENT'}">
                            <div class="user-table">
                                <c:set var="client" value="${requestScope.user}"/>
                                <jsp:useBean id="client" type="by.epam.fitness.model.user.Client"/>
                                <table>
                                    <tr>
                                        <td class="user-table-td-key">${id}</td>
                                        <td class="user-table-td-value">${client.id}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${clientFio}</td>
                                        <td class="user-table-td-value">${client.name} ${client.lastName}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${registrDate}</td>
                                        <td class="user-table-td-value">
                                            <fmt:parseDate value="${client.registerDateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                                            <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy HH:mm:ss" var="regDate" />
                                                ${regDate}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${phone}</td>
                                        <td class="user-table-td-value">${client.phone}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${email}</td>
                                        <td class="user-table-td-value">${client.mail}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${discount}</td>
                                        <td class="user-table-td-value">${client.discount}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${discountLevel}</td>
                                        <td class="user-table-td-value">${client.discountLevel}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${cash}</td>
                                        <td class="user-table-td-value">${client.cash}</td>
                                    </tr>
                                    <tr>
                                        <td class="user-table-td-key">${active}</td>
                                        <td class="user-table-td-value">${client.active}</td>
                                    </tr>
                                </table>
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
