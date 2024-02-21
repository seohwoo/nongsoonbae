<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>정지 회원 목록</title>
		<link rel="icon" href="/resources/img/logo.png">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
			<style>
			@import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900|Noto+Serif:400,700,700i&display=swap');
		        body {
		        font-family: 'Noto Sans KR', sans-serif; 
		       	background-color: #f5f5f5;
		        margin: 0;
		        padding: 10px;
		        padding-top: 30px;
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
			        background-color: green;
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
			    .pagination form {
			        display: inline;
			        margin-right: 5px; 
			    }
			     .content {
			        padding-top: 20px; /* 네비게이션 바 높이보다 약간 더 큰 값으로 설정 */
			        padding-left: 20px;
			        padding-right: 20px;
			    }
			     .pagination {
				    text-align: center; /* 페이지네이션을 중앙 정렬합니다 */
				    margin-top: 20px; /* 페이지네이션과 위의 요소와의 간격을 조정합니다 */
					}
				
				.pagination form {
				    display: inline-block; /* 폼을 인라인 블록으로 설정하여 가로로 나열합니다 */
				    margin-right: 5px; /* 폼 사이의 간격을 조정합니다 */
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
	</head>
	<body>
	<%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
	<div class="content">
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
				            <button type="submit">${i}</button>
				        </form>
				    </c:forEach>
				    <c:if test="${endPage < pageCount}">
				        <form action="/admin/blacklist" method="post">
				            <input type="hidden" name="pageNum" value="${startPage+10}">
				            <button type="submit">[다음]</button>
				        </form>
				    </c:if>
				   </div>
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