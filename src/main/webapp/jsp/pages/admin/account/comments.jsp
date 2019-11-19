<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>

<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="orders.title" bundle="${rb}" var="title"/>

<fmt:message key="comments.active" bundle="${rb}" var="active"/>
<fmt:message key="comments.delete" bundle="${rb}" var="delete"/>
<fmt:message key="comments.restore" bundle="${rb}" var="restore"/>
<fmt:message key="comments.actions" bundle="${rb}" var="actions"/>
<fmt:message key="comments.id" bundle="${rb}" var="id"/>

<fmt:message key="orders.filter.show.filter" bundle="${rb}" var="showFilter"/>
<fmt:message key="orders.filter.trainer.name" bundle="${rb}" var="trainerName"/>
<fmt:message key="orders.filter.client.name" bundle="${rb}" var="clientName"/>
<fmt:message key="orders.filter.trainer.last.name" bundle="${rb}" var="trainerLastName"/>
<fmt:message key="orders.filter.client.last.name" bundle="${rb}" var="clientLastName"/>
<fmt:message key="orders.filter.start.date" bundle="${rb}" var="filterSartDate"/>
<fmt:message key="orders.filter.end.date" bundle="${rb}" var="filterEndDate"/>
<fmt:message key="orders.filter.reg.date" bundle="${rb}" var="filterRegDate"/>
<fmt:message key="orders.filter.active" bundle="${rb}" var="filterActive"/>
<fmt:message key="orders.filter.inactive" bundle="${rb}" var="filterInactive"/>
<fmt:message key="orders.filter.status" bundle="${rb}" var="filterStatus"/>
<fmt:message key="orders.filter.status.new" bundle="${rb}" var="statusNew"/>
<fmt:message key="orders.filter.status.reviewed" bundle="${rb}" var="statusReviewed"/>
<fmt:message key="orders.filter.status.rejected" bundle="${rb}" var="statusRejected"/>
<fmt:message key="orders.filter.status.accepted" bundle="${rb}" var="statusAccepted"/>
<fmt:message key="orders.filter.status.process" bundle="${rb}" var="statusInProcess"/>
<fmt:message key="orders.filter.status.terminated" bundle="${rb}" var="statusTerminated"/>
<fmt:message key="orders.filter.btn.filter" bundle="${rb}" var="btnFilter"/>

<fmt:message key="comment.user.write.about.trainer" bundle="${rb}" var="writeAbout"/>


<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filter.js"></script>
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
                    <div class="orders-title">
                        <h2>${title}</h2>
                    </div>

                    <button type="button" class="btn btn-info filter-button-show" onclick="clickFilter()">${showFilter}</button>
                    <form class="filter-form" id="filterButton" action="${pageContext.request.contextPath}/controller" method="POST">
                        <input type="hidden" name="command" value="FIND_COMMENTS_BY_FILTER_BY_ADMIN">

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputTrainerName">${trainerName}</label>
                                <input type="text" class="form-control" id="inputTrainerName" name="trainerName" placeholder="Name">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputTrainerLastName">${trainerLastName}</label>
                                <input type="text" class="form-control" id="inputTrainerLastName" name="trainerLastName" placeholder="Last name">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputClientName">${clientName}</label>
                                <input type="text" class="form-control" id="inputClientName" name="clientName" placeholder="Name">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputClientLastName">${clientLastName}</label>
                                <input type="text" class="form-control" id="inputClientLastName" name="clientLastName" placeholder="Last name">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="inputRegDate">${filterRegDate}</label>
                                <input type="date" class="form-control" id="inputRegDate" name="registerDate">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="inputActive">${filterStatus}</label>
                                <select id="inputActive" class="form-control" name="active">
                                    <option value="true">${filterActive}</option>
                                    <option value="false">${filterInactive}</option>
                                    <option value="" selected>ANY</option>
                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-success">${btnFilter}</button>
                    </form>

                    <table>
                        <c:forEach items="${requestScope.comments}" var="comment">
                            <jsp:useBean id="comment" type="by.epam.fitness.model.Comment"/>
                            <tbody class="comment">
                            <tr>
                                <td>
                                    <div class="comment-client">
                                        <p>${comment.clientName} ${comment.clientLastName}</p>
                                        <p>${writeAbout}</p>
                                        <span>${comment.trainerName} ${comment.trainerLastName}</span>
                                    </div>
                                </td>
                                <td>
                                    <div class="comment-wrap">
                                        <div class="comment-date">
                                            <small>
                                                <fmt:parseDate  value="${comment.registerDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                                                <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy HH:mm:ss" var="regDate" />
                                                    ${regDate}
                                            </small>
                                        </div>
                                        <div class="comment-text">
                                            <p>${comment.comment}</p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>${active}</td>
                                <td>${comment.active}</td>
                            </tr>
                            <tr>
                                <td>${id}</td>
                                <td>${comment.id}</td>
                            </tr>
                            <tr>
                                <td>${actions}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${comment.active}">
                                            <a href="${pageContext.request.contextPath}/controller?command=DELETE_COMMENT_BY_ADMIN&commentId=${comment.id}">${delete}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/controller?command=RESTORE_COMMENT_BY_ADMIN&commentId=${comment.id}">${restore}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>