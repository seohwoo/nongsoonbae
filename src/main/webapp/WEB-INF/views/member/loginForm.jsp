<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<title>login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.6.0/kakao.min.js" integrity="sha384-6MFdIr0zOira1CHQkedUqJVql0YtcZA1P0nbPrQYJXVJZUkTk/oX4U9GhUIs3/z8" crossorigin="anonymous"></script>
<script>Kakao.init('25d3a749f71a3b11746ebbe324fa0eca'); Kakao.isInitialized();</script>




<link href="/resources/css/login.css" rel="stylesheet" type="text/css">

</head>
<body>
<form class="form-signin" action="/login" method="POST">
	<img src="/resources/img/spring.png" style="width: 200px; height: 95px">
	<h1 class="h3 mb-3 font-weight-normal"></h1>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="text" name="username" class="form-control" placeholder="ID" required autofocus>
	<input type="password" name="password" class="form-control" placeholder="Password" required>
	자동 로그인 : <input type="checkbox" name="remember-me" />
	<button class="btn btn-lg btn-primary btn-block" type="submit">LOGIN</button>
	<c:if test="${param.err == true}">
		<p style="color: red">ID와 Password를 확인해주세요.</p>
	</c:if>
	<p class="mt-5 mb-3"> -------------------또는-------------------- </p>
	<div>
		<a id="kakao-login-btn" href="javascript:loginWithKakao()">
		  <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="카카오 로그인 버튼" />
		</a>
		<p id="token-result"></p>
		
		<script>
		  function loginWithKakao() {
		    Kakao.Auth.authorize({
		      redirectUri: 'http://localhost:8080/login/oauth2/code/kakao',
		     
		    });
		  }
		  function requestUserInfo() {
			  Kakao.API.request({
				  url: '/v2/user/me',
				  data: {
				    property_keys: ['kakao_account.email', 'kakao_account.gender'],
				  },
				})
				  .then(function(response) {
				    console.log(response);
				  })
				  .catch(function(error) {
				    console.log(error);
				  });
			  }
		
		  // 아래는 데모를 위한 UI 코드입니다.
		  displayToken()
		  function displayToken() {
		    var token = getCookie('authorize-access-token');
		
		    if(token) {
		      Kakao.Auth.setAccessToken(token);
		      Kakao.Auth.getStatusInfo()
		        .then(function(res) {
		          if (res.status === 'connected') {
		            document.getElementById('token-result').innerText
		              = 'login success, token: ' + Kakao.Auth.getAccessToken();
		          }
		        })
		        .catch(function(err) {
		          Kakao.Auth.setAccessToken(null);
		        });
		    }
		  }
		
		  function getCookie(name) {
		    var parts = document.cookie.split(name + '=');
		    if (parts.length === 2) { return parts[1].split(';')[0]; }
		  }
		</script>
	</div>
	<p class="mt-5 mb-3 text-muted">loginForm.jsp</p>
</form>
</body>
</html>