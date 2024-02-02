<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>일단 상점 메인</title>
	</head>
                              
	<script>
	    function openNewWindow() {
	        // 새 창을 열기
	        var width = 460;
	        var height = 300;
	
	        // 화면 중앙에 위치하도록 계산
	        var left = (window.innerWidth - width) / 2;
	        var top = (window.innerHeight - height) / 2;
	
	        window.open('/product/deleteShoplist', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top);
	    }
	</script>
	   
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="table" style="height: 800px">
			<c:if test="${status==0}">
				<form action="/product/createProduct?myName=${myName}" method="post">
					<h3>아직 내 상점이 없습니다. 지금 바로 개설해보세요!</h3>
					<input type="submit" value="나의 상점 만들기">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</c:if>
			
			<c:if test="${status!=0}">
			<h3>${myName} 페이지</h3><br />
				<button onclick="openNewWindow()">상점 폐쇄하기</button>
				<input type="button" value="나의 상점 정보" onclick="javascript:window.location='/product/productMyShop?username=${username}'">
				<input type="button" value="상품 등록" onclick="javascript:window.location='/product/productWriteForm?myName=${myName}'">
				<input type="button" value="나의 상점 관리" onclick="javascript:window.location='/product/myProduct'">
				<input type="button" value="전체 상품 조회" onclick="javascript:window.location='/product/allProduct'">
				<br />
				<div>
					<div class="col p-4 d-flex flex-column position-static bg-body-tertiary border rounded-3" style="width: 30%; margin-left: auto; margin-right: auto; margin-top: 5%">
			          <strong class="d-inline-block mb-2 text-primary">Membership</strong>
			          <h3 class="mb-0">멤버쉽 가입</h3>
			          <div class="mb-1 text-muted"><hr></div>
			          <div>더욱 상세한 차트를 통해 수익을 올려보세요.</div>
			          <p class="card-text mb-auto"></p>
			          <a class="text-primary" href="../user/membership">이동하기 ></a>
			        </div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</c:if>
			
		</div>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>