<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h3>임시 상품 메인 </h3>
		<table border="1px" style="text-align: center;">
			<tr>
				<td>상품넘버</td>
				<td>상품이름</td>
			</tr>
			
			<c:forEach var="APdto" items="${APdto}">
				<tr>
					<td>
						<a href="/product/productInfo?productnum=${APdto.productnum}&follow=${APdto.username}">${APdto.productnum}</a>
					</td>
					<td>${APdto.productname}</td>			
				</tr>				
			</c:forEach>
		</table>
		<p>	
			<a href="/product/productMain">상품 메인</a>
		</p>
	</body>
</html>