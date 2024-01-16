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
		<h3>${productDTO.productname} 상품 정보</h3>
		<td>${areaName1}</td>
		<td>${areaName2}</td>
		<td>${name}</td>
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
			
			<tr>
				<td>${productDTO.productnum}</a></td>
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
	</body>
</html>