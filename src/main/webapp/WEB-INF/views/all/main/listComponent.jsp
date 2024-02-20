<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


    <div class="col-md-3">
      <a href="/product/productInfo?productnum=${dto.productnum}&follow=${dto.username}">
	      <div class="card" style="width: 18rem;">
	 	  	<img src="/resources/realImage/${dto.image_filename}" width="300px" height="300px" class="card-img-top" alt="...">
	 	  	<div class="card-body">
		  		<h5 class="card-title">${dto.productname}</h5>
		    	<p class="card-text"><span><b>${dto.min_price}</b>원</span></p>
		    	<p class="card-text"><span>★ ${dto.avg_stars}(${dto.review_count})</span><b class="text-muted" style="font-size: 12px;"> 조회수 : ${dto.readcnt} 찜 : ${dto.wishcnt}</b></p>
		        <p class="card-text"><span style="font-size: 14px;"><fmt:formatDate value="${dto.startdate}" dateStyle="long" type="date"/> 등록</span></p>
		    	<div class="farmer">
		    	<p class="card-subtitle mb-2 text-muted h6"><label>${dto.area_name1} > ${dto.area_name2}</label><b>${dto.user_name}</b></p>
				<a href="/product/productMyShop?username=${dto.username}">
					<img src="/resources/img/${dto.user_image}" width="80px" height="80px" style="" /></div>
				</a>
	  		</div>
	  	  </div>
  		</a>
 	</div>
  