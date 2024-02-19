<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="result">
			<div class="resulttxt" style="padding: 10px;">
				<h2><b style="color: #198754">${userSearch}</b> 검색 결과입니다.</h2><h2 style="font-size: 12px;">검색 수 : ${searchCnt}</h2>
			</div>
			<hr />
			<c:if test="${searchCnt==0}">
				<div style="min-height: 600px;">
					<h1>검색결과가 없습니다</h1>
				</div>
			</c:if>
			<c:if test="${searchCnt>0}">
				<div class="container mx-auto mt-4">
	 				<div class="row">
						<c:forEach var="dto" items="${searchList}">
							<%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>
						</c:forEach>
					</div>
				</div>
			</c:if>
		</div>
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>