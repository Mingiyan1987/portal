<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  Author: mingiyan
  Date: 2019-09-29
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
​   <div​ ​id​=​"templatemo_menu">
​       <ul id="panel_reff">
​           <li><a​ ​href​=​"${contextPath}"​>${labelHome}​</a></li>
            <sec:authorize access="isAuthenticated()">
                <li><a href="${contextPath}/articles/add">${labelCreate}</a></li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <li><a href="${contextPath}/registration">${labelReg}</a></li>
            </sec:authorize>
​       </ul>
        <ul id="panel_acc">
            <sec:authorize access="isAnonymous()">
                <li><a href="${contextPath}/login">${labelLogin}</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a id="quit" href="${contextPath}/logout">Выйти</a><span id="username">${pageContext.request.userPrincipal.name}</span></li>
            </sec:authorize>
        </ul>
​   </div>​ ​<!-- end of templatemo_menu -->