<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>공지</title>
        <link rel="icon" href="/resources/img/logo.png">
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
                background-color: #d9534f; /* 빨간색 계열로 설정 */
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-left: 10px; /* 버튼 사이의 간격 */
            }
            .delete-button:hover {
                background-color: #c9302c; /* 호버 시 색상 변경 */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h2>${dto.title}</h2>
                <a href="/nsb/noticeList" class="back-button">전체글보기</a>
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
