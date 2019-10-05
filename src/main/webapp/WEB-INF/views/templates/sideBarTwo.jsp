<%--
  Created by IntelliJ IDEA.
  User: mingiyan
  Date: 2019-09-29
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
    <div id="temlatemo_sidebar_one">
        <h4>Категории</h4>
            <ul class="templatemo_list">
                <c:if test="${not empty categories}">
                    <c:forEach items="${categories}" var="category">
                        <li><a class="category_reff"
                               href="${contextPath}/categories.id}">

                        </a>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        <div​ ​class​=​"cleaner_h40"​></div>
    </div>
