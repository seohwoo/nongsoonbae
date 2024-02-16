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
	        function setSortAndSubmit(sortValue) {
		        var form = document.getElementById('sortForm');
		        form.sort.value = sortValue;
		        form.submit();
		    }
	        function goToPageWithSort(pageNum, sortValue) {
	            var form = document.getElementById('sortForm'); // 폼 ID
	            if (!form.pageNum) {
	                var pageNumInput = document.createElement('input');
	                pageNumInput.type = 'hidden';
	                pageNumInput.name = 'pageNum';
	                form.appendChild(pageNumInput);
	            }
	            form.pageNum.value = pageNum;
	            form.sort.value = sortValue;
	            form.submit(); // 폼 제출
	        }
	        function setSortAndSubmitDropdown() {
	            var selectedSort = document.getElementById('sortSelect').value;
	            document.getElementById('sortInput').value = selectedSort;
	            document.getElementById('sortForm').submit();
	        }
	    </script>
	</head>
	<body>
	   <div style="display: flex;">
		<c:if test="${cntDetail == 0 }" >
	         <h1 class="catenull"> 상품 없음! </h1>
	         </c:if>
	         <c:if test="${cntDetail >  0 }" >
	         	<form id="sortForm" action="/nsb/menu" method="get">
				        <input type="hidden" name="pageNum" value="1" />
				        <input type="hidden" name="cate1" value="${cate1}" />
				        <input type="hidden" name="cate2" value="${cate2}" />
				        <input type="hidden" name="sort" id="sortInput" /> 
				        <select id="sortSelect" onchange="setSortAndSubmitDropdown()">
					        <option value="">기본정렬(최신순)</option>
					        <option value="readcnt" ${sort == 'readcnt' ? 'selected' : ''}>인기순</option>
					        <option value="wishcnt" ${sort == 'wishcnt' ? 'selected' : ''}>찜 많은 순</option>
					   		<option value="cheap" ${sort == 'cheap' ? 'selected' : ''}>가격 낮은 순</option>
					    </select>
    			</form>
    			<c:if test="${adCntDetail >  0 }" >
	    			<div class="container mx-auto mt-4">
		 				<div class="row">
				            <c:forEach var="ad" items="${adproductdetail}">
				               <%@include file="/WEB-INF/views/all/main/adListComponent.jsp"%>
				            </c:forEach>
			            </div>
		            </div>
	       		</c:if>
	         	<div class="container mx-auto mt-4">
	 				<div class="row">
		            <c:forEach var="dto" items="${productlistdetail}">
		               <%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>
		            </c:forEach>
		            </div>
	            </div>
	         </c:if>	
	     </div>
	     <div class="pagination">
				<c:if test="${isCateSelect == 2 && !empty sort}">
					<c:if test="${startPage > 10}">
			    		<form action="/nsb/menu" method="get">
			       			<input type="hidden" name="pageNum" value="${startPage-10}">
			       			<input type="hidden" name="cate1" value="${cate1}">  
			       			<input type="hidden" name="cate2" value="${cate2}">        			
			       			<input type="hidden" name="sort" value="${sort}">
			       			<button type="submit">이전</button>
			    		</form>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
				   		 <form action="/nsb/menu" method="get">
				        	<button type="button" onclick="goToPageWithSort(${i}, '${sort}')">${i}</button>
				    	 </form>
					</c:forEach>
					<c:if test="${endPage < pageCount}">
				    	<form action="/nsb/menu" method="get">
				     	   	<input type="hidden" name="pageNum" value="${startPage+10}">
				        	<input type="hidden" name="cate1" value="${cate1}">
				        	<input type="hidden" name="cate2" value="${cate2}">
				        	<input type="hidden" name="sort" value="${sort}"> 
				        	<button type="submit">다음</button>
				    	</form>
					</c:if>
					</c:if>	
					<c:if test="${isCateSelect == 2 && empty sort}">
						<c:if test="${startPage > 10}">
				    		<form action="/nsb/menu" method="get">
				       			<input type="hidden" name="pageNum" value="${startPage-10}">
				       			<input type="hidden" name="cate1" value="${cate1}">
				       			<input type="hidden" name="cate2" value="${cate2}">
				       			<button type="submit">이전</button>
				    		</form>
						</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
				   		 <form action="/nsb/menu" method="get">
				   		 	<input type="hidden" name="pageNum" value="${i}">
				   		 	<input type="hidden" name="cate1" value="${cate1}">
				   		 	<input type="hidden" name="cate2" value="${cate2}">
							<button type="submit">${i}</button>
				    	 </form>
					</c:forEach>
					<c:if test="${endPage < pageCount}">
			    		<form action="/nsb/menu" method="get">
			     	   		<input type="hidden" name="pageNum" value="${startPage+10}">
			        		<input type="hidden" name="cate1" value="${cate1}">
			        		<input type="hidden" name="cate2" value="${cate2}">
			        		<button type="submit">다음</button>
			    		</form>
					</c:if>
				</c:if>				
		</div>
		
   </body>
</html>