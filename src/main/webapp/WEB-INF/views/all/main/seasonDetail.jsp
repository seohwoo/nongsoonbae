<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<h1>${catename}</h1>
		<c:if test="${productCnt==0}">
			<h1>상품을 준비중입니다😭😭😭😭</h1>
		</c:if>
		<c:if test="${productCnt>0}">
			<c:forEach var="dto" items="${productList}">
				<%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>
			</c:forEach>
		</c:if>
	</body>
</html>