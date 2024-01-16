<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h3>상품 메인 TEST</h3>
		<table border="1px" style="text-align: center;">
			<tr>
				<td>상품넘버</td>
				<td>상품이름</td>
				<td>이름</td>
			</tr>
			
			<c:forEach var="dto" items="${allProductDTO}">
				<tr>
					<td>${dto.productnum}</td>
					<td><a href="/product/productDetail?productname=${dto.productname}">${dto.productname}</a></td>
					<td>${dto.username}</td>			
				</tr>				
			</c:forEach>
		</table>
		<p>	
			<a href="/product/productMain">상품 메인</a>
		</p>
	</body>
</html>