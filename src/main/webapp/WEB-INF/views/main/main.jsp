<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>메인입니당</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<h1>메인입니당</h1>
		<jsp:include page="/WEB-INF/views/main/season.jsp" />
		<jsp:include page="/WEB-INF/views/main/chart.jsp" />
		<jsp:include page="/WEB-INF/views/main/seasonDetail.jsp" />
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</body>
</html>