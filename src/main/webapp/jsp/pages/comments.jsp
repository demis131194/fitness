<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/message" var="rb"/>
<fmt:message key="project.name" bundle="${rb}" var="projectName"/>
<fmt:message key="project.contacts" bundle="${rb}" var="projectContacts"/>
<fmt:message key="user.box.fio" bundle="${rb}" var="fio"/>
<fmt:message key="comment.user.write.about.trainer" bundle="${rb}" var="writeAbout"/>

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

        <div class="main-section">
            <table>
                <c:forEach items="${requestScope.comments}" var="comment">
                    <jsp:useBean id="comment" type="by.epam.fitness.to.CommentTo"/>
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
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<c:import url="/jsp/fragment/footer.jsp"/>
</body>
</html>
