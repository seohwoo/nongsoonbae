<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
   <head>
        <meta charset="UTF-8">
        <title>공지게시판</title>
        <link rel="icon" href="/resources/img/logo.png">
        <style>
            body { font-family: Arial, sans-serif; }
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
			</style>
	</head>
	<body>
			<div class="container">
				<div class="header">
					<h1>🍓🍓 공지게시판 🍓🍓</h1>
				</div>
				<c:if test="${count > 0}">
					<c:forEach var="article" items="${list}">
						<div class="notice">
						<form action="/nsb/noticeView" method="post">
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
				        <form action="/nsb/noticeList" method="post">
				            <input type="hidden" name="pageNum" value="${startPage-10}">
				            <button type="submit">[이전]</button>
				        </form>
				    </c:if>
				    <c:forEach var="i" begin="${startPage}" end="${endPage}">
				        <form action="/nsb/noticeList" method="post">
				            <input type="hidden" name="pageNum" value="${i}">
				            <button type="submit">[${i}]</button>
				        </form>
				    </c:forEach>
				    <c:if test="${endPage < pageCount}">
				        <form action="/nsb/noticeList" method="post">
				            <input type="hidden" name="pageNum" value="${startPage+10}">
				            <button type="submit">[다음]</button>
				        </form>
				    </c:if>
				</div>
			</div>
	</body>
</html>
