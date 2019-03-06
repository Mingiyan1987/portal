<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page session="true"%>
<html>
<head>
    <title>Страница авторизации</title>
</head>
<body onload='document.f.username.focus();'>
<form name="formLogin" action='/login' method="POST">
    <div class="form-group">
        <label for="exampleLogin">Email address</label>
        <input type="login" class="form-control" id="exampleLogin" value=''>
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
    </div>
    <div class="form-check">
        <input type="checkbox" class="form-check-input" id="exampleCheck1">
        <label class="form-check-label" for="exampleCheck1">Check me out</label>
    </div>
    <input type='submit' type='submit' value="Login" />
</form>
</body>
</html>
