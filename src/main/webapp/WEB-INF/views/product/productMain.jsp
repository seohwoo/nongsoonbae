<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>일단 상점 메인</title>
	</head>
	
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
		<div class="table">
		<h3>${myName} 페이지</h3>
			<form action="/product/createProduct?myName=${myName}" method="post">
			<c:if test="${status==0}">
				<h3>아직 내 상점이 없습니다. 지금 바로 개설해보세요!</h3>
				<input type="submit" value="나의 상점 만들기">
			</c:if>
			<c:if test="${status!=0}">
				<input type="button" value="나의 상점 정보" onclick="javascript:window.location='/product/productMyShop?username=${username}'">
				<input type="button" value="상품 등록" onclick="javascript:window.location='/product/productWriteForm?myName=${myName}'">
				<input type="button" value="나의 상점 관리" onclick="javascript:window.location='/product/myProduct'">
				<input type="button" value="전체 상품 조회" onclick="javascript:window.location='/product/allProduct'">
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</c:if>
			</form>
		</div>
	</body>
</html>