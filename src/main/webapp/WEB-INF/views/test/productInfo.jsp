<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class = "card-wrapper">
		  <div class = "card-p">
		    <!-- card left -->
		    <div class = "product-imgs">
		      <div class = "img-display">
		        <div class = "img-showcase">
		          <img src = "https://fadzrinmadu.github.io/hosted-assets/product-detail-page-design-with-image-slider-html-css-and-javascript/shoe_1.jpg" alt = "shoe image">
		          <img src = "https://fadzrinmadu.github.io/hosted-assets/product-detail-page-design-with-image-slider-html-css-and-javascript/shoe_2.jpg" alt = "shoe image">
		          <img src = "https://fadzrinmadu.github.io/hosted-assets/product-detail-page-design-with-image-slider-html-css-and-javascript/shoe_3.jpg" alt = "shoe image">
		          <img src = "https://fadzrinmadu.github.io/hosted-assets/product-detail-page-design-with-image-slider-html-css-and-javascript/shoe_4.jpg" alt = "shoe image">
		        </div>
		      </div>
		      <div class = "img-select">
		        <div class = "img-item">
		          <a href = "#" data-id = "1">
		            <img src = "https://fadzrinmadu.github.io/hosted-assets/product-detail-page-design-with-image-slider-html-css-and-javascript/shoe_1.jpg" alt = "shoe image">
		          </a>
		        </div>
		        <div class = "img-item">
		          <a href = "#" data-id = "2">
		            <img src = "https://fadzrinmadu.github.io/hosted-assets/product-detail-page-design-with-image-slider-html-css-and-javascript/shoe_2.jpg" alt = "shoe image">
		          </a>
		        </div>
		        <div class = "img-item">
		          <a href = "#" data-id = "3">
		            <img src = "https://fadzrinmadu.github.io/hosted-assets/product-detail-page-design-with-image-slider-html-css-and-javascript/shoe_3.jpg" alt = "shoe image">
		          </a>
		        </div>
		        <div class = "img-item">
		          <a href = "#" data-id = "4">
		            <img src = "https://fadzrinmadu.github.io/hosted-assets/product-detail-page-design-with-image-slider-html-css-and-javascript/shoe_4.jpg" alt = "shoe image">
		          </a>
		        </div>
		      </div>
		    </div>
		    <!-- card right -->
		    <div class = "product-content">
		      <div class="product-farmer">
		      	<b>상점 이름</b><p class="text-muted" style="font-size: 12px;">구독자 수 : 0명</p><input type="button" class="sellbutton" value="💬판매자와 채팅" onclick="javascript:window.location='/chat/room" />
		      </div>
		      
		      <h4 class = "product-title">상품 이름</h4>
		      <div style="clear:both;"></div>
		      <div class = "product-rating">
		        <i class = "fas fa-star"></i>
		        <i class = "fas fa-star"></i>
		        <i class = "fas fa-star"></i>
		        <i class="fas fa-star"></i>
		        <i class = "fas fa-star-half-alt"></i>
		        <span>4.7(21)</span>
		      </div>
			  <div style="clear:both;"></div>
		      <div class = "product-price">
		        <p class = "new-price">가격 : <span>15,000원</span></p>
		      </div>
		      <div style="clear:both;"></div>
			  <br />
		      <div class = "product-detail">
		        <p>택배 배송 : 3000원</p>
		        <div style="clear:both;"></div>
		        <p class="text-muted">5만원 이상 구매 시 무료배송</p>
		        <div style="clear:both;"></div>
		        <select id="Pdto" name="Pdto">
				   <option value="-------">상품 옵션 선택</option>
				   <option value="선택1">상품명 : ㄷㄷ 가격 : +1000 재고 : 1개</option>
				</select>
		      </div>
		
		      <div class = "purchase-info">
		        <button type = "button" class = "btn">
		         장바구니 담기 <i class = "fas fa-shopping-cart"></i>
		        </button>
		        <button type = "button" class = "btn">찜하기</button>
		      </div>
		    </div>
		  </div>
		</div>
		<hr />
		<div class="detail-content">
			제품 설명란
		</div>
		<hr />
		<div class="product-review">
			<div class="container mx-auto mt-6" style="display: flex;">
				<div class="container">
					<p>사용자 평점</p>
					<p>4.7</p>
				</div>
				<div class="container">
					<p>전체 리뷰 수</p>
					<p>2건</p>
				</div>
			</div>
			<div class="reviews">
				<p>구매 상품 : 어쩌고</p>
				<p>리뷰 내용 길게 어쩌고저쩌고</p>
				<div class="reviewsImg">
					<img alt="reviewImg" src="/resources/img/logo.png"/><img alt="reviewImg" src="/resources/img/logo.png"/><img alt="reviewImg" src="/resources/img/logo.png"/><img alt="reviewImg" src="/resources/img/logo.png"/>
				</div>
			</div>
		</div>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>