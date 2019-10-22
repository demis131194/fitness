<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2><a href="${pageContext.request.contextPath}">HOME</a></h2>
<hr/>
<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/test">
    <input type="hidden" name="command" value="login"/>
    Login:
    <br/>
    <input type="text" name="Login" value=""/>
    <br/>
    Password:
    <br/>
    <input type="password" name="Password" value=""/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="Log in">
</form>
</body>
</html>
