<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
	<head>
	<meta charset="UTF-8">
	<title>광고신청확인하기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
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
	<script type="text/javascript">
	function submitFormWithAction(form, action, confirmMessage = null) {
		console.log("Function called with action: " + action);
	    if (confirmMessage === null || confirm(confirmMessage)) {
	        form.action = action;
	        form.submit();
	    }
	}
	</script>
	<body>
	<%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
	<%@include file="/WEB-INF/views/admin/ad/adNav.jsp"%>
	<h2>광고신청 (${submitCnt})</h2>
	<c:forEach var="list" items="${adList}">
        <div class="adList">
           <form method="post" >
            <input type="hidden" name="username" value="${list.username}"> 
            <input type="hidden" name="productnum" value="${list.productnum}" />
            <input type="hidden" name="num" value="${list.num}" />
            <input type="hidden" name="days" value="${list.days}" />
                <h5>No.${list.num}</h5>
                <h2> ${list.productname} (${list.productnum}) </h2>
                <h3> 판매자 ${list.username}</h3>
                <h3> 신청기간 ${list.days}일 (단가 ${list.price}원) </h3>
                <div class="day-meta">
                    <p> 신청날짜: 
                    <fmt:formatDate value="${list.submitdate}" pattern="yyyy년 MM월 dd일" /> 
                    </p>
                </div>
                <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="formattedDate"/>
				<input type="hidden" name="startDate" value="${formattedDate}" />
                <input type="button" value="승인" onclick="submitFormWithAction(this.form, '/admin/adPro', '광고를 승인하시겠습니까?');">
       			<input type="button" value="거절" onclick="submitFormWithAction(this.form, '/admin/adNoPro', '광고를 반려하시겠습니까?');">
            </form>             
        </div>
    </c:forEach>
    <script type="text/javascript">
		$(document).ready(function() {
	        var status = <c:out value="${adStatus}" default="-1" />;
	       	var noStatus = <c:out value="${noAdStatus}" default="-1" />;
	        	
		    if (noStatus !== -1) {
		         if (noStatus === 1) {
		            alert("신청 반려가 완료되었습니다.");
		         } else if (noStatus === 0) {
		             alert("신청 반려에 실패하였습니다.");
		         }
		      }    	
	        if (status !== -1) {
	            if (status === 1) {
	                alert("승인이 완료되었습니다.");
	            } else if (status === 0) {
	                alert("승인에 실패하였습니다.");
	            }
	        }
	    });
	</script>		
	</body>
</html>