<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>카테고리별</title>
		<link rel="icon" href="/resources/img/logo.png">
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
            function checkAndRedirect(cate1Value) {
                if (cate1Value === '0') {
                    window.location.href = '/nsb/menu';
                    $('#catelist').hide();
                    return false; 
                }
                return true; // 폼 제출 허용
            }
            function setSortAndSubmitDropdown() {
	            var selectedSort = document.getElementById('sortSelect').value;
	            document.getElementById('sortInput').value = selectedSort;
	            document.getElementById('sortForm').submit();
	        }
        </script>
	</head>
	<body>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
			<header class="intro">
			  <div class="intro-slideshow">
			    <img src="https://images.unsplash.com/photo-1610576375407-23f9a9455daa?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" style="opacity: 1;">
			  </div>
			  <div class="intro-back">
			  	<div class="intro-header">
			    	<h1>카테고리</h1>
			  	</div>
			  </div>
			</header>
			<div style="display: flex; align-items: center; justify-content: center;">
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
			<c:if test="${allCnt >  0 }" >
				<form id="sortForm" action="/nsb/menu" method="get">
				    <input type="hidden" name="pageNum" value="1" />
				    <input type="hidden" name="sort" id="sortInput" /> 
				    <select class="btn dropdown-toggle" id="sortSelect" onchange="setSortAndSubmitDropdown()" style="background: #fff; margin: 10px;">
				        <option class="dropdown-item" value="">기본정렬(최신순)</option>
				        <option class="dropdown-item" value="readcnt" ${sort == 'readcnt' ? 'selected' : ''}>인기순</option>
				        <option class="dropdown-item" value="wishcnt" ${sort == 'wishcnt' ? 'selected' : ''}>찜 많은 순</option>
				        <option class="dropdown-item" value="cheap" ${sort == 'cheap' ? 'selected' : ''}>가격 낮은 순</option>
				    </select>
				</form>
				<!-- 광고상품 4개 랜덤 -->
				<c:if test="${adAllCnt >  0 }" >
					<div class="container mx-auto mt-4">
		 			  	<div class="row">
							<c:forEach var="ad" items="${adAllprocuct}">
								<%@include file="/WEB-INF/views/all/main/adListComponent.jsp"%>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<!--일반 상품 정렬  -->
				<div class="container mx-auto mt-4">
	 			  	<div class="row">
						<c:forEach var="dto" items="${allproductList}">
							<%@include file="/WEB-INF/views/all/main/listComponent.jsp"%>
						</c:forEach>
					</div>
				</div>
			</c:if>	
			<div >
				<jsp:include page="/WEB-INF/views/all/main/categorylist.jsp" />
			</div>	
			<div id="catelist">
				<jsp:include page="/WEB-INF/views/all/main/catelistDetail.jsp" />
			</div>
			<div class="pagination" style="justify-content:center; margin-top: 10px; margin-bottom: 20px;">
				<c:if test="${isCateSelect == 0 && !empty sort}">
					<c:if test="${startPage > 10}">
			    		<form action="/nsb/menu" method="get">
			       			<input type="hidden" name="pageNum" value="${startPage-10}">
			       			<input type="hidden" name="sort" value="${sort}"> <!-- 정렬 기준 포함 -->
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
			        		<input type="hidden" name="sort" value="${sort}"> <!-- 정렬 기준 포함 -->
			        		<button type="submit">다음</button>
			    		</form>
					</c:if>
				</c:if>	
				<c:if test="${isCateSelect == 0 && empty sort}">
					<c:if test="${startPage > 10}">
			    		<form action="/nsb/menu" method="get">
			       			<input type="hidden" name="pageNum" value="${startPage-10}">
			       			<button type="submit">이전</button>
			    		</form>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
				   		 <form action="/nsb/menu" method="get">
				   		 	<input type="hidden" name="pageNum" value="${i}">
							<button type="submit">${i}</button>
				    	 </form>
					</c:forEach>
					<c:if test="${endPage < pageCount}">
			    		<form action="/nsb/menu" method="get">
			     	   		<input type="hidden" name="pageNum" value="${startPage+10}">
			        		<button type="submit">다음</button>
			    		</form>
					</c:if>
				</c:if>								
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

