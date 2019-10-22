<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2><a href="${pageContext.request.contextPath}">HOME</a></h2>
<hr/>
<h3>Welcome!</h3>
<hr/>
${user.name}, hello!
<br/>
Your role is - ${sessionScope.get("role")}
<hr/>

</body>
</html>
