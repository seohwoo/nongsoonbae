<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품 페이지</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
		<script src="/resources/js/jquery-3.7.1.min.js"></script>
	</head>

	<script>
		$(function() {
			var selectedOptionCount;
			
			$("#Pdto").on("change", function() {
				var selectedOptionVal = $("#Pdto").val();
				var selectedOptionNum = selectedOptionVal.split("-")[0];
					selectedOptionCount = selectedOptionVal.split("-")[1];
				$("#selectedOptionNum").val(selectedOptionNum);
				
				var selectedOptionText = $("#Pdto option:selected").text();
                var newRow = $("<tr>").append($("<td>").text(selectedOptionText));
                
            	// 증가, 감소 버튼 추가
                var increaseButton = $("<button>").text("+").click(increaseQuantity);
                var decreaseButton = $("<button>").text("-").click(decreaseQuantity);
                var numberText = $("<input type='text' name='count' id='count' value='1' readonly>");
                
                newRow.append($("<td>").append(increaseButton).append(numberText).append(decreaseButton));
                $("#finish").append(newRow);
			})
			
			function increaseQuantity(e) {
				
				if(parseInt(selectedOptionCount) > e.target.nextElementSibling.value) {
					e.target.nextElementSibling.value = parseInt(e.target.nextElementSibling.value)+1 ;
				}else{
					alert("재고 수가 부족합니다.");
				}
			}
			

            function decreaseQuantity(e) {
            	count = parseInt(e.target.previousElementSibling.value);
            	if(count > 1){
            		e.target.previousElementSibling.value = parseInt(e.target.previousElementSibling.value)-1 ;
            	}
            var number = e.target.nextElementSibling.value;
            }
		})


		
		
		
		
		function addToCart() {
			var pdtoVal = $("#Pdto").val();
			if ('-------' == pdtoVal) {
				alert("상품 옵션을 선택해주세요.");
			} else {
				var optionnum = $("#selectedOptionNum").val();
				var productnum = ${productnum};
				window.location = '/product/productShoppingPro?follow=${follow}&productnum=' + productnum + '&optionnum=' + $('#selectedOptionNum').val() + '&count=' + $('#count').val();
			}
		}

		
		function openReviewWindow() {
			var pdtoVal= $("#Pdto").val();
			if('-------' == pdtoVal){
				alert("상품 옵션을 선택해주세요.");
			}else{
			var optionnum = $("#selectedOptionNum").val();
			var productnum = ${productnum};
			var reviewWindow = window.open('/product/productReview?optionnum='+optionnum + '&productnum='+productnum, '_blank', 'width=400,height=300,resizable=yes');
			}
		}
		


		
	</script>
	
	<body>
		<table border="1px" style="text-align: center;" id="finish">
			<tr> 
				<td>농부 이름</td>
				<td>${APdtoNAF.name}</td>
			</tr>

			<tr> 
				<td>농장 주소</td>
				<td>${address}</td>
			</tr>			

			<tr> 
				<td>구독자 수</td>
				<td>${APdtoNAF.followers}</td>
			</tr>			

			<tr> 
				<td>상품 이름</td>
				<td>${APdto.productname}</td>
			</tr>			

			<tr> 
				<td>상품 가격</td>
				<td>${APdto.price}</td>
			</tr>

			<tr>
			    <td>상품 사진</td>
			    <td>
			        <c:forEach var="Images" items="${Images}">
			            <img src="/resources/realImage/${Images.filename}" width="100" height="100">
			        </c:forEach>
			    </td>
			</tr>
			
			<tr>
				<td>리뷰수 / 평점</td>
				<td>${cnt}개  ${stars}/5</td>
			<tr>
			
				<td>상품 옵션</td>
				<td>
					<select id="Pdto" name="Pdto">
						<option value="-------">-------</option>
							<c:forEach var="Pdto" items="${Pdto}">
								<option value="${Pdto.optionnum}-${Pdto.productcount}">
									상품명 : ${Pdto.optionname} 가격 : ${Pdto.price} 재고 : ${Pdto.productcount}
								</option>
							</c:forEach>
					</select>
				</td>
			</tr>
		</table>
<!-- ----------- -->
		<input type="hidden" id="selectedOptionNum" name="selectedOptionNum" value="" />
		<input type="button" value="찜하기" onclick="javascript:window.location='/product/productPickPro?follow=${follow}&productnum=${productnum}&optionnum='+ $('#selectedOptionNum').val()">
		<input type="button" value="농부상점가기" onclick="javascript:window.location='/product/productMyShop?username=${follow}'">
		<input type="button" value="장바구니담기" onclick="addToCart()">
		<button onclick="openReviewWindow()">리뷰작성</button>	
	
		<br /><br /><br /><br /> <hr /> <br />
		<table border="1" style="text-align: center;">
			<tr>
				<td>이름</td>
				<td>상품 이름</td>
				<td>별점</td>
				<td>작성일</td>
				<td>content</td>
			</tr>		
			
			<c:forEach var="Rdto" items="${Rdto}">
				<tr>
					<td>${Rdto.username}</td>
					<td>${Rdto.optionname}</td>
					<td>
						<c:forEach begin="1" end="${Rdto.stars}" step="1" var="i">
							<i class="fas fa-star" style="color: #ffc83d;"></i>
						</c:forEach>
					</td>
					<td><fmt:formatDate value="${Rdto.regdate}" dateStyle="short" type="date"/></td>
					<td>${Rdto.content}</td>
				</tr>
			</c:forEach>
		</table>
		
		<br /><br /><br /><br /> <hr /> <br />
		<tr>${APdto.content}</tr>
		
	</body>
</html>

<style>
	.stars1 {
    	font-size: 30px;
    	cursor: pointer;
	}
	.stars1 .star {
		color: #FFA500;
    	transition: color 0.3s;
	}
</style>