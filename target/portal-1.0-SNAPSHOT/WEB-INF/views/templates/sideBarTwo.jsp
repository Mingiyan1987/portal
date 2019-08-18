<%@​ page language​=​"java"​ contentType​=​"text/html; charset=UTF-8" pageEncoding​=​"UTF-8"​%>
<%​@taglib​ uri​=​"http://java.sun.com/jsp/jstl/core"​ prefix​=​"c"​%>
<c:set​ ​value​=​"${pageContext.request.contextPath}"​ ​var​=​"contextPath"​ ​/> ​<div​ ​id​=​"templatemo_sidebar_one">
<h4>​Категории​</h4>
​<ul​ ​class​=​"templatemo_list">
​<c:if​ ​test​=​"${not empty categories }">
​<c:forEach​ ​items​=​"${categories}"​ ​var​=​"category"​ >
<li><a
        href​=​"${contextPath}/categories/${category.id}"> $​{​category​.​name​}</​a​></​li>
</c:forEach> ​</c:if>
​</ul>
​<div​ ​class​=​"cleaner_h40"​></div> ​</div>