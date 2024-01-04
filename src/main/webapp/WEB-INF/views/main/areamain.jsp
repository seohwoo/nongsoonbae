<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>지역별 보기</title>
	</head>
	<body>
	<h1> 지역별 카테고리</h1>
	<div style="display:flex;">
			<c:forEach var="list" items="${list}" >
				<div style=" margin-right: 10px; text-align: center;">
					<a href="areas?pageNum=${pageNum}&area1=${dto.area1}&area2=${dto.area2}">
					<span>${dto.areaname}</span>
					</a> 
				</div>
			</c:forEach>
			<c:if test="${pageNum > 1 }">
				<button onclick="window.location='/main/areas?pageNum=${pageNum-1}'">이전</button>
			</c:if>
			<c:if test="${pageNum < 1 }">
				<button onclick="window.location='/main/areas?pageNum=${pageNum+1}'">다음</button>
			</c:if>	
		</div>
	</body>
</html>





