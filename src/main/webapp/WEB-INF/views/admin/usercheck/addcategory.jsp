<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>물품 카테고리 추가하기</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
	    body {
	        font-family: Arial, sans-serif;
	        background-color: #f8f8f8;
	        padding: 20px;
	    }
	
	    h1, h2 {
	        color: #333;
	        margin-bottom: 20px;
	    }
	
	    table {
	        width: 100%;
	        border-collapse: collapse;
	    }
	
	    td {
	        vertical-align: top;
	        border: 1px solid #ddd;
	        padding: 10px;
	        background-color: #fff;
	        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
	    }
	
	    .scrollable {
	        max-height: 300px; 
	        overflow-y: auto;
	        border: 1px solid #ddd;
	        padding: 10px;
	        margin-bottom: 20px;
	    }
	
	    form {
	        background-color: #fff;
	        padding: 20px;
	        border-radius: 8px;
	        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	    }
	
	    input[type="text"], input[type="file"], select {
	        width: 100%;
	        padding: 10px;
	        margin: 5px 0 20px 0;
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
	    }
	
	    input[type="submit"]:hover {
	        background-color: #45a049;
	    }
	</style>

</head>
<body>
    <%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
    <c:if test="${isImg.equals('0')}">
    	<script type="text/javascript">
    		alert("카테고리추가실패...");
   		</script>
    </c:if>
    
    <% if (request.getAttribute("operationSuccess") != null) { %>
    <script>alert('<%=request.getAttribute("operationSuccess")%>');</script>
	<% } %>
	<% if (request.getAttribute("fileFormatError") != null) { %>
    <script>alert('<%=request.getAttribute("fileFormatError")%>');</script>
	<% } %>
    
    
    
    <h1>물품별 카테고리 추가하기</h1>
    <table>
        <tr>
            <td>
                <h2>대분류</h2>
                <div class="scrollable">
                    <c:forEach var="cate" items="${catelist}">
                        <c:if test="${cate.cate2 == 0 && cate.cate3 == 0}">
                            ${cate.catename} <br/>
                        </c:if>
                    </c:forEach>
                </div>
            </td>
            <td>
                <h2>중분류</h2>
                <div class="scrollable">
                    <c:forEach var="cate" items="${catelist}">
                        <c:if test="${cate.cate2 > 0 && cate.cate3 == 0}">
                            ${cate.catename} <br/>
                        </c:if>
                    </c:forEach>
                </div>
            </td>
        </tr>
    </table>
    <h2>대분류 추가하기</h2>
    <form action="/admin/addCatePro" method="post" id="addCate" enctype="multipart/form-data"> 
        <input type="text" name="addCate" placeholder="대분류 추가하기">
        <input type="file" name="categoryImage" accept="image/*">
        <input type="submit" value="추가">
        <input type="hidden" value="${num}" name="num" />
    </form>
    
    <div>
    	<jsp:include page="/WEB-INF/views/admin/usercheck/addDetailCate.jsp" />
    </div>
    
    
    
     <div>
    	<jsp:include page="/WEB-INF/views/admin/usercheck/updateSubCate.jsp" />
    </div>
    
    
    <script>
    $(document).ready(function() {
        var existingCateNames = [];
        <c:forEach var="cate" items="${catelist}">
            <c:if test="${cate.cate2 == 0 && cate.cate3 == 0}">
                existingCateNames.push("${cate.catename}");
            </c:if>
        </c:forEach>

        $('#addCate').on('submit', function(e) {
            var newCateName = $('input[name="addCate"]').val().trim();

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
            } else {
            	var numValue = parseInt($('input[name="num"]').val());
                $('input[name="num"]').val(numValue + 1);
            }
        });
        if('${isImg}'==='1') {
            alert('대분류 카테고리가 정상적으로 추가되었습니다.');
		}

  
    </script>
</body>
</html>


