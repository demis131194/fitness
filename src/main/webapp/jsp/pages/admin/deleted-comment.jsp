<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="comment.deleted.title" bundle="${rb}" var="commentDeletedTitle"/>
<fmt:message key="comment.deleted.text" bundle="${rb}" var="commentDeletedText"/>
<fmt:message key="account.menu.comments" bundle="${rb}" var="comments"/>

<html>
<head>
    <title>${projectName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <meta http-equiv="refresh" content="5;${pageContext.request.contextPath}/controller?command=FIND_ALL_COMMENTS_BY_ADMIN">
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>

<div class="main">
    <div class="container">
        <div class="main-section">
            <div class="сщььуте-deleted">
                <div class="col-lg-10 offset-lg-1">
                    <h2 class="order-deleted-title">${commentDeletedTitle}</h2>
                    <p>${commentDeletedText} ${requestScope.commentId}</p>
                    <a href="${pageContext.request.contextPath}/controller?command=FIND_ALL_COMMENTS_BY_ADMIN">${comments}</a>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>