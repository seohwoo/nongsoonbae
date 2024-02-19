<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<title>error</title>
<link href="/resources/css/login.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<form class="form-signin" action="/login/pro" method="POST">
	<img src="/resources/img/spring.png" style="width: 200px; height: 95px">
	<h1 class="h3 mb-3 font-weight-normal">접근 권한이 없습니다.</h1>
	<p class="mt-5 mb-3 text-muted">loginError.jsp</p>
</form>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>