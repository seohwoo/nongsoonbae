<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


    <div class="col-md-3">
      <a href="/product/productInfo?productnum=${dto.productnum}&follow=${dto.username}">
	      <div class="card" style="width: 18rem;">
	 	  	<img src="/resources/realImage/${dto.image_filename}" width="300px" height="300px" class="card-img-top" alt="...">
	 	  	<div class="card-body">
		  		<h5 class="card-title">${dto.productname}</h5>
		    	<p class="card-text"><span>별점 : ${dto.avg_stars}(${dto.review_count}) , 조회수 : ${dto.readcnt}, 찜개수 : ${dto.wishcnt}</span></p>
		        <p class="card-text"><span><fmt:formatDate value="${dto.startdate}" dateStyle="long" type="date"/></span></p>
		    	<div class="farmer">
		    	<p class="card-subtitle mb-2 text-muted h6"><label>${dto.area_name1} > ${dto.area_name2}</label><b>${dto.username}</b></p>
				<a href="/product/productMyShop?username=${dto.username}">
					<img src="/resources/img/${dto.user_image}" width="80px" height="80px" style="" /></div>
				</a>
	  		</div>
	  	  </div>
  		</a>
 	</div>
  