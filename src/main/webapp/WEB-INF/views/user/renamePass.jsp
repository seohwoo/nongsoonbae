<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>비밀번호 변경</title>
    <link rel="icon" href="/resources/img/logo.png">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<link href="/resources/css/login.css" rel="stylesheet" type="text/css">

    <script>
        $(document).ready(function(){
            $("#changePassBtn").click(function(){
                var password = $("#password").val();
                var repassword = $("#repassword").val();
                var username = $("#username").val();
                console.log(username);

                if(password === repassword){
                	$.ajax({
                    type: "POST",
                    url: "/member/passPro",
                    data: { password: password, username : username },
                    success: function(data){
                        alert("비밀번호가 성공적으로 변경되었습니다.");
                        $('#changePassBtn').attr('disabled',true);
        				$('#password').attr('readonly',true);
        				$('#repassword').attr('readonly',true);
        				// 5초 후에 로그인으로 이동
                        setTimeout(function(){
                            window.location.href = "/member/form";
                        }, 1000);
                    },
                    error: function(){
                        alert("비밀번호 변경에 실패했습니다.");
                    }
                });
              }else{
            	alert("입력하신 비밀번호가 서로 다릅니다.");
              }  
            });
        });
    </script>
</head>
<body>
    <%@include file="/WEB-INF/views/include/header.jsp"%>
    <form class="form-signin" action="" method="POST">
        <h1 class="h1 mb-1">비밀번호 변경</h1><br />
        변경 비밀번호<input type="password" id="password" class="form-control" placeholder="Password" required>
        비밀번호 확인<input type="password" id="repassword" class="form-control" placeholder="RePassword" required>
        <input type="hidden" id="username" value="${username}" />
        <input type="button" id="changePassBtn" class="btn btn-primary" value="변경" />
        <p class="mt-5 mb-3 text-muted">renamePass.jsp</p>
    </form>
    <%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>