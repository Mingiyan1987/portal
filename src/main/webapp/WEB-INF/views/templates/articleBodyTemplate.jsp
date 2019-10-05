<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mingiyan
  Date: 2019-09-29
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<script>
    var number = 4;
    // Порядок сортировки
    var order = "DESC";
    // Поле для сортировки
    var orderBy = "publishedDate";
    // Счетчик страниц (блоков)
    var pageCounter = 0;

    var artilceBody = "<div class='post_section'>\"​+​\"<h2><a class='article__title'\n" +
        "href='' ></a></h2>\"​+​\"<strong>Дата: class='article__date'></span>|<strong>Автор: class='article__author'></span>\"\n" +
        "+​\"<p><div class='article__content'></div>\" +​\"<div class='cleaner'></div>\"\n" +
        "</span></strong><span\n" +
        "       </strong><span\n" +
        "+​\"<p><div class='category'>Категория: class='article__category'></span></div> <div class='button float_r'><a href=' ' class='more'>Читать далее</a></div>\"​+​\"<div class='cleaner'></div>\"\n" +
        "+​\"</div><div class='cleaner_h40'></div>" +
        "+\"<a href='    ' class='edit'>${labelEdit}</a><a href='     ' class='delete'>${labelDelete}</a>"
</script>
