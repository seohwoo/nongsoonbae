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
			<c:forEach var="areas" items="${areas}" >
				<tr>
					<a href="category?area1=${dto.area1}">${areas.areaname}</a> </br>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>