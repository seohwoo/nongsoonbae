<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="/resources/css/main.css" rel="stylesheet" type="text/css">
		<meta charset="UTF-8">
		<title>메인입니당</title>		
	</head>
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<table class="table-borderless">
		<tbody>
			<tr>
			<td class="title">
				<h1>메인입니당</h1>
			</td>
			</tr>
			<tr>
			<td class="season">
				<jsp:include page="/WEB-INF/views/main/season.jsp" />
			</td>
			</tr>
			<tr>
			<td class="chart">
				<jsp:include page="/WEB-INF/views/main/chart.jsp" />
			</td>
			</tr>
			<tr>
			<td class="detail">
				<jsp:include page="/WEB-INF/views/main/seasonDetail.jsp" />
			</td>
			</tr>
		</tbody>
	</table>	
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>