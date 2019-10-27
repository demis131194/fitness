<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Error 404</title>
</head>
<body>
<h2>Error 404</h2>
<hr/>
<table width = "100%" border = "1">
    <tr valign = "top">
        <td width = "40%"><b>Error:</b></td>
        <td>Page not found</td>
    </tr>

    <tr valign = "top">
        <td><b>URI:</b></td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>

    <tr valign = "top">
        <td><b>Status code:</b></td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>

</table>
</body>
</html>
