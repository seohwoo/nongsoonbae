<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h3>${username} 페이지</h3>
		<form action="/product/createProduct" method="post" name="createProduct">
			<input type="submit" value="나의 상점 만들기">
			<input type="button" value="상품 등록" onclick="javascript:window.location='/product/productWriteForm'">
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</body>
</html>