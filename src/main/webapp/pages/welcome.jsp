<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h3>Welcome!</h3>
<hr/>
${user}
<hr/>

<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="logout">
    <br/>
    <input type="submit" value="Logout">
</form>

</body>
</html>
