<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	 <style>
	  	nav {
		    background-color: #333;
		    overflow: hidden;
		}
		
		nav ul {
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
		}
		
		nav ul li {
		    float: left;
		}
		
		nav ul li a {
		    display: block;
		    color: white;
		    text-align: center;
		    padding: 14px 16px;
		    text-decoration: none;
		}
		
		nav ul li a:hover {
		    background-color: #ddd;
		    color: black;
		}
	 </style>
	</head>
	<body>
		<nav>
	        <ul>
	            <li><a href="/admin/usercheck">전체회원</a></li>
	            <li><a href="/admin/blacklist">정지회원</a></li>
	            <li><a href="/admin/addcategory">카테고리 추가하기</a></li>
	        </ul>
	    </nav>
	</body>
</html>