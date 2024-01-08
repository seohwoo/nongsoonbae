<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>카테고리별</title>
	</head>
	<body>
	<h1> 품목별 카테고리</h1>
		<div style="display: flex;">
			<c:forEach var="dto" items="${dto}" >
				<div style="margin-right: 10px;">
					<a href="/main/areamain?area1=${dto.area1}&area2=${dto.area2}">${dto.areaname}</a>
				</div>
			</c:forEach>
			
			
		</div>
	</body>
</html>

