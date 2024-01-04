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
			<c:forEach var="areas" items="${areas}" >
				<div style=" margin-right: 10px; text-align: center;">
					<a href="/main/areamain?area1=${areas.area1}&area2=${areas.area2}">
					<span>${areas.areaname}</span>
					</a>
				</div>
			</c:forEach>
		</div>
	</body>
</html>