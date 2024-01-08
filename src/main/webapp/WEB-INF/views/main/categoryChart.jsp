<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<link href="/resources/css/main.css" rel="stylesheet" type="text/css">
		<meta charset="UTF-8">
		<title>실시간 차트</title>
	</head>
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<table class="chart table-borderless">
	<tr><td>
		<h1 class="h1 mb-4">실시간 차트</h1></td></tr>
		<tr>
			<c:forEach var="dto" items="${catemenu}" >
			<td>
			<div style="display: flex;">
				<div style="margin-right: 10px;">
					<a href="chart?cate1=${dto.cate1}&cate2=1&cate3=1">
						<img src="${dto.img}" border="0" width="100" height="100">
					</a>
					<span>${dto.catename}</span>
				</div></div></td>
			</c:forEach></tr>
		
		<tr><td>
		<div style="display: flex;">
			<c:forEach var="cate" items="${cateList}" >
				<div style="margin-right: 10px;">
					<a href="chart?cate1=${cate.cate1}&cate2=${cate.cate2}&cate3=${cate.cate3}&categoryNum=${categoryNum}">
						<img src="${cate.img}" border="0" width="100" height="100" class="bd-placeholder-img rounded-circle">
					</a>
					<span>${cate.catename}</span>
				</div>
			</c:forEach>
		</div></td></tr>	
		<tr><td class="page">
		<c:if test="${cate1!=null}">
			<c:if test="${categoryNum>1}">
				<button onclick="window.location='/main/chart?categoryNum=${categoryNum-1}&cate1=${cate1}&cate2=1&cate3=1'">⏪</button>
			</c:if>
			<c:if test="${categoryNum==1}">
				<button onclick="window.location='#'">⏸</button>
			</c:if>
			<c:if test="${categoryNum<maxCategoryNum}">
				<button onclick="window.location='/main/chart?categoryNum=${categoryNum+1}&cate1=${cate1}&cate2=${cate2}&cate3=${cate3}'">⏩</button>
			</c:if>
			<c:if test="${categoryNum>=maxCategoryNum}">
				<button onclick="window.location='#'">⏸</button>
			</c:if>
		</c:if></td></tr><tr><td>
		<jsp:include page="/WEB-INF/views/main/chart.jsp" />
		</td></tr></table>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>