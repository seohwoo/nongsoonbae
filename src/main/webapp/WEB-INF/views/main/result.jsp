<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<h1>검색결과 : ${searchCnt}</h1>
		<h1>검색어 : ${userSearch}</h1>
		<c:if test="${searchCnt==0}">
			<h1>검색결과가 없습니다😭😭😭😭</h1>
		</c:if>
		<c:if test="${searchCnt>0}">
			<c:forEach var="dto" items="${searchList}">
				<h1>${dto.productname}</h1>
				<h1>${dto.username}</h1>
				<h1>${dto.cate1} > ${dto.cate2} > ${dto.cate3}</h1>
				<h1>${dto.area1} > ${dto.area2}</h1>
			</c:forEach>
		</c:if>
	</body>
</html>