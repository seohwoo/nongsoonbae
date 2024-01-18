<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<form action="/nsb/noticeView" method="get">
			<input type="hidden" name="num" value="${notice.num}">
			<input type="hidden" name="pageNum" value="1">
			<button type="submit" style="background:none; border:none; padding:0; margin:0; font: inherit; color: blue; text-decoration: underline; cursor: pointer;">
			<h5>${notice.title}</h5>
			</button>
		</form>
	</body>
</html>