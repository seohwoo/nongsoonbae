<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="icon" href="/resources/img/logo.png">	
		<script src="/resources/js/jquery-3.7.1.min.js"></script>	
	</head>
	
	<script>
		$(function() {
			$("#option").on("change", function() {
				var selectedOptionNum = $("#option").val();
				$("#selectedOptionNum").val(selectedOptionNum);
			})
		})
	</script>
	
	<body>
		<table border="1px" style="text-align: center;">
			${Adto1.areaname}  ${Adto2.areaname}  ${APdto.name} | 팔로워  ${APdto.followers}
			
			<tr>
			    <td>
			        <c:forEach var="Idto" items="${Idto}">
			            <img src="/resources/realImage/${Idto.filename}" width="100" height="100">
			        </c:forEach>
			    </td>
			</tr>
			
			<tr> 
				<td width="200">상품이름</td>
				<td width="200">${APdto.productname}</td>
			</tr>
			
			<tr> 
				<td width="200">찜 개수</td>
				<td width="200">${APdto.wishcnt}</td>
			</tr>
			
			<tr> 
				<td width="200">조회수</td>
				<td width="200">${APdto.readcnt}</td>
			</tr>
			
			<tr>
				<td>
					<select id="option" name="option">
						<option value="-------">-------</option>
							<c:forEach var="option" items="${option}">
								<option value="${option.optionnum}">상품명 : ${option.optionname} 가격 : ${option.price} 재고 : ${option.productcount}
								</option>
							</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td>
					<input type="hidden" id="selectedOptionNum" name="selectedOptionNum" value="" />
					<input type="button" value="농부상점가기" onclick="javascript:window.location='/product/productMyShop?follow=${follow}'">
				</td>
			</tr>
		</table>												
	</body>
</html>