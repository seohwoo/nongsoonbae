<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" href="/resources/img/logo.png">
	</head>
	<body>
	<div class="notice success">
		<form action="/nsb/noticeView" method="get">
			<input type="hidden" name="num" value="${notice.num}">
			<input type="hidden" name="pageNum" value="1">
			<button type="submit" style="background:none; border:none; padding:0; margin:0; font: inherit; color: blue; text-decoration: underline; cursor: pointer;">
			<h5 class="icon-link icon-link-hover">${notice.title}</h5>
			</button>
		</form></div>
	</body>
</html>