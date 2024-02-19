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
			var optionSelected = false; // 옵션 선택 여부를 나타내는 변수
			$("#Pdto").on("change", function() {
				var selectedOptionVal = $("#Pdto").val();
				var selectedOptionNum = selectedOptionVal.split("-")[0];
					selectedOptionCount = selectedOptionVal.split("-")[1];
				$("#selectedOptionNum").val(selectedOptionNum);
				
				var selectedOptionText = $("#Pdto option:selected").text();
                var newRow = $("<tr>").append($("<td>").text(selectedOptionText));

                // 이미 옵션이 선택된 경우 경고 메시지 표시
                if (optionSelected) {
                    alert("상품은 한 가지만 선택할 수 있습니다.");
                    return; // 선택한 옵션 초기화 없이 종료
                }
                optionSelected = true; // 옵션이 선택되었음을 표시
                
                
            	// 증가, 감소 버튼 추가
                var increaseButton = $("<button>").text("+").click(increaseQuantity);
                var decreaseButton = $("<button>").text("-").click(decreaseQuantity);
                var deleteButton = $("<button>").text("X").click(deleteRow);
                var numberText = $("<input type='text' name='count' id='count' value='1' readonly>");
                
                newRow.append($("<td>").append(increaseButton).append(numberText).append(decreaseButton).append(deleteButton));
                $("#finish").append(newRow);
			})
			
			function increaseQuantity(e) {
				var selectedOptionVal = $("#Pdto").val();
				    selectedOptionCount = selectedOptionVal.split("-")[1];
				
				if(parseInt(selectedOptionCount) > parseInt(e.target.nextElementSibling.value)) {
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

            function deleteRow() {
                $(this).closest('tr').remove();
                optionSelected = false; // 옵션 선택 초기화
                $("#Pdto").val("-------"); // 옵션 값을 "-------"으로 설정
            }
		
		
		})


		function logedIn() {
			 
		}
		
		function addToWishList() {
			if(${isLogedIn} === false) {
				alert("로그인 해주세요!!");
				window.location = '/member/form';
			}else{
				window.location = '/product/productPickPro?follow=${follow}&productnum=${productnum}&optionnum='+ $('#selectedOptionNum').val();
			} 
		}
		
		
		function addToCart() {
			if(${isLogedIn} === false) {
				alert("로그인 해주세요!!");
				window.location = '/member/form';
			}else{
				var pdtoVal = $("#Pdto").val();
				var selectedOptionVal = $("#Pdto").val();
				    selectedOptionCount = selectedOptionVal.split("-")[1];
				if ('-------' == pdtoVal) {
					alert("상품 옵션을 선택해주세요.");
			    } else if (parseInt(selectedOptionCount) === 0) {
			        alert("상품 재고가 없습니다.");	
				} else {
					var optionnum = $("#selectedOptionNum").val();
					var productnum = ${productnum};
					window.location = '/product/productShoppingPro?follow=${follow}&productnum=' + productnum + '&optionnum=' + $('#selectedOptionNum').val() + '&count=' + $('#count').val();
				}
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
		
	    function openDeleteWindow(productnum, myName) {
	        // 새 창을 열기
	        var width = 460;
	        var height = 300;

	        // 화면 중앙에 위치하도록 계산
	        var left = (window.innerWidth - width) / 2;
	        var top = (window.innerHeight - height) / 2;

	        window.open('/product/reviewsDelete?productnum=' + productnum + '&myName=' + myName, '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top);
	    }
				
	</script>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<body>
	
		<table border="1px" style="text-align: center; margin-left: auto; margin-right: auto;" id="finish">
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
			
			<!-- TEST -->
			
				<td>상품 옵션</td>
				<td>
				    <select id="Pdto" name="Pdto">
				        <option value="-------">-------</option>
				        <c:forEach var="Pdto" items="${Pdto}">
				            <c:if test="${(Pdto.productcount - Pdto.sellcount) != 0}">
				                <option value="${Pdto.optionnum}-${Pdto.productcount}">
				                    상품명 : ${Pdto.optionname} 가격 : ${Pdto.price} 재고 : ${Pdto.productcount - Pdto.sellcount}
				                </option>
				            </c:if>
				        </c:forEach>
				    </select>
				</td>
			</tr>
		</table>
<!-- ----------- -->
		<input type="hidden" id="selectedOptionNum" name="selectedOptionNum" value="" />
		<c:if test="${!isUser}">
			<input type="button" value="찜하기" onclick="addToWishList()">
			<input type="button" value="농부상점가기" onclick="javascript:window.location='/product/productMyShop?username=${follow}'">
			<input type="button" value="장바구니담기" onclick="addToCart()">
			<button onclick="openReviewWindow()">리뷰작성</button>	
		</c:if>
		<c:if test="${isUser}">
			<input type="button" value="내상점가기" onclick="javascript:window.location='/product/productMyShop?username=${follow}'">
			<input type="button" value="상품내리기" onclick="javascript:window.location='/product/deleteProduct?productnum=${productnum}&username=${follow}'">
		</c:if>
	
		<br /><br /><br /><br /> <hr /> <br />
		<table border="1" style="text-align: center;">
			<tr>
				<td>이름</td>
				<td>상품 이름</td>
				<td>별점</td>
				<td>작성일</td>
				<td>content</td>
				<td>사진</td>
				<td>리뷰삭제</td>
			</tr>		
			
			<c:forEach var="allReviews" items="${allReviews}">
				<tr>
					<td>${allReviews.username}</td>
					<td>${allReviews.optionname}</td>
					<td>
						<c:forEach begin="1" end="${allReviews.stars}" step="1" var="i">
							<i class="fas fa-star" style="color: #ffc83d;"></i>
						</c:forEach>
					</td>
					<td><fmt:formatDate value="${allReviews.regdate}" dateStyle="short" type="date"/></td>
					<td>${allReviews.content}</td>
					<td>
						<img src="/resources/file/reviews/${allReviews.filename}" width="70" height="70">
					</td>
					
					<td>
						<c:if test="${allReviews.username eq myName}">
							<button onclick="openDeleteWindow('${productnum}', '${myName}')">리뷰 삭제</button>
						</c:if>
					</td>			
			</c:forEach>  
		</table>
		
		<br /><br /><br /><br /> <hr /> <br />
		<tr>${APdto.content}</tr>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
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