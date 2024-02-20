<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>농순배</title>		
		<script type="text/javascript">
	        var errorParam = '<%= request.getParameter("error") %>';
	        if (errorParam === 'true') {
	            // "error" 값이 true일 때 alert 띄우기
	            alert("접근 권한이 없습니다.");
	        }
	        
	    //이미지 슬라이드
	    document.addEventListener("DOMContentLoaded", function() {
		    var slideshowImages = document.querySelectorAll(".intro-slideshow img");
	
		    var nextImageDelay = 3000;
		    let currentImageCounter = 0;

		    slideshowImages[currentImageCounter].style.opacity = 1;
		    
		    setInterval(nextImage, nextImageDelay);
	
		    function nextImage() {
		      slideshowImages[currentImageCounter].style.opacity = 0;
	
		      currentImageCounter = (currentImageCounter+1) % slideshowImages.length;
	
		      slideshowImages[currentImageCounter].style.opacity = 1;
		    }
	    });
	    </script>
	    <script type="text/javascript" src="/resources/js/jquery-1.10.2.min.js"></script>
	</head>
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<header class="intro">
	  <div class="intro-slideshow">
	    <img src="https://images.unsplash.com/photo-1619082737911-1119bc3167ec?q=80&w=1932&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" style="opacity: 0;">
	    <img src="https://images.unsplash.com/photo-1504309250229-4f08315f3b5c?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" style="opacity: 0;">
	    <img src="https://images.unsplash.com/photo-1464226184884-fa280b87c399?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" style="opacity: 0;">
	    <img src="https://images.unsplash.com/photo-1461354464878-ad92f492a5a0?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" style="opacity: 1;">
	    <img src="https://images.unsplash.com/photo-1560693225-b8507d6f3aa9?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" style="opacity: 0;">
	  </div>
	  <div class="intro-back">
	  	<div class="intro-header">
	    	<h1>농순배</h1>
	    	<p>농수산물 순식간에 배송해드립니다</p>
	  	</div>
	  </div>
	</header>
	<jsp:include page="/WEB-INF/views/include/loading.jsp" />
	<table class="table-borderless main">
		<tbody id="contentContainer">
			<tr>
				<td class="title">
					<h5>최신공지 <jsp:include page="/WEB-INF/views/all/main/newNotice.jsp" /></h5>
					<br />
				</td>
			</tr>
			<tr>
				<td class="search">
					<jsp:include page="/WEB-INF/views/all/main/search.jsp" />
				</td>
			</tr>
			<tr>
				<td class="season">
					<jsp:include page="/WEB-INF/views/all/main/season.jsp" />
				</td>
			</tr>
			<tr>
				<td class="detail">
					<jsp:include page="/WEB-INF/views/all/main/seasonDetail.jsp" />
				</td>
			</tr>
		</tbody>
	</table>	
	
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>