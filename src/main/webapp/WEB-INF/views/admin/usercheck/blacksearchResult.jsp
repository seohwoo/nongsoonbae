<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>정지회원검색결과</title>
		<style>
		        body {
		        font-family: Arial, sans-serif;
		        background-color: #f5f5f5;
		        margin: 0;
		        padding: 10px;
			    }
			    .blacklist {
			        background-color: white;
			        border: 1px solid #ddd;
			        padding: 10px;
			        margin-bottom: 10px;
			        border-radius: 5px;
			        font-size: 0.8em; 
			    }
			    .blacklist h2, .blacklist h3 {
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
			        background-color: blue;
			    }
			    .pagination form {
			        display: inline;
			        margin-right: 5px; 
			    }
	    </style>	
	</head>
	<body>
	<%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
		<c:if test="${blacksearchCnt  == 0 }" >
	         <h1>💔 ${userSearch}💔 에 대한 검색결과가 없습니다! </h1>
	         <input type="button" value="돌아가기" onclick="goBack()"/>  
	     </c:if>
	       <c:if test="${blacksearchCnt  >  0 }" >
				<h1> 💔 ${userSearch}💔 검색결과 (${blacksearchCnt}명)</h1>
				<div>
					<jsp:include page="/WEB-INF/views/admin/usercheck/blackuserSearch.jsp" />
				</div>
				<c:forEach var="blacklist" items="${blacksearchlist}">
		    		<div class="blacklist">
		      		 <form action="/admin/reuserPro" method="post" >
			            <input type="hidden" name="username" value="${blacklist.u_username}"> 
				            <h2>💔 ID : ${blacklist.u_username}</h2>
				            <h3>이름 : ${blacklist.u_name}</h3>
			          			<input type="submit" value="계정복구" onclick="return confirmRecovery('${blacklist.u_username}')" />
				            <div class="email-meta">
				                <p>이메일: ${blacklist.u_email}</p>
				                <p>정지사유: ${blacklist.b_reason} 
				                (날짜: <fmt:formatDate value="${blacklist.b_bandate}" pattern="yyyy년 MM월 dd일" /> )
				                </p>
				       	    </div>
		        		</form>             
		    		</div>
				</c:forEach>
		</c:if>
	</body>
	<script>
	 function confirmRecovery(username) {
	      return confirm(username + ' 님의 계정을 복구하시겠습니까?');
	    }
	 function goBack() {
         window.history.back(); 
     }
	</script>	
	
</html>