<%--
  Created by IntelliJ IDEA.
  User: mingiyan
  Date: 02/03/2019
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%​@taglib​ uri​=​"http://java.sun.com/jsp/jstl/core"​ prefix​=​"c"​%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>index</title>
</head>
<body>

<h1>Index</h1>

<sec:authorize access="isAuthenticated()">
    <p>USER: <sec:authentication property="username"/></p>
</sec:authorize>

<p>
    <a href="/free" target="_blank">GO TO FREE PAGE</a>
</p>

<sec:authorize access="!isAuthenticated()">
    <p>
        <a href="/login">GOTO LOGIN</a>
    </p>
</sec:authorize>

<sec:authorize access="hasRole('ADMINISTRATOR')">
    <p>
        <a href="/admin">GOTO ADMIN PAGE</a>
    </p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <p>
        <a href="/logout">GOTO LOGOUT</a>
    </p>
</sec:authorize>
</body>
</html>
