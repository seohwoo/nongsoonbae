<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


    <div class="col-md-3">
      <a href="/product/productInfo?productnum=${ad.productnum}&follow=${ad.username}">
	      <div class="card" style="width: 18rem;">
	 	  	<img src="/resources/realImage/${ad.image_filename}" width="300px" height="300px" class="card-img-top" alt="...">
	 	  	<div class="card-body">
		  		<h5 class="card-title">✨ ${ad.productname}</h5>
		    	<p class="card-text"><span>별점 : ${ad.avg_stars}(${ad.review_count}) , 조회수 : ${ad.readcnt}, 찜개수 : ${ad.wishcnt}</span></p>
		        <p class="card-text"><span><fmt:formatDate value="${ad.startdate}" dateStyle="long" type="date"/></span></p>
		    	<div class="farmer">
		    	<p class="card-subtitle mb-2 text-muted h6"><label>${ad.area_name1} > ${ad.area_name2}</label><b>${ad.username}</b></p>
				<a href="/product/productMyShop?username=${ad.username}">
					<img src="/resources/img/${ad.user_image}" width="80px" height="80px" style="" /></div>
				</a>
	  		</div>
	  	  </div>
  		</a>
 	</div>
  