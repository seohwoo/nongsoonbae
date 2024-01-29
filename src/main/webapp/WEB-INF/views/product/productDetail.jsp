<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>	
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
			
			<c:forEach var="Idto" items="${Idto}">
				<img src="/resources/realImage/${Idto.filename}">
			</c:forEach>
			<tr>
				<td>
					<input type="hidden" id="selectedOptionNum" name="selectedOptionNum" value="" />
					<input type="button" value="찜하기" onclick="javascript:window.location='/product/productPickPro?follow=${follow}&productnum=${productnum}&optionnum='+ $('#selectedOptionNum').val()">
				</td>
			</tr>
		</table>												
	</body>
</html>