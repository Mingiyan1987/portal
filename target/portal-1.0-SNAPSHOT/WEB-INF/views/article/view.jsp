<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Author: mingiyan
  Date: 2019-09-29
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath"/>
<c:if test="${not empty add}">
    <h2><a class="ad_title" href=''></a>${article.title}</h2>
        <strong>Дата:  </span> </strong><span class="ad_date"><fmt:fomDate pattern="yyyy-MM-dd" value="${article.publishedDate}"/></span>
        <strong>Автор:</strong>
        <span class='ad__user'>${article.author.firstname}</span>
        <div class="cleaner"></div>
        <p><div class="ad_content view">${article.content}</div>
        <div class="cleaner"></div>
        <p><div class="category view">Категория: <span>${article.category.name}</span> </div>
</c:if>