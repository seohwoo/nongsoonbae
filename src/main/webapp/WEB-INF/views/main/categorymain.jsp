<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>카테고리별</title>
	</head>
	<body>
	<h1> 품목별 카테고리</h1>
		<div style="display:flex;">
			<c:forEach var="dto" items="${dto}" >
				<div style="margin-right: 10px; text-align: center;">
					<img src="${dto.img}" border="0" width="100" height="100">
					<a href="/main/menulist?cate1=${dto.cate1}&cate2=${dto.cate2}&cate3=${dto.cate3}">
					<span>${dto.catename}</span>
					</a>
				</div>
			</c:forEach>
		</div>
	</body>
</html>

