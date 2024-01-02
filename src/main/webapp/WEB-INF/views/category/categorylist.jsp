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
		<table>
			<c:forEach var="cate" items="${menu}" >
				<tr>
					<a href="menulist?cate1=${cate.cate1}&area2=${cate.cate2}">${cate.catename}</a> </br>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>

