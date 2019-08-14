<%--
  Created by IntelliJ IDEA.
  User: mingiyan
  Date: 05/03/2019
  Time: 08:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
    <title>Форма просмотра статьи</title>
</head>
<body>
<c:if​ ​test​=​"${not empty ad}"​>
​<div​ ​class​=​'post_section view'>
​<h2><a​ ​class​=​'ad__title'​ ​href​=​''​ ​></a>​${ad.title}​</h2>
​<strong>​Дата: ​</span></strong>
<span class​=​'ad__date'​>​<fmt:formatDate pattern="yyyy-MM-dd" value​=​"${ad.publishedDate}"​ ​/></​span​>​ ​|​ ​<strong>​Автор:
    <​/strong> <span class='ad__author'>${ad.author.firstname}</​span> ​<div​ ​class​=​"cleaner"​></div>
    ​<p><div​ ​class​=​'ad__content view'​>​${ad.content}​</div> ​<div​ ​class​=​'cleaner'​></div>
    ​<p><div​ ​class​=​'category view'​>​Категория:
    <span>​${ad.category.name}​</span></div> ​</div>
    ​</c:if>
</body>
</html>
