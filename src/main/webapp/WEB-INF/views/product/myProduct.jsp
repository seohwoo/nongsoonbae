<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h3>${username} 상점 정보</h3>
		<hr />
		<table border="1px" style="text-align: center;">
			<tr>
				<td>상품넘버</td>
				<td>상품이름</td>
				<td>찜한 갯수</td>
				<td>가격</td>
				<td>상품재고</td>
				<td>판매갯수</td>
				<td>사진갯수</td>
				<td>상품설명</td>
				<td>상품 조회수</td>
				<td>판매시작일</td>
				<td>판매종료일</td>
				<td>옵션상태유무</td>
			</tr>
			
			<c:forEach var="productDTO" items="${productDTO}">
				<tr>
					<td>${productDTO.productnum}</td>
					<td>${productDTO.productname}</td>			
					<td>${productDTO.wishcount}</td>			
					<td>${productDTO.totalprice}</td>			
					<td>${productDTO.productcount}</td>			
					<td>${productDTO.sellcount}</td>			
					<td>${productDTO.imagecount}</td>			
					<td>${productDTO.content}</td>			
					<td>${productDTO.readcount}</td>
					<td><fmt:formatDate value="${productDTO.startdate}" dateStyle="short" type="date"/></td>
					<td><fmt:formatDate value="${productDTO.enddate}" dateStyle="short" type="date"/></td>
					<td>${productDTO.optionstatus}</td>			
				</tr>				
			</c:forEach>
			
			
			
			
		</table>
		<p>	
			<a href="/product/productMain">상품 메인</a>
		</p>
	</body>
</html>