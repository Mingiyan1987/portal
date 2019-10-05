<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
​<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  Author: mingiyan
  Date: 2019-09-29
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${contextPath}/resources" var="resPath"/>
<div class="post_section">
    <form:form modelAttribute="article" class="add_article_form"
               method="POST" action="${reff}">
        <h2 class="message">Создание статьи</h2>
        <strong class="add_category">Категория</strong>
        <select id="categoryId" name="categoryId" class="cd-select">
            <c:if test="${not empty categories}">
                <option value="0" selected>Выбрать категорию</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.nameCategory}</option>
                </c:forEach>
            </c:if>
        </select>
        <p><label path="title" class="add_title"/>
                <input type="text" path="title" class="article_title_input"/>
                <errors path="title" cssClass="error"/>
        <p style="padding-top:50px;">
                <textarea path="content" id="content" class="contentarea"></textarea>
                <errors path="content" cssClass="error"/>
           <input type="submit" class="button_sub" value="${labelPublish}"/>
        </p>
    </form:form>
</div>

<script src="https://cdn.ckeditor.com/ckeditor5/12.4.0/classic/ckeditor.js"></script>
<script type="text/javascript">
    ${document}.ready(function () {
        CKEDITOR.replace('content');
        CKEDITOR.config.width="100%";
        CKEDITOR.config.height=600;
    });
</script>