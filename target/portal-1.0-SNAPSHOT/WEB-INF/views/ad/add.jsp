<%--
  Created by IntelliJ IDEA.
  User: mingiyan
  Date: 05/03/2019
  Time: 08:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath"  />

<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Форма добавления статьи</title>
    <script   src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
</head>
<body>
<div id="templatemo_header_wrapper">
    <div id="templatemo_header">

        <div id="site_title">

        </div>

        <div id="templatemo_rss">
            <a href="" target="_parent">SUBSCRIBE<br /><span>OUR FEED</span></a>
        </div>

    </div> <!-- end of header -->

    <div id="templatemo_menu">

        <ul>
            <li><a href="${contextPath}">Главная</a></li>
            <li><a href="${contextPath}/articles/add">Написать статью</a></li>
        </ul>

    </div> <!-- end of templatemo_menu -->

</div> <!-- end of header wrapper -->

<div id="templatemo_main_wrapper">
    <div id="templatemo_add_content_wrapper">

        <div id="templatemo_content">




        <div class="cleaner_h40"><!-- a spacing between 2 posts --></div>

    </div>

    <div class="cleaner_h40"></div>

</div>

<div class="cleaner"></div>
</div> <!-- end of content wrapper -->

<div id="templatemo_footer_wrapper">
    <div id="templatemo_footer">

        Copyright © 2016 <a href="#">IT SITE</a> |


    </div> <!-- end of templatemo_copyright -->
</div> <!-- end of copyright wrapper -->
</body>
</html>
