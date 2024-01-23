<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>일단 상점 메인</title>
	</head>
	
	<body>
		<h3>${myName} 페이지</h3>
		<form action="/product/createProduct?myName=${myName}" method="post">
			<input type="submit" value="나의 상점 만들기">
			<input type="button" value="상품 등록" onclick="javascript:window.location='/product/productWriteForm?myName=${myName}'">
			<input type="button" value="나의 상점 관리" onclick="javascript:window.location='/product/myProduct'">
			<input type="button" value="전체 상품 조회" onclick="javascript:window.location='/product/allProduct'">
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</body>
</html>