<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<h2>품목 추가하기</h2>
		<h4>추가할 카테고리 선택하기</h4>
			<form id="cateSelectForm" action="/admin/addDetailCateForm" method="post">
		        <select id="cate1Select" name="cate1Select">
		            <c:forEach var="cate" items="${catelist}">
		                <c:if test="${cate.cate2 == 0 && cate.cate3 == 0}">
		                    <option value="${cate.cate1}">${cate.catename}</option> 
		                </c:if>
		            </c:forEach>
		        </select>
	        	<button id="submitBtn">선택</button>
	   		 </form>
		<div id="subCateContainer" style="display:none;">
	    	<jsp:include page="/WEB-INF/views/admin/usercheck/addDetailCateForm.jsp" />
	    </div> 
    
    
    <script>
    $('#submitBtn').on('click', function(e) {
        e.preventDefault();
        var cate1SelectValue = $('#cate1Select').val();
        $.ajax({
            url: '/admin/addDetailCateForm',
            type: 'POST',
            data: $('#cateSelectForm').serialize(),
            success: function(response) {
                $('#subCateContainer').html(response).show();
            },
            error: function(xhr, status, error) {
                console.error("오류 발생: " + error);
            }
        });
    });
    
    </script>
	</body>
</html>