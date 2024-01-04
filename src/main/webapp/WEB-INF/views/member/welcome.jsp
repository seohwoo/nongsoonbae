<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<title>welcome!!</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<link href="/resources/css/login.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<form class="form-signin" action="" method="POST">
	<h1 class="h1 mb-1">${username}님</h1>
	<h1 class="h4 mb-4"> 회원 가입을 축하드립니다!</h1><br />
	<h1 class="h6 mb-5 font-weight-normal">농순배를 이용하기 위해 <br/> 추가 정보를 미리 등록해주세요.</h1>
	<a href="/member/detailsForm" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">등록하기</a>
	<a href="" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover">지금은 됐어요</a>
	<p class="mt-5 mb-3 text-muted">welcome.jsp</p>
</form>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>