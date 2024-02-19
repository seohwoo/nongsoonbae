<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>중분류 항목 추가하기</title>
	</head>
	<body>
	<h3> 기타 추가 된 품목 중분류로 합치기 </h3>
		<form id="cateSelect" action="/admin/updateSubCateForm" method="post">
			<select id="cate1Select" name="cate1Select">
		    	<c:forEach var="cate" items="${catelist}">
		        	<c:if test="${cate.cate2 == 0 && cate.cate3 == 0}">
		                  <option value="${cate.cate1}">${cate.catename}</option> 
		            </c:if>
		         </c:forEach>
		     </select>
	        	<button id="submitUpdateBtn">조회하기</button>
	   	</form>
	
		<div id="etcCateContainer" style="display:none;">
	    	<jsp:include page="/WEB-INF/views/admin/usercheck/updateSubCateForm.jsp" />
	    </div>
    
    
	<script>
    $('#submitUpdateBtn').on('click', function(e) {
        e.preventDefault();
        var cate1SelectValue = $('#cate1Select').val();
        $.ajax({
            url: '/admin/updateSubCateForm',
            type: 'POST',
            data: $('#cateSelect').serialize(),
            success: function(response) {
                $('#etcCateContainer').html(response).show();
            },
            error: function(xhr, status, error) {
                console.error("오류 발생: " + error);
            }
        });
    });
    </script>
	</body>
</html>