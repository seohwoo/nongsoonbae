<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	    <script>
	        $(document).ready(function(){
	            // 정렬 버튼 클릭 이벤트
	            $(".sort-btn").click(function(){
	                var sort = $(this).data("sort");
	                $("#sortForm input[name='sort']").val(sort);
	                $("#sortForm").submit();
	            });
	        });
	    </script>
	</head>
	<body>
		<c:if test="${areacnt > 0}">
		    <form action="/nsb/area" method="get">
		        <input type="hidden" name="areaNum" value="1"/>
		        <button type="submit">👈</button>
		    </form>
		    <form action="/nsb/area" method="get">
		        <input type="hidden" name="areaNum" value="2"/>
		        <button type="submit">👉</button>
		    </form>
		</c:if>
		<div style="display: flex;">
			<c:if test="${cnt == 0 }" >
				   <h1> 상품을 준비 중입니다. </h1>
			</c:if>
		</div>
		<div style="display: flex;">
			<c:if test="${cnt >  0 }" >
				<form id="sortForm" action="/nsb/area" method="get">
		            <input type="hidden" name="sort" value=""/>
		            <button type="button" class="sort-btn" data-sort="readcnt">추천순</button>
		            <button type="button" class="sort-btn" data-sort="wishcnt">최신순</button>
	        	</form>
			<div class="container mx-auto mt-4">
	 			<div class="row">
				<c:forEach var="dto" items="${productlist}">
				     <%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>
				</c:forEach>  
				</div>
			</div>
			</c:if>	
			
		</div>
	</body>	
</html>
