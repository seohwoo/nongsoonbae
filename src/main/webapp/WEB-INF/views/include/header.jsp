<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<link href="/resources/css/login.css" rel="stylesheet" type="text/css">
<link href="/resources/css/main.css" rel="stylesheet" type="text/css">
    <!-- 헤더 -->
    <header>
        <!-- 로고 -->
        <section class="logo" >
            <h2><a href="nsb/main" class="down">농순배</a></h2>
        </section>
        <!-- gnb -->
        <section class="menu_wrap">
            <div class="menu">
                <ul>
	                <li class="nav-item"><a href="/nsb/main" class="nav-link link-dark px-2">산지제철</a></li>
			        <li class="nav-item"><a href="/nsb/chart" class="nav-link link-dark px-2">실시간 차트</a></li>
			        <li class="nav-item"><a href="/nsb/menu" class="nav-link link-dark px-2">카테고리</a></li>
			        <li class="nav-item"><a href="/nsb/area" class="nav-link link-dark px-2">전국 특산품</a></li>
                </ul>
            </div>
        </section>
        <!-- log / sign in -->
        <section class="log_wrap">
        	<sec:authorize access="isAnonymous()">
				<!-- 로그인 안 한 익명일 경우 -->
				<span class="log-links"><a href="/member/form" class="nav-link link-dark px-2">로그인</a>
				<a href="/member/regForm" class="nav-link link-dark px-2">회원가입</a></span>
			</sec:authorize>		
          	<sec:authorize access="isAuthenticated()">
				<!-- 로그인(인증된) 사용자인 경우 -->	
				<span class="log-links"><a href="/product/productMain" class="nav-link link-dark px-2">상품등록</a>
				<a href="/user/mypage" class="nav-link link-dark px-2">마이페이지</a>
				<a href="/member/logout" class="nav-link link-dark px-2">로그아웃</a></span>
			</sec:authorize>
        </section>
    </header>