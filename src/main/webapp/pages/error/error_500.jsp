<%--
  Created by IntelliJ IDEA.
  User: Moxirbek
  Date: 08.07.2022
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
<h1> Request from: </h1> <h3>${pageContext.errorData.requestURI} is failed </h3></br>
Servlet name: ${pageContext.errorData.servletName} </br>
Status code: ${pageContext.errorData.statusCode} </br>
Exception: ${pageContext.exception} </br>
<br/><br/><br/>
Message from exception: ${error_msg}
</body>
</html>
