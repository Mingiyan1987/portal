<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@​ page language​=​"java"​ contentType​=​"text/html; charset=UTF-8" pageEncoding​=​"UTF-8"​%>
<%@​ taglib uri​=​"http://java.sun.com/jsp/jstl/core"​ prefix​=​"c"​%>
<%@​ taglib uri​=​"http://www.springframework.org/tags/form"​ prefix​=​"form"​%>
<c:set​ ​value​=​"${pageContext.request.contextPath}"​ ​var​=​"contextPath"​ ​/> <c:set​ ​value​=​"${contextPath}/resources"​ ​var​=​"resPath"​ ​/>
</c:if>
​<div​ ​class​=​"post_section"​>
​<form:form​ ​modelAttribute​=​"article"​ ​class​=​"add_article_form"
method​=​"POST"​ ​action​=​"${contextPath}/articles"​>
​<h2​ ​class​=​"message"​>​Создание статьи​</h2>
<strong​ ​class​=​"add_category"​>​Категория*​</strong>
<select​ ​id​=​"categoryId"​ ​name​=​"categoryId"​ ​class​=​"cd-select">
​<c:if​ ​test​=​"${not empty categories}">
​<option​ ​value​=​"0"​ ​selected​>​Выбрать категорию​</option>
​<c:forEach​ ​items​=​"${categories}"​ ​var​=​"category">
​<option​ ​value​=​"${category.id}"​>​${category.name}​</option>
​</c:forEach> ​</c:if>
​</select>
​<p><form:label ​path​=​"title" class​=​"add_title"​>​Заголовок*​</form:label>form:<form:input ​type​=​"text" ​path​=​"title"
class​=​"add_title_input"​ ​/>
​<form:errors​ ​path​=​"title"​ ​cssClass​=​"error"​ ​/> ​<p​ ​style​=​"​padding​-​top​:​50px​;​">
​<form:textarea ​path​=​"content" ​id​=​"content" class​=​"contentarea"​></form:textarea>
​<form:errors​ ​path​=​"content"​ ​cssClass​=​"error"​ ​/> ​<div​ ​class​=​"author_add">
​<span​ ​class​=​"author_info_title"​>​Автор*​</span>
​<form:input ​path​=​"author.firstname" ​type​=​"text" ​placeholder​=​"Имя"
class​=​"add_author_firstname"​ ​/>
​<form:errors​ ​path​=​"author.firstname"​ ​cssClass​=​"error"​ ​/>
​<form:input ​path​=​"author.lastname" ​type​=​"text" ​placeholder​=​"Фамилия" name​=​"add_author_lastname"​ ​/>
​<form:errors​ ​path​=​"author.lastname"​ ​cssClass​=​"error"​ ​/> ​</div>
​<input​ ​type​=​"submit"​ ​class​=​"button_sub"​ ​value​=​"${labelPublish}"​ ​/>
​</form:form> ​</div>
<script​ ​src​=​"${resPath}/assets/ckeditor/ckeditor.js"​></script> <script​ ​type​=​"text/javascript">
$​(​document​).​ready​(​function​(){ CKEDITOR​.​replace​(​'content'​); CKEDITOR​.​config​.​width​=​"100%"; CKEDITOR​.​config​.​height​=​600;
​}); </script>