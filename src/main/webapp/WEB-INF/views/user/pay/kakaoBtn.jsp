<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>카카오페이버튼</title>
		<link rel="icon" href="/resources/img/logo.png">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<style type="text/css">
			.custom-button {
			    width: 100px; /* 버튼의 너비 */
			    height: 40px; /* 버튼의 높이 */
			    background-image: url('/resources/img/kakaoBtn.png'); /* 이미지 경로 */
			    background-size: cover; /* 이미지를 버튼에 맞게 조정 */
			    background-color: white;
			    border: none; /* 테두리 제거 */
			    cursor: pointer; /* 커서를 포인터로 변경하여 클릭 가능한 상태 표시 */
			}
		</style>
	</head>
	<body>
		<button id="btn_kakao-pay" class="custom-button"></button>
		<script>
			$(function(){
				$("#btn_kakao-pay").click(function(){
					// 카카오페이 결제전송
					$.ajax({
						type:'POST'
						,url:'/user/pay/ready'
						,data: {
							isMembership: '${isMembership}'
						}
						,success:function(response){
							location.href = response.next_redirect_pc_url;			
						}
					})
					// 버튼 클릭이벤트 해제
				})
			})	
		</script>
	</body>
</html>