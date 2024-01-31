<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<title>logout</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<link href="/resources/css/login.css" rel="stylesheet" type="text/css">

</head>
		<script type="text/javascript">
	        var errorParam = '<%= request.getParameter("error") %>';
	        if (errorParam === 'true') {
	            // "error" 값이 true일 때 alert 띄우기
	            alert("이미 회원 탈퇴된 계정입니다.");
	            window.location.href="/member/out";
	        }
	    </script>
	<body>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
			<form class="form-signin" action="/member/out" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<img src="/resources/img/spring.png" style="width: 200px; height: 95px">
				<h1 class="h5 mb-3 font-weight-normal">로그아웃 하시겠습니까?</h1><br />
				<button class="btn btn-primary col-6" type="submit">LOGOUT</button>
				<p class="mt-5 mb-3 text-muted">logout.jsp</p>
			</form>
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>