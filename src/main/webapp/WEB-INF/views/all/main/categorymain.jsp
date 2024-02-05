<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>카테고리별</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script type="text/javascript">
            function checkAndRedirect(cate1Value) {
                if (cate1Value === '0') {
                    window.location.href = '/nsb/menu';
                    $('#catelist').hide();
                    return false; 
                }
                return true; // 폼 제출 허용
            }
        </script>
	</head>
	<body>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
			<h1 class="title">카테고리</h1><br />
			<div style="display: flex; padding-left : 30%; padding-right: 30%;">
		    <c:forEach var="dto" items="${catelist}">
		        <div style="margin-right: 10px;">
		           <form action="/nsb/menu" method="post" onsubmit="return checkAndRedirect('${dto.cate1}')">
			            <input type="hidden" name="cate1" value="${dto.cate1}" />
		 	            <button class="btn" type="submit">
		 	            	<c:if test='${dto.img.endsWith(".png") || dto.img.endsWith(".jpg") || dto.img.endsWith(".jpeg")}'>
		 	            		<img src="/resources/img/${dto.img}" border="0" width="100" height="100">
		 	            	</c:if>
		 	            	<c:if test='${!dto.img.endsWith(".png") && !dto.img.endsWith(".jpg") && !dto.img.endsWith(".jpeg")}'>
			 	            	<img src="${dto.img}" border="0" width="100" height="100">
		 	            	</c:if>
			 	            <br />
			                <span>${dto.catename}</span>
		                </button>
		            </form>
		        </div>
		    </c:forEach>
			</div>
			 <c:if test="${request.getRequestURI() eq '/nsb/menu'}">
		        <select id="pageSelect">
		            <option value="">정렬방식</option>
		            <option value="page1.jsp">인기순</option>
		            <option value="page2.jsp">최신순</option>
		            <option value="page3.jsp">찜많은순</option>
		        </select>
 		   </c:if>
			<c:if test="${allCnt >  0 }" >
			<div class="container mx-auto mt-4">
 			  <div class="row">
				<c:forEach var="dto" items="${allprocuctList}">
					<%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>
				</c:forEach>
				</div>
				</div>
			</c:if>	
			<div id="catelist">
				<jsp:include page="/WEB-INF/views/all/main/categorylist.jsp" />
			</div>	
			<div>
				<jsp:include page="/WEB-INF/views/all/main/catelistDetail.jsp" />
			</div>
			<%@include file="/WEB-INF/views/include/footer.jsp"%>
			
			<script>
			document.getElementById('pageSelect').addEventListener('change', function() {
			    var page = this.value;
			    if(page) {
			        window.location.href = page;
			    }
			});
			</script>
			
	</body>
</html>

