<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: mingiyan
  Date: 2019-10-04
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${contextPath}/resources" var="resPath"/>
<section class="container">
    <div class="login">
        <h1>${authorReg}</h1>
        <c:if test="${not empty message}">
            <span class="error">${message}</span>
            <p/>
        </c:if>
            <form:form modelAttribute="author" action="/registration" method="POST">
                <p>${labelFirstName}: <form:input path="firstname" value="" placeholder="${labelFirstName}"/></p>
                <form:errors path="firstname" cssClass="error"/>
                <p>${labelLastName}: <form:input path="lastname" value="" placeholde="${labelLastName}"/></p>
                <form:errors path="lastname" cssClass="error" />
                <p>${labelLogin}: <form:input path="login" type="text" placeholder="${labelLogin}"/></p>
                <form:errors path="login" cssClass="error"/>
                <p>${labelPass}: <form:input path="password" type="password" placeholder="${labelPass}"/></p>
                <form:errors path="password" cssClass="error" />
                <p class="submit"><input type="submit" name="commit" value="${labelBreg}"></p>
            </form:form>
    </div>
</section>
