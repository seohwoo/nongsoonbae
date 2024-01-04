<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>



<link href="/resources/css/login.css" rel="stylesheet" type="text/css">
<nav class="py-2 bg-light border-bottom">
    <div class="container d-flex flex-wrap">
      <ul class="nav me-auto">
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2">산지제철</a></li>
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2">실시간 차트</a></li>
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2">카테고리</a></li>
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2">전국 특산품</a></li>
      </ul>
      <ul class="nav">
	    <sec:authorize access="isAnonymous()">
			<!-- 로그인 안 한 익명일 경우 -->
			<li class="nav-item"><a href="/member/form" class="nav-link link-dark px-2">로그인</a></li>
	       	<li class="nav-item"><a href="/member/regForm" class="nav-link link-dark px-2">회원가입</a></li>
		</sec:authorize>		
		<sec:authorize access="isAuthenticated()">
			<!-- 로그인(인증된) 사용자인 경우 -->	
			<li class="nav-item"><a href="/mypage" class="nav-link link-dark px-2">마이페이지</a></li>
			<li class="nav-item"><a href="/member/logout" class="nav-link link-dark px-2">로그아웃</a></li>
		</sec:authorize>
	</ul>
    </div>
  </nav>
  <header class="py-3 mb-4 border-bottom">
    <div class="container d-flex flex-wrap justify-content-center">
      <a href="/" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto text-dark text-decoration-none">
        <img alt="nongsoonbae" src="/resources/img/logo.png" width="100%" height="40px">
      </a>
      <form class="col-12 col-lg-auto mb-3 mb-lg-0">
        <input type="search" class="form-control" placeholder="검색어 입력" aria-label="Search">
      </form>
    </div>
  </header>