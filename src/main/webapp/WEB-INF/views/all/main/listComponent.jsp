<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<div>
	<img src="/resources/realImage/${dto.image_filename}" width="300px" height="300px">
	<br />
	<h1>${dto.min_price}</h1>
	<h1>${dto.productname}</h1>
	<span>
		<label>${dto.area_name1} > ${dto.area_name2}</label> 
		${dto.username}
	</span>
	<img src="/resources/img/${dto.user_image}" width="100px" height="100px" />
	<br />
	<span>${dto.cate_name}</span>
	<br />
	<span>별점 : ${dto.avg_stars}(${dto.review_count}) , 조회수 : ${dto.readcnt}, 찜개수 : ${dto.wishcnt}</span>
	<br />
	<span><fmt:formatDate value="${dto.startdate}" dateStyle="long" type="date"/></span>
</div>