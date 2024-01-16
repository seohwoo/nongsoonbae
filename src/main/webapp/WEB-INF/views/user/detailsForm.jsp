<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<title>detailsForm</title>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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
	$(function(){
		$("#update").on("click",function(){
			const address = $('#roadAddress').val() + " " + $('#detailAddress').val() + $('#extraAddress').val();
            $('#address').val(address);    
			$("#detailsForm").submit();
			alert("변경이 완료되었습니다.");
		});
	});
</script>
</head>
<body>
<%@include file="/WEB-INF/views/include/header.jsp"%>

<form class="form-signin" action="/member/details" method="POST" id="detailsForm" enctype="multipart/form-data" >
	<h1 class="h3 mb-3 font-weight-normal">회원정보 입력</h1>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<img src="/resources/img/default.png" style=" max-width: 50%;  height: auto;"> <br />
	프로필 사진 <input type="file" class="form-control" onchange="chooseImage(this)" required />
	주소<input type="text" class="form-control" id="postcode" placeholder="우편번호" readonly>
		<input type="button" class="form-control" onclick="DaumPostcode()" value="우편번호 찾기"><br>
		<input type="text" class="form-control" id="roadAddress" placeholder="도로명주소" readonly>
		<span id="guide" class="form-control" style="color:#999;display:none"></span>
		<input type="text" class="form-control" id="detailAddress" placeholder="상세주소" required>
		<input type="text" class="form-control" id="extraAddress" placeholder="참고항목" readonly>
		<input type="hidden" id="address" name="address" />
		
	전화번호<input type="text" name="phone" class="form-control" placeholder="PHONE NUMBER" required>
	<br />
	<div class="d-grid gap-2  mx-auto">
	  <input class="btn btn-lg btn-primary btn-block" type="button" id="update" value="UPDATE" />
	</div>
	
	<p class="mt-5 mb-3 text-muted">detailsForm.jsp</p>
</form>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>