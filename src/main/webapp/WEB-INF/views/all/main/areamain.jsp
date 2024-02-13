<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>지역별</title>
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
	            // 정렬 버튼 클릭 이벤트
	            $(".sort-btn").click(function(){
	                var sort = $(this).data("sort");
	                $("#sortForm input[name='sort']").val(sort);
	                $("#sortForm").submit();
	            });
	        });
		    function checkAndRedirect(e, area1Value) {
		            if (area1Value === '0') {
		                window.location.href = '/nsb/area';
		                e.preventDefault(); // 기본 동작 방지
		            }
		        }
	    </script>
	</head>
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<header class="intro">
	  <div class="intro-slideshow">
	    <img src="https://images.unsplash.com/photo-1597395167758-21b8c03b57cf?q=80&w=2072&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" style="opacity: 1;">
	  </div>
	  <div class="intro-back">
	  	<div class="intro-header">
	    	<h1>전국 특산품</h1>
	  	</div>
	  </div>
	</header>
	<div style="display: flex;" class="catelist">
	    <form action="/nsb/area" method="get">
	        <button class="catebtn" type="submit" style="margin-right: 10px;">전체</button>
	    </form>
	    <c:forEach var="dto" items="${arealist}" varStatus="status">
	        <div style="margin-right: 10px;">
	            <form class="areaSelectForm" action="/nsb/area" method="post" onsubmit="checkAndRedirect(event, '${dto.area1}')">
	                <input type="hidden" name="areaNum" value="${areaNum}" />
	                <input type="hidden" name="area1" value="${dto.area1}" />
	                <button type="submit" class="catebtn">${dto.areaname}</button>
	            </form>
	        </div>
	    </c:forEach>
	</div>
	<div class="areabtn">
	<c:if test="${count > 0}">
	    <form action="/nsb/area" method="get">
	        <input type="hidden" name="areaNum" value="1"/>
	        <button type="submit" class="catebtn">👈</button>
	    </form>
	    <form action="/nsb/area" method="get">
	        <input type="hidden" name="areaNum" value="2"/>
	        <button type="submit" class="catebtn">👉</button>
	    </form>
	</c:if>
	</div>
	<c:if test="${allCnt > 0}">
		<form id="sortForm" action="/nsb/area" method="post">
    		<input type="hidden" name="sort" value=""/>
    		<button type="submit" name="sort" value="readcnt">추천순</button>
    		<button type="submit" name="sort" value="wishcnt">최신순</button>
		</form>
	<div class="container mx-auto mt-4">
 			<div class="row">
	    <c:forEach var="dto" items="${allprocuctList}">
	        <%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>
	    </c:forEach>
	    </div>
	</div>
	</c:if>
	<div id="targetDiv">
	    <jsp:include page="/WEB-INF/views/all/main/arealist.jsp" />
	</div>
	<div class="pagination">
	<c:if test="${!isAreaSelected}">
		<c:if test="${startPage > 10}">
			<form action="/nsb/area" method="post">
				<input type="hidden" name="pageNum" value="${startPage-10}">
				<button type="submit">이전</button>
			</form>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<form action="/nsb/area" method="post">
				<input type="hidden" name="pageNum" value="${i}">
				<button type="submit">${i}</button>
			</form>
		</c:forEach>
		<c:if test="${endPage < pageCount}">
			<form action="/nsb/area" method="post">
				<input type="hidden" name="pageNum" value="${startPage+10}">
				<button type="submit">다음</button>
			</form>
		</c:if>
		</c:if>					
		</div>				    
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>
