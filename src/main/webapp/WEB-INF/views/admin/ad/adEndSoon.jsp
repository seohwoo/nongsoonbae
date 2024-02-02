<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>광고종료예정</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	    input[type="submit"] {
	        background-color: #4CAF50;
	        color: white;
	        padding: 10px 20px;
	        border: none;
	        border-radius: 4px;
	        cursor: pointer;
	        font-size: 16px;
	    }
	    input[type="submit"]:hover {
	        background-color: #45a049;
	    }
	</style>
	<script>
    function confirmEnd() {
        return confirm("종료하시겠습니까?");
    }
	</script>
	</head>
	<body>
	<%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
	<%@include file="/WEB-INF/views/admin/ad/adNav.jsp"%>
	<h2>광고종료예정상품 (${endSoonCnt})</h2>
	<c:forEach var="list" items="${endSoonList}">
	    <div class="adList">
	       <form action="/admin/adEndPro" method="post" onsubmit="return confirmEnd();">
	        <input type="hidden" name="username" value="${list.username}"> 
	        <input type="hidden" name="productnum" value="${list.productnum}" />
	        <input type="hidden" name="num" value="${list.num}" />
	            <h2> ${list.productname} (${list.productnum}) </h2>
	            <h3> 판매자 ${list.username}</h3>
	            <h3> 신청기간 ${list.days}일 (단가 ${list.price}원) </h3>
	            <div class="day-meta">
	                <p> 광고기간: 
	                <fmt:formatDate value="${list.startdate}" pattern="yyyy년 MM월 dd일" /> 
	                ~ <fmt:formatDate value="${list.enddate}" pattern="yyyy년 MM월 dd일" /> 
	                (${list.days}일)
	                </p>
	            </div>
	            <input type="submit" value="광고종료"  />
	        </form>             
	    </div>
	</c:forEach>
	<script type="text/javascript">
		$(document).ready(function() {
	        var status = <c:out value="${endAdStatus}" default="-1" />;
	        if (status !== -1) {
	            if (status === 1) {
	                alert("해당 상품의 광고가 종료되었습니다.");
	            } else if (status === 0) {
	                alert("광고 내리기에 실패하였습니다.");
	            }
	        }
	    });
	</script>	
	</body>
</html>