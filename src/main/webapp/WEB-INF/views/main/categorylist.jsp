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
			<c:forEach var="cate" items="${menu}" >
				<div style=" margin-right: 10px; text-align: center;">
					<a href="menulistDetail?cate1=${cate.cate1}&cate2=${cate.cate2}">${cate.catename}</a> </br>
				</div>
			</c:forEach>
		</div>
	</body>	
</html>















