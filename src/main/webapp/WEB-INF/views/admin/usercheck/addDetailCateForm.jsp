<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            padding: 20px;
        }

        h4 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        input[type="text"], input[type="file"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
	</head>
	<body>
	<h4>아래의 항목은 중복 추가할 수 없습니다. </h4>
	<c:forEach var="cate" items="${detailCateList}">
          ${cate.catename}
   </c:forEach>
   <form action="/admin/addDetailCatePro" method="post" id="addDetailCate" enctype="multipart/form-data"> 
	  <input type="text" name="addDetail" placeholder="품목적기">
	  <input type="hidden" value="${cate1Select}" name="cate1Select" /> 
	  <input type="hidden" value="${subMaxNum}" name="subMaxNum" />
	  <input type="hidden" value="${datailMaxNum}" name="datailMaxNum" />
	  <input type="file" name="addImage" accept="image/*">
	  <input type="submit" value="추가">
   </form>	
   
   
   <script>
	$(document).ready(function() {
	    var existingCateNames = [];
	    <c:forEach var="cate" items="${detailCateList}">
	        existingCateNames.push("${cate.catename}");
	    </c:forEach>
	
	    $('#addDetailCate').on('submit', function(e) {
	        var newCateName = $('input[name="addDetail"]').val().trim();
	
	        // 입력 필드가 비어 있을 경우
	        if (!newCateName) {
	            alert('항목을 입력해주세요.');
	            e.preventDefault(); // 폼 제출 방지
	            return;
	        }
	
	        // 중복 체크
	        if (existingCateNames.includes(newCateName)) {
	            alert('중복된 항목은 추가할 수 없습니다.');
	            e.preventDefault(); // 폼 제출 방지
	            return;
	        }
	
	        // datailMaxNum 값 증가
	        var numValue = parseInt($('input[name="datailMaxNum"]').val());
	        $('input[name="datailMaxNum"]').val(numValue + 1);
	    });
	
	});
	</script>

   
   
   
	</body>
</html>