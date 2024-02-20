<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script type="text/javascript">
		    $(document).ready(function(){
	            $(".sort-btn").click(function(){
	                var sort = $(this).data("sort");
	                $("#sortForm input[name='sort']").val(sort);
	                $("#sortForm").submit();
	            });
	        });
	        function setSortAndSubmit(sortValue) {
	            var form = document.getElementById('sortForm');
	            form.sort.value = sortValue;
	            form.submit();
	        }
	        function goToPageWithSort(pageNum, sortValue) {
	            var form = document.getElementById('sortForm');
	            if (!form.pageNum) {
	                var pageNumInput = document.createElement('input');
	                pageNumInput.type = 'hidden';
	                pageNumInput.name = 'pageNum';
	                form.appendChild(pageNumInput);
	            }
	            form.pageNum.value = pageNum;
	            form.sort.value = sortValue;
	            form.submit();
	        }
	        function setSortAndSubmitDropdown() {
	            var selectedSort = document.getElementById('sortSelect').value;
	            document.getElementById('sortInput').value = selectedSort;
	            var urlParams = new URLSearchParams(window.location.search);
	            document.getElementById('sortForm').cate1.value = urlParams.get('cate1');
	            document.getElementById('sortForm').cate2.value = urlParams.get('cate2');
	            document.getElementById('sortForm').cate3.value = urlParams.get('cate3');

	            document.getElementById('sortForm').submit();
	        }
	    </script>
	
	</head>
	<body>
		<h1>${catename}</h1>
		<c:if test="${productCnt==0}">
			<h1>상품을 준비중입니다</h1>
		</c:if>
		<c:if test="${productCnt>0}">

		<form id="sortForm" action="/nsb/main" method="get">
			<input type="hidden" name="categoryNum" value="${categoryNum}"/>
		    <input type="hidden" name="pageNum" value="1" />
		   	<input type="hidden" name="cate1" value="${cate1}"/>
			<input type="hidden" name="cate2" value="${cate2}"/>
			<input type="hidden" name="cate3" value="${cate3}"/>
		    <input type="hidden" name="sort" id="sortInput" /> 
		    <select id="sortSelect" onchange="setSortAndSubmitDropdown()">
		        <option value="">기본정렬(최신순)</option>
		        <option value="readcnt" ${sort == 'readcnt' ? 'selected' : ''}>인기순</option>
		        <option value="wishcnt" ${sort == 'wishcnt' ? 'selected' : ''}>찜 많은 순</option>
		        <option value="cheap" ${sort == 'cheap' ? 'selected' : ''}>가격 낮은 순</option> 
		    </select>
		</form>
		<div class="container mx-auto mt-4">
			<div class="row">
				<c:if test="${adCnt>0}">
					<c:forEach var="ad" items="${adList}">
						<%@include file="/WEB-INF/views/all/main/adListComponent.jsp"%>	
					</c:forEach>
				</c:if>
			</div>

 		  <div class="row">
			<c:forEach var="dto" items="${productList}">
				<%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>	
			</c:forEach>
			</div>
		  </div>
		  <c:if test="${startPage > 10}">
	    		<form action="/nsb/main" method="get">
	    			<input type="hidden" name="categoryNum" value="${categoryNum}"/>
	       			<input type="hidden" name="pageNum" value="${startPage-10}">
	       			<input type="hidden" name="cate1" value="${cate1}"/>
					<input type="hidden" name="cate2" value="${cate2}"/>
					<input type="hidden" name="cate3" value="${cate3}"/>
	       			<button type="submit">이전</button>
	    		</form>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
		   		 <form id="pageForm" action="/nsb/main" method="get">
		   			<input type="hidden" name="categoryNum" value="${categoryNum}"/>
		   		 	<input type="hidden" name="pageNum" value="${i}">
		   		 	<input type="hidden" name="cate1" value="${cate1}"/>
					<input type="hidden" name="cate2" value="${cate2}"/>
					<input type="hidden" name="cate3" value="${cate3}"/>
					<button type="button" onclick="goToPage(${i});">${i}</button>
		    	 </form>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
	    		<form action="/nsb/main" method="get">
	    			<input type="hidden" name="categoryNum" value="${categoryNum}"/>
	     	   		<input type="hidden" name="pageNum" value="${startPage+10}">
	        		<input type="hidden" name="cate1" value="${cate1}"/>
					<input type="hidden" name="cate2" value="${cate2}"/>
					<input type="hidden" name="cate3" value="${cate3}"/>
	        		<button type="submit">다음</button>
	    		</form>
			</c:if>
		  
		</c:if>
	</body>
</html>