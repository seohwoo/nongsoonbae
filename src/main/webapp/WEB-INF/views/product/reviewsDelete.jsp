<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="icon" href="/resources/img/logo.png">
	</head>
	
	<body>
		<form action="/product/reviewsDeletePro?productnum=${productnum}&myName=${myName}" method="post">
			<input type="submit" name="confirm" value="리뷰 삭제하기" />
		</form>
	</body>
</html>