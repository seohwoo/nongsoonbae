<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	    <style>
	    select, button {
	        padding: 8px 15px;
	        margin-right: 10px;
	        border: 1px solid #ccc;
	        border-radius: 4px;
	        background-color: #f8f8f8;
	        cursor: pointer;
		    }
		
		    button {
		        background-color: #4CAF50;
		        color: white;
		        border: none;
		    }
		
		    button:hover {
		        background-color: #45a049;
		    }
	    
	    
	    
	    </style>
	</head>
	<body>
		<c:if test="${etcCnt  == 0 }" >
			<h3> 아직 기타 품목에 추가된 상품이 없습니다. </h3>
		</c:if>
		<c:if test="${etcCnt  > 0 }" >
	    <form action="/admin/updateSubCatePro" method="post" onsubmit="return validateForm()">
	        <h3> 아래 품목으로 중분류 카테고리 생성하기 (3개부터 카테고리를 추가할 수 있습니다.)</h3>
		        <c:forEach var="cate" items="${etcList}">
		            ${cate.catename}<br>
		        </c:forEach>
	        <input type="text" name="newCateName" placeholder="추가할 카테고리의 이름을 써주세요." />
	        <input type="hidden" value="${subMaxNum}"  name="subMaxNum"/>
	        <input type="hidden" value="${cate1Select}" name="cate1Select" />
	        <input type="submit" value="카테고리 추가하기">
	    </form>   
	    </c:if>
	    <script>
	        function validateForm() {
	            var cateCount = ${fn:length(etcList)};
	            var newCateName = document.getElementsByName("newCateName")[0].value;
	
	            if (newCateName.trim() === "") {
	                alert('항목을 적어주세요.');
	                return false;
	            }
	
	            if (cateCount < 3) {
	                alert('카테고리 생성은 3개 이상부터 가능합니다.');
	                return false;
	            }
	            
	        }
	    </script>
	</body>
</html>
