<%@​ page language​=​"java"​ contentType​=​"text/html; charset=UTF-8" pageEncoding​=​"UTF-8"​%>
<%​@taglib​ uri​=​"http://java.sun.com/jsp/jstl/core"​ prefix​=​"c"​%>
<c:set​ ​value​=​"${pageContext.request.contextPath}"​ ​var​=​"contextPath"​ ​/>
​<div​ ​id​=​"templatemo_menu">
​<ul>
​<li><a​ ​href​=​"${contextPath}"​>​Главная страница​</a></li>
​<li><a​ ​href​=​"${contextPath}/articles/add"​>​Создать статью​</a></li>
​</ul>
​</div>​ ​<!-- end of templatemo_menu -->
