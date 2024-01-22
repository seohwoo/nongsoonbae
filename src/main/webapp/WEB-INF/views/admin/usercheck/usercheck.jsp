<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>회원관리</title>
	</head>
	<body>
	<%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
		<h2>회원 관리하기</h2>	
		<div>
			<jsp:include page="/WEB-INF/views/admin/usercheck/userSearch.jsp" />
		</div>
		<div>
			<jsp:include page="/WEB-INF/views/admin/usercheck/userall.jsp" />
		</div>
	</body>
</html>