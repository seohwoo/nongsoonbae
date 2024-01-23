<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	</head>
	<body>
	<h3> 기존 분류 항목 (기존에 있는 항목은 추가할 수 없습니다. 두개 항목 이상의 물품을 정해서 넣어주세요. ex. 키위/파인애플)</h3> <br />
		<c:forEach var="subCate" items="${subcatelist}">
   		 <span class="subCate">${subCate.catename}</span> <br/>
		</c:forEach>

		<h3>중분류에 해당하는 각각의 품목을 입력해주세요</h3>
		<form action="/admin/addSubCatePro" method="post" id="addSubCate" enctype="multipart/form-data"> 
	        <input type="text" name="addSubCate" placeholder="중분류 추가하기">
	        <input type="submit" value="추가">
	        <input type="hidden" value="${cate1Select}" name="cate1Select" />
	        <input type="hidden" id="subMaxNum" name="subMaxNum" value="${subMaxNum}" />
   			<input type="text" name="addCate1" placeholder="품목적기">
       		<input type="hidden" value="1" name="addCateNum1" />
        	<input type="file" name="categoryImage1" accept="image/*">
        	<input type="text" name="addCate2" placeholder="품목적기">
        	<input type="file" name="categoryImage2" accept="image/*">
        	<input type="hidden" value="2" name="addCateNum2" />
   	 	</form>	
   	 	
   	 	
		<script>
		    $(document).ready(function() {
		        var existingCateNames = [];
		        $('.subCate').each(function() {
		            existingCateNames.push($(this).text().trim().toLowerCase()); // 공백 제거 및 소문자 변환
		        });
		
		        $('#addSubCate').on('submit', function(e) {
		            var newCateName = $('input[name="addSubCate"]').val().trim().toLowerCase(); // 공백 제거 및 소문자 변환
		            var addCate1 = $('input[name="addCate1"]').val().trim();
		
		            // '중분류 추가하기' 입력 필드가 비어 있을 경우
		            if (!newCateName) {
		                alert('중분류 항목을 입력해주세요.');
		                e.preventDefault(); // 폼 제출 방지
		                return;
		            }
		
		            // 'addCate1' 입력 필드가 비어 있을 경우
		            if (!addCate1) {
		                alert('품목을 입력해주세요.');
		                e.preventDefault(); // 폼 제출 방지
		                return;
		            }
		
		            // 중복 확인
		            if (existingCateNames.includes(newCateName)) {
		                alert('이미 존재하는 항목입니다.');
		                e.preventDefault(); // 폼 제출 방지
		                return;
		            }
		
		            // subMaxNum 값을 증가시키고 업데이트
		            var subMaxNum = $('#subMaxNum').val();
		            $('#subMaxNum').val(parseInt(subMaxNum) + 1);
		
		            // 여기에 추가되었습니다 알림 추가
		            alert('추가되었습니다.');
		
		            // 폼 제출
		        });
		    });
		</script>

   	 	
	</body>
</html>