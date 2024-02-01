<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>내 상점</title>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4a59143e26d58362551774373cb766b2&libraries=services"></script>
		<style type="text/css">
    		p {

    		
    		}
	    </style>
	</head>
	
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="background"></div>
		<div class="seller">
			<img class="sellimg" src="/resources/file/profile/${SLdto.image}" /><br />
				<b class="h3">${SLdto.name}</b>
				<p>${SLdto.shopname}</p>
				<div class="container">
					<input type="button" class="sellbutton" value="follow" onclick="javascript:window.location='/product/productMain'">
					<input type="button" class="sellbutton" value="💬판매자와 채팅" onclick="javascript:window.location='/chat/room" />
				</div>
				<p class="text-muted" style="font-size: 12px;">관심 고객 수 : ${SLdto.followers}</p>
		</div>		
		<br /> <hr /> <br />	
		<div class="sellcontent">
			<table style="text-align: center;">
				<tr>
					<td>소개 : </td><td colspan="5">${SLdto.shopcontent}</td>
				</tr>
				<tr>
					<td>판매 상품</td>
					<td colspan="5">
					
							<div class="p-4">
						      <div class="row g-0 border rounded overflow-hidden flex-md-row shadow-sm h-md-250 position-relative">
						        <div class="col p-4 d-flex flex-column position-static">
						          <strong class="d-inline-block mb-2 text-primary-emphasis">World</strong>
						          <h3 class="mb-0">Featured post</h3>
						          <div class="mb-1 text-body-secondary">Nov 12</div>
						          <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
						          <a href="#" class="icon-link gap-1 icon-link-hover stretched-link">
						            Continue reading
						            <svg class="bi"><use xlink:href="#chevron-right"></use></svg>
						          </a>
						        </div>
						        <div class="col-auto d-none d-lg-block">
						          <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
						        </div>
						      </div>
						    </div>
					
					</td>	
				</tr>
				
				<c:forEach var="APdto" items="${APdto}"> 
					<tr>
						<td>
							<img src="/resources/realImage/${APdto.filename}">
						</td>
						<td>
							<a href="/product/productDetail?productnum=${APdto.productnum}">${APdto.productnum}</a>	
						</td>										
						<td>
							${APdto.productname}	
						</td>
						<td>
							${APdto.price}
						</td>
						<td>
							${APdto.wishcnt}
						</td>
						<td>
							${APdto.readcnt}
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="map">
			<p>소재지 : ${address}</p>
			<div id="map"></div>
		</div>
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