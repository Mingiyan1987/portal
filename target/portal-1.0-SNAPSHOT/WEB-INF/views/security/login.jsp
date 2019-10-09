<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mingiyan
  Date: 2019-10-05
  Time: 09:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<section class="container">
    <div class="login">
        <h1>Авторизация</h1>
        <form action="<c:url value='/login'/>" method="POST">
            <c:if test="${not empty message}">
                <span class="error">${message}</span>
            </c:if>
                <p><input type="text" name="username" placeholder="Логин"></p>
                <p><input type="password" name="password" placeholder="Пароль"></p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <p class="submit"><input type="submit" name="commit" value="Войти"></p>
        </form>
    </div>
</section>