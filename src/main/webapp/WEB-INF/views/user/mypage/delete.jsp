<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>
	<main class="col-9 py-md-5 pl-md-5 bd-content" role="main" style="margin-top: 30px; margin-left: auto; margin-right : auto;">
		<div class="container">
			<form class="form-signin" action="/user/deletePro" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="username" value="${username}" />
				<h2>탈퇴 하시겠습니까?</h2>
				<h5>탈퇴 시 그동안의 이용 내역이 모두 삭제되고</h5>
				<h5>동일 아이디로 재가입할 수 없습니다.</h5>
				<button class="btn btn-primary col-6" type="submit">탈퇴하기</button>
			</form>
		</div>
	</main>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>