<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>공지읽기</title>
        <script>
		    function confirmDelete() {
		        return confirm('삭제하시겠습니까?');
		    }
		</script>
        <style>
            /* 기존 CSS 스타일 */
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }
            .container {
                background-color: #fff;
                padding: 20px;
                margin: auto;
                width: 80%;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2, h3 {
                color: #333;
            }
            .notice-details, .content {
                margin-top: 20px;
            }

            /* 추가된 CSS 스타일 */
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .back-button {
                padding: 8px 15px;
                background-color: #4CAF50;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            .back-button:hover {
                background-color: #45a049;
            }
            .delete-button {
                padding: 8px 15px;
                background-color: #d9534f; 
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-left: 10px; 
            }
            .delete-button:hover {
                background-color: #c9302c; 
            }
        </style>
    </head>
    <body>
    <%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
        <div class="container">
            <div class="header">
                <h2>${dto.title}</h2>
                <a href="/admin/noticeList" class="back-button">전체글보기</a>
                <form action="/admin/noticeDeletePro" method="post" style="display: inline;" onsubmit="return confirmDelete();">
   					 <input type="hidden" value="${dto.num}" name="num" />
   					 <button type="submit" class="delete-button">삭제하기</button>
				</form>
            </div>
            <div class="notice-details">
                <h3>작성자: 관리자</h3>
                <h3>조회수: ${dto.readcount}</h3>
                <p>작성일: <fmt:formatDate value="${dto.regdate}" pattern="yyyy년 MM월 dd일" /></p>
            </div>
            <div class="content">
                ${dto.content}    
            </div>
        </div>
    </body>
</html>
