<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>정보</title>
		<link rel="icon" href="/resources/img/logo.png">
	</head>
	<body>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<c:if test="${!isUser}">
			<script type="text/javascript">
				alert("상점주인만 확인할 수 있습니다..");
				history.go(-1);
			</script>
		</c:if>
		<div style="display: flex; margin-left: 30vh;">
			<img src="/resources/file/profile/${shopDTO.image}" width="100px" height="100px" style="margin: 10px;">
			<div style="text-align: left;">
				<h1>${shopDTO.name}</h1>
				<span>가입일 : <fmt:formatDate value="${shopDTO.regdate}" pattern="yyyy년 MM월 dd일" /></span><br />
				<input type="button" value="내 상점 돌아가기" onclick="javascript:window.location='/product/productMyShop?username=${username}'" style="border: none;background-color: #369F36;color: #fff;">
			</div>
		</div>
		<hr />
		<div class="container" style="margin-top: 10vh; margin-bottom: 5vh;">
		 	<div class="row">
		 		<div class="col-md-6">
		 			 <div class="card" style="height: 15rem; text-align: left;">
		 			 	<h5 style="padding: 30px;">전체판매</h5>
						<h1 style="padding-left: 30px;">${shopDTO.total_payment}\</h1>
						<p style="padding-left: 30px;" class="text-muted">#전국 #딸기 랭킹 2위</p>
					</div>
				</div>
				<div class="col-md-6">
		 			 <div class="card" style="height: 15rem; text-align: left;">
		 			 	<h5 style="padding: 30px;">오늘 수익</h5>
						<h1 style="padding-left: 30px;">${todayPrice}\</h1>
					</div>
				</div>
				<div class="col-md-6">
		 			 <div class="card" style="height: 15rem; text-align: left;">
		 			 	<h5 style="padding: 30px;">상품등록개수</h5>
						<h1 style="padding-left: 30px;">${shopDTO.product_count}건</h1>
					</div>
				</div>
				<div class="col-md-6">
		 			 <div class="card" style="height: 15rem; text-align: left;">
		 			 	<h5 style="padding: 30px;">팔로위</h5>
						<h1 style="padding-left: 30px;">${shopDTO.followers}명</h1>
					</div>
				</div>
			</div>
		</div>
		<hr />
		<div style="margin-top: 5vh;">
			<h2>판매 날짜 차트</h2>
			<jsp:include page="/WEB-INF/views/user/myshop/shopchart.jsp" />
		</div>
		<br />
		<br />
		<br />
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>