<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    @import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900|Noto+Serif:400,700,700i&display=swap');
    @font-face {
        font-family: 'GapyeongWave';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2312-1@1.1/GapyeongWave.woff2') format('woff2');
        font-weight: normal;
        font-style: normal;
    }
    
    body, html {
        margin: 0;
        padding: 20px 100px; /* 좌우 여백을 100px로 조정 */
    }
    .usernav { 
        position: fixed;
        top: 0;
        width: 100%;
        background-color: #fff;
        z-index: 1000; /* 다른 콘텐츠 위에 위치하도록 z-index 설정 */
    }

    body, html {
        margin: 0;
        padding: 0;
    }
    nav {
        background-color: #fff;
        overflow: hidden;
        display: flex;
        justify-content: space-between; 
        padding-left: 100px;
        padding-right: 50px;
        height: 120px;
    }
    nav ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
        display: flex;
        align-items: center;
    }
    nav ul li {
        padding: 14px 16px;
    }
    nav ul li a {
        color: black;
        text-decoration: none;
        display: flex;
        align-items: center;
    }
    nav ul li a .text {
        font-family: 'GapyeongWave', sans-serif;
        font-size: 32px; /* 폰트 크기 설정 */
        color: green; /* 폰트 색상 설정 */
        margin-left: 4px; 
    }

   
    nav ul:not(:first-child) li a {
        font-family: 'Noto Sans KR', sans-serif; 
        font-weight: 700;
        font-size: 18px;
        
    }
</style>
</head>
<body>
    <nav>
        <ul>
            <li><a href="/nsb/main" class="down"><img src="/resources/img/logo.png" style="height: 60px;"><span class="text">농순배</span></a></li>
        </ul>
        <ul>
            <li><a href="/admin/usercheck">전체회원</a></li>
            <li><a href="/admin/blacklist">정지회원</a></li>
            <li><a href="/admin/addcategory">카테고리 추가하기</a></li>
            <li><a href="/admin/noticeList">공지게시판 관리</a></li>
            <li><a href="/admin/adList">광고신청확인</a></li>
        </ul>
        <ul>
            <li><a href="/member/logout" >로그아웃</a></li>
        </ul>
    </nav>
</body>
</html>

