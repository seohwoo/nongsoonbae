<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<h1>농순배 멤버쉽</h1>
		<p>이런게 좋아요</p>
		어쩌고저쩌고<br />
		...<br />
		...<br />
		와우~~~<br />
		<h2>단돈 4900원!!!!!!!!</h2>
		<c:if test="${!ismem}">
			<button type="button" id="btn_kakao-pay" class="btn btn-success" style ="margin-top : 30px; margin-bottom: 30px;">지금 바로 가입하기</button>
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
		</c:if>
		<c:if test="${ismem}">
			<h1>마지막 결제일</h1>
			<h1><fmt:formatDate value="${lastMembershipPayDate}" dateStyle="long" type="both"/></h1>
			<button type="button" id="btn_kakao-pay" class="btn btn-success" style ="margin-top : 30px; margin-bottom: 30px; background-color: red;" onclick="window.location.href='/user/quitMembership'">멤버쉽 해지하기</button>
		</c:if>
	</div>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>