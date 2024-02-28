<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<div class="content-box" style="background: #fff;border: 2px solid #ddd;padding: 20px;"> 
        <h2>📰<b>농순배 멤버쉽</b>📰</h2><br />
        <h3>월 4,900원</h3><br />
        <div class="highlight-box" style="border: 2px solid #ddd;padding: 20px;margin: 20px auto;width: 60%;background-color: #FFFFE4;box-shadow: 0px 0px 10px #ccc;"> 
            <h4>💚 더욱 상세한 상품 가격 차트 </h4><br />
            <h4>💚 내 상점의 효과적인 노출 </h4><br />
            <h4>💚 농순배를 더 유용하게 사용하는 법! </h4>
        </div>
        <h6 class="text-muted">구독 상품 구매 시 해당 결제일에 맞춰 다음 달 결제일에 등록하신 카드로 자동 결제됩니다.</h6>
        <h6 class="text-muted">자세한 문의사항은 고객센터 연락 부탁드립니다. </h6> <br />
     </div>
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
		<div style="margin-top: 30px;">
			<h5>고객님은 현재 멤버쉽 회원입니다.</h5>
			<h5>다음 결제일</h5>
			<h5><b>${nextPayDate}</b></h5>
			<button type="button" id="btn_kakao-pay" class="btn btn-success" style ="margin-top : 30px; margin-bottom: 30px; background-color: red;" onclick="window.location.href='/user/quitMembership'">멤버쉽 해지하기</button>
		</div>
		</c:if>
	</div>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>