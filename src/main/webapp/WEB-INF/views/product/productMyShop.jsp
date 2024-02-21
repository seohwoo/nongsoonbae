<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>내 상점</title>
		<link rel="icon" href="/resources/img/logo.png">
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4a59143e26d58362551774373cb766b2&libraries=services"></script>
		<style type="text/css">
    		p {

    		
    		}
	    </style>
	    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	</head>

	<script>
		 function openNewAdWindow() {
		        // 광고신청 페이지를 새 창으로 열기
		        var url = '/user/adMain'; // 새 창에서 열 페이지의 URL
		        var width = 600; // 새 창의 너비
		        var height = 750; // 새 창의 높이
	
		        // 화면 중앙에 위치하도록 계산
		        var left = (window.innerWidth - width) / 2;
		        var top = (window.innerHeight - height) / 2;
	
		        window.open(url, '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top);
		    }
	    function openNewWindow() {
	        // 새 창을 열기
	        var width = 460;
	        var height = 300;
	
	        // 화면 중앙에 위치하도록 계산
	        var left = (window.innerWidth - width) / 2;
	        var top = (window.innerHeight - height) / 2;
	
	        window.open('/product/deleteShoplist', '_blank', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top);
	    }
	    
	    $(document).ready(function () {
	    	$('#chat').on('click', function () {
	    		 window.open('/chat/create?follow=' + "${follow}" , '_blank', 'width=600,height=1200');
	    	});
	    });
	    
	</script>
	
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
		<c:if test="${status==0}">
			<form action="/product/createProduct" method="post" style="min-height: 500px;">
				<h3>아직 내 상점이 없습니다. 지금 바로 개설해보세요!</h3>
				<input type="submit" value="나의 상점 만들기">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</c:if>
		
		<c:if test="${status!=0}">
		
		<div class="background"></div>
		<div class="seller">
			<img class="sellimg" src="/resources/file/profile/${SLdto.image}" /><br />
				<b class="h3">${SLdto.name}</b>
				<p>${SLdto.shopname}</p>
				<div class="container">
					<c:if test="${Session!=follow && isNotLogIn}">
						<input type="button" class="sellbutton" value="follow" onclick="javascript:window.location='/product/followPro?follow=${follow}'">
						<input type="button" class="sellbutton" id="chat" value="💬판매자와 채팅" />
					</c:if>
					<c:if test="${Session==follow}">
	
					<input type="button" class="sellbutton" value="판매량조회" onclick="javascript:window.location='/product/shopinfo?username=${follow}'">
					<c:if test="${isMembership && !quitMembership}">
						<input type="button" class="sellbutton" value="상품 등록" onclick="javascript:window.location='/membership/write?myName=${myName}'">
						<input type="button" class="sellbutton" value="멤버쉽해지" onclick="javascript:window.location='/user/membership'">
					</c:if>
					<c:if test="${!isMembership && !quitMembership}">
						<input type="button" class="sellbutton" value="상품 등록" onclick="javascript:window.location='/product/productWriteForm?myName=${myName}'">
						<input type="button" class="sellbutton" value="멤버쉽" onclick="javascript:window.location='/user/membership'">
					</c:if>
					<c:if test="${isMembership && quitMembership}">
						<input type="button" class="sellbutton" value="상품 등록" onclick="javascript:window.location='/membership/write?myName=${myName}'">
					</c:if>
					<input type="button" class="sellbutton" value="광고신청하기" onclick="openNewAdWindow()">
					</c:if>
				</div>
				<p class="text-muted" style="font-size: 12px;">관심 고객 수 : ${SLdto.followers}</p>
		</div>		
		<br /> <hr /> <br />	
		<div class="sellcontent">
			<table style="text-align: center;">
				<tr>
					<td colspan="5">${SLdto.shopcontent}</td>
				</tr>
				<tr>
					
					<td colspan="5">
					<c:forEach var="APdto" items="${APdto}"> 
							<div class="p-4" style="width: 900px;">
						      <div class="row g-0 border rounded overflow-hidden flex-md-row shadow-sm h-md-250 position-relative">
						        <div class="col p-4 d-flex flex-column position-static">
						          <strong class="d-inline-block mb-2 text-primary-emphasis">${APdto.productnum}</strong>
						          <h3 class="mb-0">${APdto.productname}</h3>
						          <div class="mb-1 text-body-secondary">${APdto.price}원</div></br>
						          <a href="/product/productInfo?productnum=${APdto.productnum}&follow=${APdto.username}" class="icon-link gap-1 icon-link-hover stretched-link">
						            상품 페이지 이동 >
						          </a>
						        </div>
						        <div class="col-auto d-none d-lg-block">
						          <img src="/resources/realImage/${APdto.filename}" width="200" height="190" />
						        </div>
						      </div>
						    </div>
						    </c:forEach>
					</td>	
					
				</tr>
				
				
			</table>
		</div>
		<div class="map">
			<p>소재지 : ${address}</p>
			<div id="map"></div>
		</div>
		<c:if test="${Session==follow}">
			<button class="sellbutton" onclick="openNewWindow()">상점 폐쇄하기</button>
		</c:if>
		</c:if>
		
		<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = {
			        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };  
			
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
			
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch('${SLdto.address})', function(result, status) {
			
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
			
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });
			
			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0;">${SLdto.shopname}</div>'
			
			        });
			        infowindow.open(map, marker);
			
			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			});    
		</script>
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>