<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
    <title>Index</title>
</head>
<body>
<%--<c:if test="${sessionScope.userRole == null}">--%>
    <c:redirect url="jsp/pages/main.jsp"/>
<%--</c:if>--%>
<%--<c:if test="${sessionScope.userRole == 'ADMIN'}">--%>
<%--    <c:redirect url="jsp/pages/admin/main.jsp"/>--%>
<%--</c:if>--%>
<%--<c:if test="${sessionScope.userRole == 'TRAINER'}">--%>
<%--    <c:redirect url="jsp/pages/trainer/main.jsp"/>--%>
<%--</c:if>--%>
<%--<c:if test="${sessionScope.userRole == 'CLIENT'}">--%>
<%--    <c:redirect url="jsp/pages/client/main.jsp"/>--%>
<%--</c:if>--%>

</body>
</html>