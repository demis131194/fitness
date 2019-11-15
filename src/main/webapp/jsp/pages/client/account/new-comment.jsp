<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:setBundle basename="bundle/err" var="err_rb"/>

<fmt:message key="project.name" bundle="${rb}" var="title"/>
<fmt:message key="create.comment.form.title" bundle="${rb}" var="formTitle"/>
<fmt:message key="create.comment.form.trainers" bundle="${rb}" var="formTrainers"/>
<fmt:message key="create.comment.form.comment" bundle="${rb}" var="formComment"/>
<fmt:message key="create.comment.form.placeholder.comment" bundle="${rb}" var="formPlaceholderComment"/>
<fmt:message key="create.comment.form.submit" bundle="${rb}" var="formSubmit"/>


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
                    <div class="create-comment-title">
                        <h2>${formTitle}</h2>
                    </div>
                    <div class="create-order-form">
                        <form name="createCommentForm" action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" value="CREATE_NEW_COMMENT">
                            <div class="form-group">
                                <label for="inputTrainerId">${formTrainers}</label>
                                <select id="inputTrainerId" class="form-control" name="trainerId">
                                    <c:forEach items="${requestScope.trainers}" var="trainer">
                                        <jsp:useBean id="trainer" class="by.epam.fitness.model.user.Trainer"/>
                                        <option value="${trainer.id}">${trainer.lastName} ${trainer.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="input-comment">${formComment}</label>
                                <textarea name="comment" class="form-control" id="input-comment" placeholder="${formPlaceholderComment}"></textarea>
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
