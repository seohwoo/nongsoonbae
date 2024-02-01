<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<main class="col-9 py-md-5 pl-md-5 bd-content" role="main" style="margin-top: 30px; margin-left: auto; margin-right : auto;">
      
      		<h1>${username}님! 어서오세요.</h1><br />
      		<div class="col d-flex">
	      		<div class="col p-4 m-4 d-flex flex-column position-static bg-body-tertiary border rounded-3">
		          <strong class="d-inline-block mb-2 text-primary">Modify</strong>
		          <h3 class="mb-0">내 정보 수정</h3>
		          <div class="mb-1 text-muted"><hr></div>
		          <div></div>
		          <p class="card-text mb-auto">사이트 이용을 위한 주소지, 전화번호 입력, 비밀번호 변경이 가능합니다.</p>
		          <a href="../member/detailsForm">이동하기 ></a>
		       </div>
	      		<div class="col p-4 m-4 d-flex flex-column position-static bg-body-tertiary border rounded-3">
		          <strong class="d-inline-block mb-2 text-primary">My Shop</strong>
		          <h3 class="mb-0">내 상점 정보</h3>
		          <div class="mb-1 text-muted"><hr></div>
		          <div></div>
		          <p class="card-text mb-auto">개인 상점 개설부터 관리까지. 지금 바로 농순배에서 직거래 시작.</p>
		          <a href="../product/productMain">이동하기 ></a>
		        </div>
      		</div>
      <a class="mt-5 mb-3 text-muted" href="/user/delete">회원 탈퇴</a>
    </main>
</body>
</html>