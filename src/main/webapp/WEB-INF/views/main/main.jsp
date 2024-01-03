<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>메인입니당</title>		
	</head>
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
		<h1 class="h1 mb-1">메인입니당</h1><br />
		<jsp:include page="/WEB-INF/views/main/season.jsp" /><br />
		<jsp:include page="/WEB-INF/views/main/chart.jsp" /><br />
		<jsp:include page="/WEB-INF/views/main/seasonDetail.jsp" />
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>