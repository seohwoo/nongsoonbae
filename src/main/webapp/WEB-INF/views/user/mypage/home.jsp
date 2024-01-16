<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<main class="col-9 py-md-3 pl-md-5 bd-content" role="main" style="margin-top: 30px; margin-left: auto; margin-right: auto;">
      <table>
      	<tr>
      		<td colspan="4">
      		<h1>${username}님! 어서오세요.</h1>
      		</td>
      	</tr>
      	<tr>
      		<td>
	      		<div class="col p-4 d-flex flex-column position-static">
		          <strong class="d-inline-block mb-2 text-primary">Modify</strong>
		          <h3 class="mb-0">내 정보 수정</h3>
		          <div class="mb-1 text-muted">Nov 12</div>
		          <p class="card-text mb-auto">사이트 이용을 위한 주소지, 전화번호 입력, 비밀번호 변경이 가능합니다.</p>
		          <a href="member/detailForm" class="stretched-link">이동하기 ></a>
		        </div>
      		</td>
      		<td>
	      		<div class="col p-4 d-flex flex-column position-static">
		          <strong class="d-inline-block mb-2 text-primary">Buy List</strong>
		          <h3 class="mb-0">멤버쉽 가입</h3>
		          <div class="mb-1 text-muted">Nov 12</div>
		          <p class="card-text mb-auto">상품 광고 및 상세 가격 차트까지. 농순배 멤버쉽으로 판매를 시작해보세요.</p>
		          <a href="#" class="stretched-link">이동하기 ></a>
		        </div>
      		</td>
      	</tr>
      </table>
    </main>
</body>
</html>