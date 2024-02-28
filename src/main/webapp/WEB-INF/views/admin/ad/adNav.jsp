<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<style>
	@import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900|Noto+Serif:400,700,700i&display=swap');
	.sub-nav ul {
		font-family: 'Noto Sans KR', sans-serif; 
	    list-style-type: none;
	    margin: 0;
	    padding: 0;
	    overflow: hidden;
	    background-color: #e0e0e0;
	    height: 60px; /* 네비게이션 바의 높이를 60px로 조정 */
	}
	
	.sub-nav li {
	    float: left;
	    height: 100%; /* <li> 태그의 높이를 <ul>의 높이와 동일하게 설정 */
	}
	
	.sub-nav li a {
	    display: block;
	    color: black;
	    text-align: center;
	    padding: 20px 16px; /* 상하 패딩을 조정하여 높이 변경 */
	    text-decoration: none;
	    line-height: 20px; /* 필요에 따라 줄 높이(line-height) 조정 */
	}
	</style>
	</head>
	<body>
		<nav class="sub-nav">
		    <ul>
		        <li><a href="/admin/adList">광고신청</a></li>
		        <li><a href="/admin/adEndSoon">오늘 끝나는 광고</a></li>
		        <li><a href="/admin/adIng">현재 광고 중 </a></li>
		        <li><a href="/admin/adEnd">종료된 광고 </a></li>
		    </ul>
		</nav>
	
	</body>
</html>