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
		<script src="/resources/js/productImg.js"></script>
		<link href="/resources/css/productInfo.css" rel="stylesheet" type="text/css">
   </head>
   <script>
      $(function() {
         var selectedOptionCount;
         var optionSelected = false; // 옵션 선택 여부를 나타내는 변수
         $("#Pdto").on("change", function() {
        	 console.log("-----");
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

         });
         
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

      }); 
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

           window.open('/product/reviewsDeletePro?productnum=' + productnum + '&myName=' + myName, '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top);
       }
           
   </script>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<body>
		<div class = "card-wrapper">
		  <div class = "card-p">
		    <!-- card left -->
		    <div class = "product-imgs">
		      <div class = "img-display">
		        <div class = "img-showcase">
		        <!-- 이미지 반복 1 -->
		        <c:forEach var="Images" items="${Images}">
			            <img src="/resources/realImage/${Images.filename}" alt = "image">
			    </c:forEach>
		        </div>
		      </div>
		      <div class = "img-select">
		      	<c:forEach var="Images" items="${Images}" varStatus="loop">
		        	<div class = "img-item">
		        <!-- 이미지 반복 2 + 개수 -->
		                <a href = "#" data-id="${loop.index + 1}">
			            <img src="/resources/realImage/${Images.filename}" alt = "image">
			            </a>
		        	</div>
		        </c:forEach>
		      </div>
		    </div>
		    <!-- card right -->
		    <div class = "product-content">
		      <div class="product-farmer">
		      	<b>${APdtoNAF.name}</b><p class="text-muted" style="font-size: 12px;">구독자 수 : ${APdtoNAF.followers}명</p><input type="button" class="sellbutton" value="💬판매자와 채팅" onclick="javascript:window.location='/chat/room" />
		      </div>
		      
		      <h4 class = "product-title">${APdto.productname}</h4>
		      <div style="clear:both;"></div>
		      <div class = "product-rating">
		        <i class = "fas fa-star"></i>
		        <i class = "fas fa-star"></i>
		        <i class = "fas fa-star"></i>
		        <i class="fas fa-star"></i>
		        <i class = "fas fa-star-half-alt"></i>
		        <span>${stars}(${cnt})</span>
		      </div>
			  <div style="clear:both;"></div>
		      <div class = "product-price">
		        <p class = "new-price">가격 : <span>${APdto.price}원</span></p>
		      </div>
		      <div style="clear:both;"></div>
			  <br />
		      <div class = "product-detail">
		        <p>택배 배송 : 3000원</p>
		        <div style="clear:both;"></div>
		        <p class="text-muted">5만원 이상 구매 시 무료배송</p>
		        <select id="Pdto" name="Pdto">
                    <option value="-------">-------</option>
                    <c:forEach var="Pdto" items="${Pdto}">
                        <c:if test="${(Pdto.productcount - Pdto.sellcount) != 0}">
                            <option value="${Pdto.optionnum}-${Pdto.productcount}">
                                ${Pdto.optionname} : ${Pdto.price}원
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
                 <div style="clear:both;" id="finish"></div>
		      </div>
			  <input type="hidden" id="selectedOptionNum" name="selectedOptionNum" value="" />
			  <div class = "purchase-info">
			  <c:if test="${!isUser}">
				<input type="button" class = "btn" value="찜하기" onclick="addToWishList()">
				<input type="button" class = "btn" value="농부상점가기" onclick="javascript:window.location='/product/productMyShop?username=${follow}'">
				<button type = "button" class = "btn"  onclick="addToCart()">
		         장바구니 담기 <i class = "fas fa-shopping-cart"></i>
		        </button>
			  </c:if>
			  <c:if test="${isUser}">
				<input type="button" class = "btn"  value="내상점가기" onclick="javascript:window.location='/product/productMyShop?username=${follow}'">
				<input type="button" class = "btn"  value="상품내리기" onclick="javascript:window.location='/product/deleteProduct?productnum=${productnum}&username=${follow}'">
			  </c:if>
			  </div>
		      
		    </div>
		  </div>
		</div>
		<hr />
		<div class="detail-content">
			${APdto.content}
		</div>
		<hr />
		<div class="product-review">
			<div class="container mx-auto mt-6" style="display: flex;">
				<div class="container review-container">
					<p>사용자 평점</p>
					<b>${stars}</b>
				</div>
				<div class="container review-container">
					<p>전체 리뷰 수</p>
					<b>${cnt}건</b>
				</div>
			</div>
			<!-- 여기에 리뷰 반복 -->
			<c:forEach var="allReviews" items="${allReviews}">
			<div class="reviews">
				<c:if test="${allReviews.username eq myName}">
					<button onclick="openDeleteWindow('${productnum}', '${myName}')">❌</button>
				</c:if>
				<div style="display: flex;"><b>${allReviews.username} </b><p> 구매한 상품 : ${allReviews.optionname}</p></div>
				<c:forEach begin="1" end="${allReviews.stars}" step="1" var="i">
					<i class="fas fa-star" style="color: #ffc83d;"></i>
				</c:forEach>
				<p>${allReviews.content}</p><p class="text-muted"><fmt:formatDate value="${allReviews.regdate}" dateStyle="short" type="date"/></p>
				<div style="clear:both;"></div>
				<div class="reviewsImg">
					<img src="/resources/file/reviews/${allReviews.filename}" width="70" height="70" />
				</div>
				<hr />
			</div>
			</c:forEach>
		</div>
		
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