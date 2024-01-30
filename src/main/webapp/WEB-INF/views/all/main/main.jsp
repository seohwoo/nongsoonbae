<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>농순배</title>		
		<script type="text/javascript">
	        var errorParam = '<%= request.getParameter("error") %>';
	        if (errorParam === 'true') {
	            // "error" 값이 true일 때 alert 띄우기
	            alert("접근 권한이 없습니다.");
	        }
	    </script>
	    <script type="text/javascript" src="/resources/js/jquery-1.10.2.min.js"></script>
	</head>
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<table class="table-borderless">
		<tbody id="contentContainer">
			<tr>
				<td class="title">
					<h1>🍒🍓🍅 농순배 🥑🥒🥬</h1>
					<h1>🌺🌻🌼🌷🥀🌱🌲🌳🌴</h1>
					<h5>최신공지 <jsp:include page="/WEB-INF/views/all/main/newNotice.jsp" /></h5>
					<br />
				</td>
			</tr>
			<tr>
				<td class="search">
					<jsp:include page="/WEB-INF/views/all/main/search.jsp" />
				</td>
			</tr>
			<tr>
				<td class="season">
					<jsp:include page="/WEB-INF/views/all/main/season.jsp" />
				</td>
			</tr>
			<tr>
				<td class="detail">
					<jsp:include page="/WEB-INF/views/all/main/seasonDetail.jsp" />
				</td>
			</tr>
		</tbody>
	</table>	
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>