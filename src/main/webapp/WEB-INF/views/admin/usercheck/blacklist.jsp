<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>정지 회원 목록</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	<h1>정지 회원 목록 (${blackcount}명)</h1>
		<div>
			<jsp:include page="/WEB-INF/views/admin/usercheck/blackuserSearch.jsp" />
		</div>
		<c:forEach var="blacklist" items="${blacklist}">
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
				<div class="pagination">
				    <c:if test="${startPage > 10}">
				        <form action="/admin/blacklist" method="post">
				            <input type="hidden" name="pageNum" value="${startPage-10}">
				            <button type="submit">[이전]</button>
				        </form>
				    </c:if>
				    <c:forEach var="i" begin="${startPage}" end="${endPage}">
				        <form action="/admin/blacklist" method="post">
				            <input type="hidden" name="pageNum" value="${i}">
				            <button type="submit">[${i}]</button>
				        </form>
				    </c:forEach>
				    <c:if test="${endPage < pageCount}">
				        <form action="/admin/blacklist" method="post">
				            <input type="hidden" name="pageNum" value="${startPage+10}">
				            <button type="submit">[다음]</button>
				        </form>
				    </c:if>
				   </div>
	</body>
	<script>
	 $(document).ready(function() {
	        var status = <c:out value="${restatus}" default="-1" />;
	        if (status !== -1) {
	            if (status === 1) {
	                alert("복구되었습니다.");
	            } else if (status === 0) {
	                alert("복구에 실패하였습니다.");
	            }
	        }
	    });

	    function confirmRecovery(username) {
	        return confirm(username + ' 님의 계정을 복구하시겠습니까?');
	    }
	</script>
</html>