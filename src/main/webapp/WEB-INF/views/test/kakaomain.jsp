<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>카카오페이버튼</title>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<button id="btn_kakao-pay">kakao</button>
		<script>
			$(function(){
			$("#btn_kakao-pay").click(function(){
				// 카카오페이 결제전송
				$.ajax({
					type:'POST'
					,url:'/test/ready'
					,data: {
	                	
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