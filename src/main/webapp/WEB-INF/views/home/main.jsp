<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  Author: mingiyan
  Date: 2019-09-29
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${contextPath}/resources" var="resPath" />
<script>
    var url = "./article/article_ajax";
    var contextPath = "${contextPath}";
</script>
<script src="${resPath}/assets/getData.js"></script>
    <div id="tempatemo_menu">
        <ul id="panel_reff">
            <li><a href="${contextPath}">${labelHome}</a></li>
            <li><a href="${contextPath}/articles/add">${labelCreate}</a></li>
            <li><a href="${contextPath}/registration">${labelReg}</a></li>
        </ul>
    </div>