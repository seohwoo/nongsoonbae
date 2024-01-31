<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>카테고리별</title>
	</head>
	<body>
	   <div style="display: flex;">
		<c:if test="${cntDetail == 0 }" >
	         <h1 class="catenull"> 상품 없음! </h1>
	         </c:if>
	         <c:if test="${cntDetail >  0 }" >
	         	<div class="container mx-auto mt-4">
 				<div class="row">
	            <c:forEach var="dto" items="${productlistdetail}">
	               <%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>
	            </c:forEach>
	            </div>
	            </div>
	         </c:if>	
	     </div>
   </body>
</html>