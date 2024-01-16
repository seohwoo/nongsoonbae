<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
<link href="/resources/css/mypage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
<div class="container-fluid" id="wrapper">
  <div class="row flex-nowrap">
    <div class="col-2 bd-sidebar align-items">
      <ul class="nav nav-pills flex-column mb-auto" style="margin-top: 100px;">
	      <li class="nav-item">
	        <a href="/user/mypage" class="nav-link active" aria-current="page" id="home">  
	          내 정보
	        </a>
	      </li>
	      <li>
	        <a href="#" class="nav-link link-dark" id="like">
	          찜/구독
	        </a>
	      </li>
	      <li>
	        <a href="#" class="nav-link link-dark" id="store">
	          내 상점
	        </a>
	      </li>
	      <li>
	        <a href="#" class="nav-link link-dark" id="cart">       
	          장바구니
	        </a>
	      </li>
	      <li>
	        <a href="#" class="nav-link link-dark" id="buylist"> 
	          구매내역
	        </a>
	      </li>
    </ul>
      <br>
    </div>
    <jsp:include page="/WEB-INF/views/user/mypage/home.jsp"/>
  </div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>

</body>
</html>