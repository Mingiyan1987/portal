<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page session="true"%>
<html>
<head>
    <title>Страница авторизации</title>
</head>
<body>
<section​ ​class​=​"container"> ​<div​ ​class​=​"login">
​<h1>​Авторизация​</h1>
<form method="post" action="​<c:url​ ​value​=​'/login'​ ​/>​" method='POST'> ​<c:if​ ​test​=​"${not empty message}">
    ​<span​ ​class​=​"error"​>​${message}​</span>
    ​</c:if>
    ​<p><input​ ​type​=​"text"​ ​name​=​"login"​ ​value​=​""​ ​placeholder​=​"Логин"​></p> ​<p><input ​type​=​"password" ​name​=​"password" ​value​=​""
                                                                                                      placeholder​=​"Пароль"​></p>
    ​<input ​type​=​"hidden" ​name​=​"${_csrf.parameterName}"
            value​=​"${_csrf.token}"​/>
    ​<p​ ​class​=​"submit"​><input​ ​type​=​"submit"​ ​name​=​"commit"​ ​value​=​"Войти"​></p>
    ​</form> ​</div>
​</section>
</body>
</html>
