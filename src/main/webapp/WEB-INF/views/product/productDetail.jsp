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
		<form action="#" method="post" name="form">
			<h3>${productDTO.productname} 상품 정보</h3>
			<td>지역 : ${areaName1}</td>
			<td>${areaName2}</td>
			<td>/ 이름 : ${name}</td>
					
			<table border="1px" style="text-align: center;">
				<tr>
					<td>상품넘버</td>
					<td>${productDTO.productnum}</td>
				</tr>
				
				<tr>	
					<td>상품이름</td>
					<td>${productDTO.productname}</td>
				</tr>
				
				<tr>	
					<td>찜한 갯수</td>
					<td>${productDTO.wishcount}</td>
				</tr>
				
				<tr>	
					<td>가격</td>
					<td>${productDTO.totalprice}</td>
				</tr>	
					
				<tr>	
					<td>상품재고</td>
					<td>${productDTO.productcount}</td>
				</tr>	
					
				<tr>	
					<td>판매갯수</td>
					<td>${productDTO.sellcount}</td>
				</tr>	
					
				<tr>	
					<td>사진갯수</td>
					<td>${productDTO.imagecount}</td>
				</tr>	
					
				<tr>	
					<td>상품설명</td>
					<td>${productDTO.content}</td>
				</tr>	
					
				<tr>	
					<td>상품 조회수</td>
					<td>${productDTO.readcount}</td>
				</tr>	
				
				<tr>	
					<td>판매시작일</td>
					<td><fmt:formatDate value="${productDTO.startdate}" dateStyle="short" type="date"/></td>
				</tr>	
					
				<tr>	
					<td>판매종료일</td>
					<td><fmt:formatDate value="${productDTO.enddate}" dateStyle="short" type="date"/></td>
				</tr>	
				
				<tr>
					<td>옵션</td>
					<td>
						<select>
							<c:forEach var="option" items="${option}">
								<option>상품명 : ${option.productname} 가격 : ${option.totalprice}</option>
							</c:forEach>
						</select>
					</td>								
				</tr>
				
				<tr> 
					<td colspan="2" align="center"> 
						<input type="submit" name="confirm" value="결제하기" >
																			
						<input type="button" value="찜하기" onclick="javascript:window.location='/product/productPick?productnum=${productnum}&otherUsername=${otherUsername}'">
						<input type="button" value="장바구니" onclick="javascript:window.location='/product/productShoppingCart?productnum=${productnum}&otherUsername=${otherUsername}'">
						<input type="button" value="리뷰쓰기" onclick="javascript:window.location='#'">
					</td>
				</tr>			
			</table>
		</form>
	</body>
</html>