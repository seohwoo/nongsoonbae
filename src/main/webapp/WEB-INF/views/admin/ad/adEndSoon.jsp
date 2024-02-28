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
	<link rel="icon" href="/resources/img/logo.png">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<style>
	@import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900|Noto+Serif:400,700,700i&display=swap');
	    body {
	        font-family: 'Noto Sans KR', sans-serif; 
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
	    .content {
	        padding-top: 130px; /* 네비게이션 바 높이보다 약간 더 큰 값으로 설정 */
	        padding-left: 20px;
	        padding-right: 20px;
	    }
	    .nav-links {
        display: flex;
        justify-content: start;
        margin-bottom: 20px;
        padding: 0;
   	 }
	    .nav-links a {
	        background-color: #4CAF50;
	        color: #333;
	        padding: 10px 15px;
	        margin-right: 10px;
	        text-decoration: none;
	        border-radius: 5px;
	        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	        transition: background-color 0.3s ease-in-out;
	    }
	    .nav-links a:hover {
	        background-color: #e9e9e9;
	    }
	    .pagination {
	    text-align: center; /* 페이지네이션을 중앙 정렬합니다 */
	    margin-top: 20px; /* 페이지네이션과 위의 요소와의 간격을 조정합니다 */
		}
		
		.pagination form {
		    display: inline-block; /* 폼을 인라인 블록으로 설정하여 가로로 나열합니다 */
		    margin-right: 5px; /* 폼 사이의 간격을 조정합니다 */
		}
		
		.pagination button {
	    background-color: #f0f0f0; /* 버튼의 배경색을 연한 회색으로 설정 */
	    color: #333; /* 버튼 내 글자색을 어두운 회색으로 설정 */
	    padding: 8px 12px; /* 버튼의 패딩 */
	    border: 1px solid #ccc; /* 버튼의 테두리를 연한 회색으로 설정 */
	    border-radius: 4px; /* 모서리 둥글게 */
	    cursor: pointer; /* 커서를 포인터로 */
	    transition: background-color 0.3s; /* 호버 효과를 위한 전환 */
		}
		
		.pagination button:hover {
		    background-color: #e0e0e0; /* 버튼을 호버했을 때의 배경색을 조금 더 어두운 회색으로 설정 */
		}
	</style>
	<script>
    function confirmEnd() {
        return confirm("종료하시겠습니까?");
    }
	</script>
	</head>
	<body>
	<div class="usernav">
        <%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
    </div>
    <div class="content">
		 
	    <div class="nav-links">
	        <a href="/admin/adList">광고신청</a>
	        <a href="/admin/adEndSoon">오늘 끝나는 광고</a>
	        <a href="/admin/adIng">현재 광고 중</a>
	        <a href="/admin/adEnd">종료된 광고</a>
	    </div>
	<h2>광고종료예정상품 (${endSoonCnt})</h2>
	<c:if test='${endSoonCnt == 0}'>
		 <div class="adList">
			<h2> 종료 예정인 상품이 없습니다. </h2>
		 </div>
	</c:if> 
	<c:if test='${endSoonCnt > 0}'>
	<c:forEach var="list" items="${endSoonList}">
	    <div class="adList">
	       <form action="/admin/adEndPro" method="post" onsubmit="return confirmEnd();">
	        <input type="hidden" name="username" value="${list.username}"> 
	        <input type="hidden" name="productnum" value="${list.productnum}" />
	        <input type="hidden" name="num" value="${list.num}" />
	           <h5>No.${list.num}</h5>
	            <h2> ${list.p_productname}(${list.productnum}) </h2>
	            <button type="button" onclick="location.href='/product/productInfo?productnum=${list.productnum}&follow=${list.username}'" title="상품 확인하기">상품 확인</button>
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
	</c:if>
	<div class="pagination">
			<c:if test="${startPage > 10}">
				<form action="/admin/adEndSoon" method="post">
					<input type="hidden" name="pageNum" value="${startPage-10}">
					    <button type="submit">이전</button>
				</form>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
			  <form action="/admin/adEndSoon" method="post">
				   <input type="hidden" name="pageNum" value="${i}">
				   <button type="submit">${i}</button>
				</form>	      
			</c:forEach>
				    <c:if test="${endPage < pageCount}">
			    <form action="/admin/adEndSoon" method="post">
				            <input type="hidden" name="pageNum" value="${startPage+10}">
				            <button type="submit">다음</button>
				        </form>
				    </c:if>
				</div>
	</div>
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