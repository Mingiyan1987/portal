<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: mingiyan
  Date: 2019-10-03
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${contextPath}/resources" var="resPath"/>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>IT SITE</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <link href="${resPath}/style.css" rel="stylesheet" type="text/css"/>
</head>
<body style="background-image:none; background-color:#0aadd6;">
    <div id="sec_content">
        <tiles:insertAttribute name="content" ignore="true"/>
    </div>
</body>
</html>
