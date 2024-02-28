<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>카테고리 추가하기</title>
    <link rel="icon" href="/resources/img/logo.png">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
    @import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900|Noto+Serif:400,700,700i&display=swap');
	    body {
	       font-family: 'Noto Sans KR', sans-serif; 
	        background-color: #f5f5f5;
	        margin: 0;
	        padding: 10px;
	        padding-top: 300px;
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
	    width: 150px; /* 버튼의 길이를 150px로 설정 */
	}
	
	input[type="submit"]:hover {
	    background-color: #45a049;
	}
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
	    .content {
	        padding-top: 100px; /* 네비게이션 바 높이보다 약간 더 큰 값으로 설정 */
	        
	        padding-right: 20px;
	    }
	   
	    .usernav {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        background-color: #fff;
        z-index: 1000; /* 다른 콘텐츠 위에 위치하도록 z-index 설정 */
    }
    
    .content {
        padding-top: 100px; /* 네비게이션 바 높이보다 약간 더 큰 값으로 설정 */
        padding-right: 20px;
    }
	</style>

</head>
<body>
    <div class="usernav">
    	<%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
	</div>
	<div class="content">
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
    <h2>대분류 카테고리 추가하기</h2>
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
    
    </div>
   <script type="text/javascript">
    $(document).ready(function() {
        var existingCateNames = [];
        <c:forEach var="cate" items="${catelist}">
            <c:if test="${cate.cate2 == 0 && cate.cate3 == 0}">
                existingCateNames.push("${cate.catename}");
            </c:if>
        </c:forEach>

        $('#addCate').on('submit', function(e) {
            var newCateName = $('input[name="addCate"]').val().trim();

            if (!newCateName) {
                alert('항목을 입력해주세요.');
                e.preventDefault();
                return;
            }

            if (existingCateNames.includes(newCateName)) {
                alert('중복된 항목은 추가할 수 없습니다.');
                e.preventDefault();
                return;
            }
        });

        var status = <c:out value="${status}" default="-1" />;
        if (status !== -1) {
            if (status === 1) {
                alert("추가가 완료되었습니다.");
            } else if (status === 0) {
                alert("유효하지 않은 파일 형식입니다.");
            }
        }

        var substatus = <c:out value="${substatus}" default="-1" />;
        if (substatus !== -1) {
            if (substatus === 1) {
                alert("추가가 완료되었습니다.");
            }
        }
    });
</script>

</body>
</html>


