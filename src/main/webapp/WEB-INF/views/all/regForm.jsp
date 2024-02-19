<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<title>regForm</title>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

<link href="/resources/css/login.css" rel="stylesheet" type="text/css">

<script>
$(document).ready(function(){
	var errorMessage = "${errorMessage}";
    if(errorMessage && errorMessage.trim() !== "") {
        alert(errorMessage);
        // errorMessage 초기화 (다시 alert이 뜨지 않도록)
        ${errorMessage = ""};
    }
	$(".btn").on("click",function(){
		var num1 = $(".birth").find("input[name='birth']").val();
		var num2 = $(".birth").find("input[name='gender']").val();
		var num3 = /^[0-9]{6}$/; // 주민번호 조건1
		var num4 = /^[1-4]{1}$/; // 주민번호 조건2
		
		if(!num3.test(num1) || !num4.test(num2)) { // 주민번호 조건1,2 둘 중 하나라도 만족하지 않으면
			alert("주민번호 형식에 맞게 입력해주세요.");
			return false;
		}
	});
});

</script>

</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>

<form class="form-signin" action="/member/reg" method="POST">
	<h1 class="h3 mb-3 font-weight-normal">회원가입</h1>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	아이디<input type="text" name="username" id="username" class="form-control" placeholder="ID" required autofocus>
	비밀번호<input type="password" name="password" class="form-control" placeholder="Password" required>
	이름<input type="text" name="name" class="form-control" placeholder="NAME" required>
	이메일<input type="text" name="email" class="form-control" placeholder="EMAIL" required>
	주민등록번호<div class="birth"><input type="number" name="birth" class="form-control" placeholder="000000" maxlength="6" required> - <input type="number" name="gender" class="form-control" placeholder="1/3 or 2/4" min="1" max="4" maxlength="1" required></div>
	<br />
	<div class="d-grid gap-2  mx-auto">
	  <button class="btn btn-lg btn-primary btn-block" type="submit">REGISTER</button>
	</div>
	<c:if test="${param.err == true}">
		<p style="color: red">ID와 Password를 확인해주세요.</p>
	</c:if>
	<p class="mt-5 mb-3 text-muted">regForm.jsp</p>
</form>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>