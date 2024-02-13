<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="/resources/css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<script>
	$('#header').prepend('<div id="menu-icon"><span class="first"></span><span class="second"></span><span class="third"></span></div>');
	
	$("#menu-icon").on("click", function(){
	$("nav").slideToggle();
	$(this).toggleClass("active");
	});
</script>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<div id="header">
  <div class="logo">
    <a href="#">관리자 도구</a>
  </div>  
  <nav>
    <ul>
      <li>
        <a href="">메인으로</a>
      </li>
      <li class="dropdown">
        <a href="">회원관리</a>
          <ul>
            <li><a href="#">전체회원</a></li>
            <li><a href="#">정지회원</a></li>
            <li><a href="#">카테고리 추가</a></li>
            <li><a href="#">어쩌고</a></li>
          </ul>        
      </li>
      <li class="dropdown">
        <a href="">공지관리</a>
          <ul>
            <li><a href="#">공지작성</a></li>
            <li><a href="#">공지목록</a></li>
            <li><a href="#">Contact Us</a></li>
            <li><a href="#">Contact Us</a></li>
          </ul>        
      </li>
      <li class="dropdown">
        <a href="">광고관리</a>
          <ul>
            <li><a href="#">광고신청 확인</a></li>
            <li><a href="#">광고목록</a></li>
            <li><a href="#">Contact Us</a></li>
            <li><a href="#">Contact Us</a></li>
          </ul>        
      </li>
      <li class="dropdown">
        <a href="">상점관리</a>
          <ul>
            <li><a href="#">상점상태</a></li>
            <li><a href="#">상점목록</a></li>
            <li><a href="#">Contact Us</a></li>
            <li><a href="#">Contact Us</a></li>
          </ul>        
      </li>
    </ul>
  </nav>
</div>
<div class="content">
	<%@include file="/WEB-INF/views/admin/usercheck/usercheck.jsp"%>
</div>
	
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>