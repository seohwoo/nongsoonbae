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
	    <img src="https://www.dropbox.com/s/h8rds5ozk0u3s2f/coder.jpg?raw=1" style="opacity: 0;">
	    <img src="https://www.dropbox.com/s/lf29ifrd354ngyv/coffee.jpg?raw=1" style="opacity: 0;">
	    <img src="https://www.dropbox.com/s/lxnf1mxej90qoae/library.jpg?raw=1" style="opacity: 0;">
	    <img src="https://www.dropbox.com/s/15gdoyzoai94j6j/path.jpg?raw=1" style="opacity: 1;">
	    <img src="https://www.dropbox.com/s/pem8kaorr488apn/universe.jpg?raw=1" style="opacity: 0;">
	  </div>
	  <div class="intro-header">
	    <h1>🍒🍓🍅 농순배 🥑🥒🥬</h1>
	    <p>농산물 순식간에 배송해드립니다</p>
	  </div>
	</header>
	
	
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