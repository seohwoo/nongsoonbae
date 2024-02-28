<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
   <head>
        <meta charset="UTF-8">
        <title>공지게시판</title>
        <link rel="icon" href="/resources/img/logo.png">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <style>
        @import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900|Noto+Serif:400,700,700i&display=swap');
            body { font-family: 'Noto Sans KR', sans-serif;  }
            .container { width: 80%; margin: auto; }
            .header { display: flex; justify-content: space-between; align-items: center; }
            .notice { border-bottom: 
			1px solid #ddd; padding: 10px; }
			.notice h2 { margin: 0; }
			.notice-meta { font-size: 0.8em; color: #666; }
			.pagination { text-align: center; margin-top: 20px; }
			.pagination a { margin: 0 5px; text-decoration: none; color: #333; }
			.pagination a:hover { text-decoration: underline; }
			.btn { background-color: #4CAF50; color: white; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; }
			.btn input[type=button] {
			border: none;
			background-color: transparent;
			color: white;
			padding: 0;
			}
			.btn:hover { background-color: #45a049; }
			.pagination form {
		    display: inline; /* 가로로 정렬 */
		    margin: 0 5px; /* 좌우 여백 */
		}
		
		.pagination button {
		    background-color: #f0f0f0; /* 버튼 배경색 */
		    color: #333; /* 글자색 */
		    border: 1px solid #ddd; /* 테두리 */
		    padding: 5px 10px; /* 내부 패딩 */
		    text-align: center; /* 텍스트 중앙 정렬 */
		    text-decoration: none; /* 텍스트 꾸밈 없앰 */
		    font-size: 0.9em; /* 폰트 크기 */
		    cursor: pointer; /* 커서 모양 */
		}
		
		.pagination button:hover {
		    background-color: #e0e0e0; /* 마우스 오버시 배경색 변경 */
		}
		.h1 {
	    text-align: center; 
		}
		.content {
	        padding-top: 130px; /* 네비게이션 바 높이보다 약간 더 큰 값으로 설정 */
	        padding-left: 20px;
	        padding-right: 20px;
	    }
	    .usernav {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        background-color: #fff;
        z-index: 1000; /* 다른 콘텐츠 위에 위치하도록 z-index 설정 */
    }
		</style>
	</head>
	<body>
	<div class="usernav">
        <%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
    </div>
	<div class="content">
			<div class="container">
				<div class="header">
					<h1> 공지게시판 </h1>
					<button class="btn" onclick="window.location='/admin/noticeForm'">글쓰기</button>
				</div>
				<c:if test="${count > 0}">
					<c:forEach var="article" items="${list}">
						<div class="notice">
						<form action="/admin/noticeView" method="get">
						    <input type="hidden" name="num" value="${article.num}">
						    <input type="hidden" name="pageNum" value="${pageNum}">
						    <button type="submit" style="background:none; border:none; padding:0; margin:0; font: inherit; color: blue; text-decoration: underline; cursor: pointer;">
						        <h2>${article.title}</h2>
						    </button>
						</form>
						<div class="notice-meta">
							<p>작성일: <fmt:formatDate value="${article.regdate}" pattern="yyyy년 MM월 dd일" /></p>
							<p>조회수: ${article.readcount}</p>
						</div>
						</div>
					</c:forEach>
				</c:if>
				<div class="pagination">
				    <c:if test="${startPage > 10}">
				        <form action="/admin/noticeList" method="post">
				            <input type="hidden" name="pageNum" value="${startPage-10}">
				            <button type="submit">이전</button>
				        </form>
				    </c:if>
				    <c:forEach var="i" begin="${startPage}" end="${endPage}">
				        <form action="/admin/noticeList" method="post">
				            <input type="hidden" name="pageNum" value="${i}">
				            <button type="submit">${i}</button>
				        </form>
				    </c:forEach>
				    <c:if test="${endPage < pageCount}">
				        <form action="/admin/noticeList" method="post">
				            <input type="hidden" name="pageNum" value="${startPage+10}">
				            <button type="submit">다음</button>
				        </form>
				    </c:if>
				</div>
			</div>
			
			</div>
	<script type="text/javascript">
		$(document).ready(function() {
	        var status = <c:out value="${status}" default="-1" />;
	        var deleteStatus = <c:out value="${deleteStatus}" default="-1" />;
	
	        if (status !== -1) {
	            if (status === 1) {
	                alert("공지글 작성이 완료되었습니다.");
	            } else if (status === 0) {
	                alert("작성에 실패하였습니다.");
	            }
	        }
	
	        if (deleteStatus !== -1) {
	            if (deleteStatus === 1) {
	                alert("삭제되었습니다.");
	            } else if (deleteStatus === 0) {
	                alert("삭제에 실패하였습니다.");
	            }
	        }
	    });
	</script>		
			
	</body>
</html>
