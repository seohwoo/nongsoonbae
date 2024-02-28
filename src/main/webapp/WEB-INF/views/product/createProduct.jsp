<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상점 등록</title>
		<link rel="icon" href="/resources/img/logo.png">
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	</head>
	
	<script>
		function DaumPostcode() {
	    	new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수
	
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById("roadAddress").value = roadAddr;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if(roadAddr !== ''){
	                    document.getElementById("extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("extraAddress").value = '';
	                }
	
	                var guideTextBox = document.getElementById("guide");
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';
	
	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }   
	            }
	        }).open();
		}
		
		$(function() {   
			$("#createProduct").on("submit", function() {
				if (!validateForm()) {
					return false; // 폼 제출을 막음
				}
				return true;
			});
		}); 
	</script>
	
	<body style="min-height: 600px;">
	<%@include file="/WEB-INF/views/include/header.jsp"%>
		<form class="form-product" action="/product/createProductPro" method="post" id="createProduct" style="margin-bottom:300px; margin-left: auto; margin-right: auto; width: 850px">
			<h3>상점 정보 입력</h3>
			<table width="850" border="1">
				<tr> 
					<td width="200">상점이름</td>
					<td width="400"> 
						<input type="text" name="shopname" class="form-control" required="required" style="width: 76%;">
					</td>
			    </tr>
			    
			    <tr> 
					<td width="200">상점소개</td>
					<td width="400"> 
						<input type="text" name="shopcontent" class="form-control" required="required" style="width: 76%;">
					</td>
			    </tr>
			    <tr>
			    	<td>
			    	주소 
			    	</td>
			    	<td>
			    	<div style="width: 80%; padding-right: 10px; padding-left: 10px;">
				    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				   		<input type="text" class="form-control" name="postcode" id="postcode" placeholder="우편번호" readonly>
						<input type="button" class="form-control" onclick="DaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" class="form-control" name="roadAddress" id="roadAddress" placeholder="도로명주소" readonly>
						<span id="guide" class="form-control" style="color:#999;display:none"></span>
						<input type="text" class="form-control" name="detailAddress" id="detailAddress" placeholder="상세주소" required>
						<input type="text" class="form-control" name="extraAddress" id="extraAddress" placeholder="참고항목" readonly>
					</div>
					</td>
			    </tr>
			   
			    
			    <tr> 
					<td colspan="2" align="center"> 
						<input class="sellbutton" type="submit" value="나의 상점 만들기" >
						<input class="sellbutton" type="reset" name="reset" value="다시입력">
						<input class="sellbutton" type="button" value="등록안함" onclick="javascript:window.location='/user/mypage'">
					</td>
				</tr>			    
			</table>	
		</form>
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>