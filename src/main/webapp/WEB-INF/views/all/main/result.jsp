<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div style="display: flex; flex-direction: column; align-items: flex-start;">
			<h1>검색결과 : ${searchCnt}</h1>
			<h1>검색어 : ${userSearch}</h1>
			<hr />
			<c:if test="${searchCnt==0}">
				<h1>검색결과가 없습니다😭😭😭😭</h1>
			</c:if>
			<c:if test="${searchCnt>0}">
				<c:forEach var="dto" items="${searchList}">
					<%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>
				</c:forEach>
			</c:if>
		</div>
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>