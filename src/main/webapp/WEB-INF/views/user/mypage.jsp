<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
</head>
<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<div class="d-flex flex-column flex-shrink-0 p-3 bg-body-tertiary" style="width: 260px; align-items : center;">
	    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
	      <svg class="bi pe-none me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
	      <span class="fs-4">마이페이지</span>
	    </a>
	    <hr>
	    <ul class="nav nav-pills flex-column mb-auto" style="width : 180px;">
	      <li class="nav-item" style="align-items : center;">
	        <a href="#" class="nav-link active" aria-current="page">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
	          메인페이지
	        </a>
	      </li>
	      <li style="align-items : center;">
	        <a href="#" class="nav-link link-body-emphasis">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
	          농부 구독 / 찜
	        </a>
	      </li>
	      <li style="align-items : center;">
	        <a href="#" class="nav-link link-body-emphasis">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"></use></svg>
	          장바구니
	        </a>
	      </li>
	      <li style="align-items : center;">
	        <a href="#" class="nav-link link-body-emphasis">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
	          내 상점
	        </a>
	      </li>
	      <li style="align-items : center;">
	        <a href="#" class="nav-link link-body-emphasis">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
	          Customers
	        </a>
	      </li>
	    </ul>
	    <hr>
	    <div class="dropdown">
	      <a href="#" class="d-flex align-items-center link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
	        <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
	        <strong>${username}</strong>
	      </a>
	      <ul class="dropdown-menu text-small shadow">
	        <li><a class="dropdown-item" href="#">New project...</a></li>
	        <li><a class="dropdown-item" href="#">Settings</a></li>
	        <li><a class="dropdown-item" href="#">Profile</a></li>
	        <li><hr class="dropdown-divider"></li>
	        <li><a class="dropdown-item" href="#">Sign out</a></li>
	      </ul>
	    </div>
	  </div>
	  
	  <div class="container" style="width: auto;">
	  	
	  	
	  	
	  
	  
	  </div>

</body>
</html>