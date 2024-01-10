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
						<form action="/main/chart" method="post">
							<input type="hidden" name="cate1" value="${dto.cate1}">
							<input type="hidden" name="cate2" value="${dto.cate2+1}">
							<input type="hidden" name="cate3" value="${dto.cate3+1}">
							<button type="submit">
								<img src="${dto.img}" border="0" width="100" height="100">
								<br />
								<span>${dto.catename}</span>
							</button>
						</form>
					</div>
				</div>
			</td>
			</c:forEach>
		</tr>
		
		<tr><td>
		<div style="display: flex;">
			<c:forEach var="cate" items="${cateList}" >
				<div style="margin-right: 10px;">
					<form action="/main/chart" method="post">
						<input type="hidden" name="categoryNum" value="${categoryNum}">
						<input type="hidden" name="cate1" value="${cate.cate1}">
						<input type="hidden" name="cate2" value="${cate.cate2}">
						<input type="hidden" name="cate3" value="${cate.cate3}">
						<button type="submit">
							<img src="${cate.img}" border="0" width="100" height="100" class="bd-placeholder-img rounded-circle">
							<br />
							<span>${cate.catename}</span>
						</button>
					</form>
				</div>
			</c:forEach>
		</div></td></tr>	
		<tr><td class="page">
		<c:if test="${cate1!=null}">
			<c:if test="${categoryNum>1}">
				<form action="/main/chart" method="post">
					<input type="hidden" name="categoryNum" value="${categoryNum-1}">
					<input type="hidden" name="cate1" value="${cate1}">
					<input type="hidden" name="cate2" value="${prevCate.cate2}">
					<input type="hidden" name="cate3" value="${prevCate.cate3}">
					<button type="submit">⏪</button>
				</form>
			</c:if>
			<c:if test="${categoryNum==1 || categoryNum>=maxCategoryNum}">
				<button onclick="window.location='#'">⏸</button>
			</c:if>
			<c:if test="${categoryNum<maxCategoryNum}">
				<form action="/main/chart" method="post">
					<input type="hidden" name="categoryNum" value="${categoryNum+1}">
					<input type="hidden" name="cate1" value="${cate1}">
					<input type="hidden" name="cate2" value="${nextCate.cate2}">
					<input type="hidden" name="cate3" value="${nextCate.cate3}">
					<button type="submit">⏩</button>
				</form>
			</c:if>
		</c:if></td></tr><tr><td>
		<jsp:include page="/WEB-INF/views/main/chart.jsp" />
		</td></tr></table>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>