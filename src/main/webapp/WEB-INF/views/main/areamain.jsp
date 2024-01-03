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
		<table>
			<c:forEach var="dto" items="${dto}" >
				<tr>
					<a href="areas?area1=${dto.area1}&area2=${dto.area2}">${dto.areaname}</a> </br>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>





