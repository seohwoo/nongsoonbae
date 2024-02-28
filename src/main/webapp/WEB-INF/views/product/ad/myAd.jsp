<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="/resources/img/logo.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>나의 광고</title>
<link rel="icon" href="/resources/img/logo.png">
	<style>
	    body {
	        font-family: Arial, sans-serif;
	        text-align: center;
	        margin: 0;
	        padding: 0;
	        background-color: #f4f4f4;
	        line-height: 1.0; /* 줄 간격 조정 */
	    }
	
	    .adList {
	        border: 2px solid #ddd; /* 테두리 추가 */
	        padding: 20px; /* 패딩 추가 */
	        margin: 20px auto; /* 상하 마진 추가 및 좌우 자동 */
	        width: 80%; /* 너비 설정 */
	        background-color: white; /* 박스 배경색 */
	        box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* 박스에 그림자 추가 */
	    }
	
	    h2, h3, h4, h5 {
	        color: #333;
	    }
	
	    h2 {
	        margin-top: 50px;
	    }
	
	    h3, h4, h5 {
	        margin-top: 20px;
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
	
	    .day-meta, .status-meta {
	        color: #666;
	        margin-top: 10px;
	    }
	    .status-green {
        color: green;
	    }
	    .status-blue {
	        color: blue;
	    }
	    .status-red {
	        color: red;
	    }
	</style>
	</head>
	<body>
	
		<c:if test="${myAdCnt == 0 }">
			<div class="adList">
		     	아직 신청한 광고가 없습니다. 
		     </div>
			<input type="button" value="돌아가기" onclick="javascript:window.location='/user/adMain'">
		</c:if>
	    <c:if test="${myAdCnt > 0 }">
			<h2>나의 광고 (${myAdCnt})</h2>
			<c:forEach var="list" items="${myAdList}">
			    <div class="adList">
			       <h2> ${list.productname} (${list.productnum}) </h2>
			       <h3> 신청기간 ${list.days}일 (단가 ${list.price}원) </h3>
			       <div class="day-meta">
			           <p> 신청날짜: 
			           <fmt:formatDate value="${list.submitdate}" pattern="yyyy년 MM월 dd일" /> 
			           </p>
			       </div>
			       <div class="status-meta">
			       	    <c:if test="${list.adstatus == 40 }">
						    <p class="status-green"> 현재 해당 상품 광고 중입니다. </p>
						</c:if>
						<c:if test="${list.adstatus == 41 }">
						    <p class="status-blue"> 관리자 검토 중 </p>
						</c:if>
						<c:if test="${list.adstatus == 42 }">
						    <p class="status-red"> 광고 일정에 따라 신청이 반려되었습니다. </p>
						</c:if>
						<c:if test="${list.adstatus == 43 }">
						    <p> 광고 일정이 종료된 상품입니다. </p>
						</c:if>

			       </div>
			    </div>
			</c:forEach>
			<input type="button" value="돌아가기" onclick="javascript:window.location='/user/adMain'">
			<input type="button" value="창 닫기" onclick="window.close();" >
		</c:if>
	<script type="text/javascript">
		$(document).ready(function() {
	        var status = <c:out value="${submitStatus}" default="-1" />;
	        if (status !== -1) {
	            if (status === 1) {
	                alert("신청이 완료되었습니다.");
	            } else if (status === 2){
	            	alert("이미 광고중이거나 광고 검토 진행 중인 상품입니다.");
	            	 history.go(-1);
	            }else if (status === 0) {
	                alert("신청에 실패하였습니다.");
	            }
	        }
	    });
	</script>		
	</body>
	</html>
