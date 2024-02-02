<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<style>
		.sub-nav ul {
	    list-style-type: none;
	    margin: 0;
	    padding: 0;
	    overflow: hidden;
	    background-color: #f2f2f2;
		}
		
		.sub-nav li {
		    float: left;
		}
		
		.sub-nav li a {
		    display: block;
		    color: black;
		    text-align: center;
		    padding: 10px 16px;
		    text-decoration: none;
		}
		
		.sub-nav li a:hover {
		    background-color: #ddd;
		}
	
	</style>
	</head>
	<body>
		<nav class="sub-nav">
		    <ul>
		        <li><a href="/admin/adList">광고신청</a></li>
		        <li><a href="/admin/adEndSoon">오늘 끝나는 광고</a></li>
		        <li><a href="#">종료된 광고 </a></li>
		    </ul>
		</nav>
	
	</body>
</html>