<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Author: mingiyan
  Date: 2019-09-29
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:set value="${pageContext.request.contextPath}" var="cotextPath"/>
<c:set value="${contextPath}/resources" var="resPath"/>
<h2 class="category_name">${category.name}</h2>
<script>
    var url = "./${category.id}/articles_ajax";
    var contextPath="${contextPath}";
</script>
<script src="${resPath}/assets/getData.js"></script>
