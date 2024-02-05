<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>광고중인 상품</title>
	<style>
	    body {
	        font-family: Arial, sans-serif;
	        background-color: #f4f4f4;
	        margin: 0;
	        padding: 20px;
	    }
	    .adList {
	        background-color: #fff;
	        border: 1px solid #ddd;
	        padding: 20px;
	        margin-bottom: 20px;
	        border-radius: 5px;
	        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	    }
	    h2, h3 {
	        color: #333;
	    }
	    .day-meta {
	        margin-top: 10px;
	        color: #666;
	    }
	    input[type="button"] {
	        background-color: #4CAF50;
	        color: white;
	        padding: 10px 20px;
	        border: none;
	        border-radius: 4px;
	        cursor: pointer;
	        font-size: 16px;
	    }
	    input[type="button"]:hover {
	        background-color: #45a049;
	    }
	</style>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	<body>
	<%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
	<%@include file="/WEB-INF/views/admin/ad/adNav.jsp"%>
	<div class="container">
		<div class="header">
			<h2>광고중인 상품(${adIngCnt}) </h2>			
		</div>
		<c:if test="${adIngCnt > 0}">
			<c:forEach var="list" items="${list}">
				<div class="adList">
					<h5>No.${list.num}</h5>
					<h2> ${list.p_productname} (${list.productnum}) </h2>
                	<h3> 판매자 ${list.username}</h3>
                	<p> 광고기간: 
	                <fmt:formatDate value="${list.startdate}" pattern="yyyy년 MM월 dd일" /> 
	                ~ <fmt:formatDate value="${list.enddate}" pattern="yyyy년 MM월 dd일" /> 
	                (${list.days}일)
	                </p>
                <h3> 신청기간 ${list.days}일 (단가 ${list.price}원) </h3>
           		</div>
			</c:forEach>
		</c:if>
		<div class="pagination">
			<c:if test="${startPage > 10}">
				<form action="/admin/adIng" method="post">
					<input type="hidden" name="pageNum" value="${startPage-10}">
					    <button type="submit">이전</button>
				</form>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
			  <form action="/admin/adIng" method="post">
				   <input type="hidden" name="pageNum" value="${i}">
				   <button type="submit">${i}</button>
				</form>	      
			</c:forEach>
				    <c:if test="${endPage < pageCount}">
			    <form action="/admin/adIng" method="post">
				            <input type="hidden" name="pageNum" value="${startPage+10}">
				            <button type="submit">다음</button>
				        </form>
				    </c:if>
				</div>
			</div>	     
	</body>
</html>