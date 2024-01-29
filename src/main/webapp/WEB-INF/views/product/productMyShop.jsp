<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4a59143e26d58362551774373cb766b2&libraries=services"></script>
		<style type="text/css">
    		p {

    		
    		}
	    </style>
	</head>
	
	<body>
		<table width="600" border="1" style="text-align: center;">
			<img src="/resources/file/profile/${SLdto.image}" width="100px" height="100px" />
			
			<input type="button" value="follow" onclick="javascript:window.location='/product/productMain'">
			
			<tr> 
				<td width="200">농부이름</td>
				<td width="400"> 
					${SLdto.name}	
				</td>
			</tr>		
		
			<tr> 
				<td width="200">상점이름</td>
				<td width="400"> 
					${SLdto.shopname}	
				</td>
			</tr>

			<tr> 
				<td width="200">상점소개</td>
				<td width="400"> 
					${SLdto.shopcontent}	
				</td>
			</tr>		

			<tr> 
				<td width="200">상점주소</td>
				<td width="400"> 
					${address}	
				</td>
			</tr>

			<tr> 
				<td width="200">팔로우</td>
				<td width="400"> 
					${SLdto.followers}	
				</td>
			</tr>												
		</table>	
		
		<br /> <hr /> <br />	
		
		<table width="600" border="1" style="text-align: center;">
			<tr>
				<td width="200">사진</td>
				<td width="200">상품번호</td>
				<td width="200">상품이름</td>
				<td width="200">상품가격</td>
				<td width="200">찜한 수</td>
				<td width="200">조회수</td>				
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
		
		
		<div id="map" style="width:40%;height:240px;"></div>
		
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
	</body>
</html>