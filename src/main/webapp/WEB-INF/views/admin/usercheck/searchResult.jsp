<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>검색결과</title>
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
	<%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
	<div>
		<c:if test="${searchCnt  == 0 }" >
	         <h1> 💟 ${ userSearch}💟 에 대한 검색결과가 없습니다! </h1>
	         <input type="button" value="돌아가기" onclick="goBack()"/>
	         </c:if>
	         <c:if test="${searchCnt  >  0 }" >
	         	<h3> 💟 ${ userSearch}💟 검색결과 ${searchCnt} 명 </h3>
	         	<c:forEach var="searchlist" items="${searchlist}">
				<div class="userlist">
					<form action="/admin/stopPro" method="post" onsubmit="return confirmSubmission(this)">
		                <input type="hidden" name="username" value="${searchlist.username}"> 
			            <h2>💟 ID : ${searchlist.username}</h2>
			            <h3>이름 : ${searchlist.name}</h3>
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
	                    <p>이메일: ${searchlist.email}</p>
	                </div>
         		   </form>				
				</div>
			</c:forEach>	
	         </c:if>	
	     </div>
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
	        var username = form.querySelector('input[name="username"]').value;
	        if (reasonValue === "notReason") {
	            alert("사유를 선택해주세요!");
	            return false; 
	        } else {
	            return confirm(username + '님을 정지하시겠습니까?'); 
	        }
	    }
	    function goBack() {
            window.history.back(); 
        }
	</script>
</html>