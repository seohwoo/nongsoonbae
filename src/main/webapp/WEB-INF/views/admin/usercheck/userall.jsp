<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원관리</title>
		<style>
	        body {
	        font-family: Arial, sans-serif;
	        background-color: #f5f5f5;
	        margin: 0;
	        padding: 10px;
		    }
		    .userlist {
		        background-color: white;
		        border: 1px solid #ddd;
		        padding: 10px;
		        margin-bottom: 10px;
		        border-radius: 5px;
		        font-size: 0.8em; 
		    }
		    .userlist h2, .userlist h3 {
		        margin: 3px 0;
		        font-size: 1em; 
		    }
		    .email-meta {
		        color: #555;
		        font-size: 0.8em; 
		    }
		    input[type="button"], input[type="submit"] {
		        background-color: pink;
		        color: white;
		        border: none;
		        padding: 8px 15px; 
		        text-align: center;
		        text-decoration: none;
		        display: inline-block;
		        font-size: 0.8em; 
		        margin: 2px 1px;
		        cursor: pointer;
		        border-radius: 5px;
		    }
		    input[type="button"]:hover, input[type="submit"]:hover {
		        background-color: red;
		    }
		    .pagination form {
		        display: inline;
		        margin-right: 5px; 
		    }
    </style>
	</head>
	<body>
		<h2>일반 회원 목록 (${count} 명)</h2>
			<c:forEach var="list" items="${list}">
				<div class="userlist">
					<form action="/admin/stopPro" method="post" onsubmit="return confirmSubmission(this)">
		                <input type="hidden" name="userId" value="${list.username}"> 
			            <h2>${list.username}</h2>
			            <h3>${list.name}</h3>
		                <input type="button" value="정지하기" onclick="toggleOptions(this)"/>
		                <div class="options" style="display:none;">
		                    <select name="reason">
		                        <option value="notReason">정지 사유 선택하기</option>
				                <option value="부적절한 내용(폭력적,혐오적 댓글이나 게시글 or 스팸 게시)">부적절한 내용(폭력적,혐오적 댓글이나 게시글 or 스팸 게시)</option>
				                <option value="부정행위(가짜 계정 생성, 부정 혜택 이용, 해킹 등)">부정행위(가짜 계정 생성, 부정 혜택 이용, 해킹 등)</option>
				                <option value="사기 (거짓 정보 제공, 결제 사기, 물품 미배송)">사기 (거짓 정보 제공, 결제 사기, 물품 미배송)</option>
				                <option value="결제 관련 (반복된 결제 실패, 카드 도용, 환불 규정 위반)">결제 관련 (반복된 결제 실패, 카드 도용, 환불 규정 위반)</option>
				                <option value="개인 정보 보호 위반 (다른 사용자의 정보를 무단으로 수집, 남용)">개인 정보 보호 위반 (다른 사용자의 정보를 무단으로 수집, 남용)</option>
				                <option value="서비스 악용 (시스템의 취약점 이용, 서버 부하 유발하는 행위)">서비스 악용 (시스템의 취약점 이용, 서버 부하 유발하는 행위)</option>
		                    </select>
		                    <input type="submit" value="선택하기"/> 
	                </div>
	                <div class="email-meta">
	                    <p>이메일: ${list.email}</p>
	                </div>
         		   </form>				
				</div>
			</c:forEach>	
				<div class="pagination">
				    <c:if test="${startPage > 10}">
				        <form action="/admin/userall" method="post">
				            <input type="hidden" name="pageNum" value="${startPage-10}">
				            <button type="submit">[이전]</button>
				        </form>
				    </c:if>
				    <c:forEach var="i" begin="${startPage}" end="${endPage}">
				        <form action="/admin/userall" method="post">
				            <input type="hidden" name="pageNum" value="${i}">
				            <button type="submit">[${i}]</button>
				        </form>
				    </c:forEach>
				    <c:if test="${endPage < pageCount}">
				        <form action="/admin/userall" method="post">
				            <input type="hidden" name="pageNum" value="${startPage+10}">
				            <button type="submit">[다음]</button>
				        </form>
				    </c:if>
	</body>
	<script>
	    function toggleOptions(button) {
	    var optionsDiv = button.parentElement.querySelector('.options'); 
	
	    if (optionsDiv.style.display === "none") {
	        optionsDiv.style.display = "block";
	    } else {
	        optionsDiv.style.display = "none";
	    }
	}
	    function confirmSubmission(form) {
	        var reasonSelect = form.querySelector('select[name="reason"]');
	        var reasonValue = reasonSelect.value;

	        if (reasonValue === "notReason") {
	            alert("사유를 선택해주세요!");
	            return false; 
	        } else {
	            return confirm("정지하시겠습니까?"); 
	        }
	    }

</script>
</html>