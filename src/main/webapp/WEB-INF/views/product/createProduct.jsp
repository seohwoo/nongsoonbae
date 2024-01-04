<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h4>${username} 페이지</h4>
		<form action="/product/createProductPro" method="post" name="createProductPro">
			<input type="submit" value="나의 상점 만들기22" >
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</body>
</html>