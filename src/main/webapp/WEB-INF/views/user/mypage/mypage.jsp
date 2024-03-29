<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
<link rel="icon" href="/resources/img/logo.png">
<link href="/resources/css/mypage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
<div class="container-fluid" id="wrapper">
  <div class="row flex-nowrap">
    <div class="col-2 bd-sidebar align-items" style="margin-top: -6%;">
      <ul class="nav nav-pills flex-column mb-auto" >
	      <li class="nav-item">
	        <a href="/user/mypage" class="nav-link active navitem" aria-current="page" id="home">  
	          내 정보
	        </a>
	      </li>
	      <li>
	        <a href="/user/like" class="nav-link link-dark navitem" id="like">
	          찜/구독
	        </a>
	      </li>
	      <li>
	        <a href="/user/cart" class="nav-link link-dark navitem" id="cart">       
	          장바구니
	        </a>
	      </li>
	      <li>
	        <a href="/user/buyList" class="nav-link link-dark navitem" id="buylist"> 
	          구매내역
	        </a>
	      </li>
	      <li>
	        <a href="#" class="nav-link link-dark navitem" id="chatlist"> 
	          채팅리스트
	        </a>
	      </li>
    </ul>
      <br>
    </div>
    <div id="content">
   		<jsp:include page="/WEB-INF/views/user/mypage/home.jsp"/>
    </div>
  </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            // Handle sidebar menu clicks
            $('.navitem').on('click', function (e) {
                e.preventDefault();
                $('.navitem').removeClass('active');
                $('.navitem').addClass('link-dark');
                $(this).removeClass('link-dark');
                $(this).addClass('active');

                var contentId = $(this).attr('id');

                // Check if the clicked link is "chatlist"
                if (contentId === 'chatlist') {
                    // Open a popup with the desired URL
                    window.open('/chat/list', '_blank', 'width=600,height=1200');
                } else {
                    // Load the content into the main page
                    $('#content').load(contentId);
                }

                sessionStorage.setItem('lastClickedLink', contentId);
            });

            // 새로고침해도 페이지를 기억함
            var lastClickedLink = sessionStorage.getItem('lastClickedLink');
            var refreshing = sessionStorage.getItem('refreshing');
            
            if (lastClickedLink) {
                $('.navitem').removeClass('active');
                $('.navitem').addClass('link-dark');
                $('#' + lastClickedLink).removeClass('link-dark');
                $('#' + lastClickedLink).addClass('active');
                $('#content').load(lastClickedLink);
            }
            // 페이지를 벗어나면 저장한 링크 삭제
            if (refreshing) {
			    // Clear the refreshing flag
			    sessionStorage.removeItem('refreshing');
			} else {
			    // Clear sessionStorage when leaving mypage
			    $(window).on('beforeunload', function() {
			        sessionStorage.removeItem('lastClickedLink');
			    });
			}
            $(document).ready(function() {
                // Check if this is a refresh
                if (performance.navigation.type === performance.navigation.TYPE_RELOAD) {
                    // Set refreshing flag
                    sessionStorage.setItem('refreshing', true);
                }
            });
        });
    </script>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>