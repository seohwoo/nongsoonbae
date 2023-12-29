<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<style  type="text/css">
	
</style>


</head>
<header class="p-3">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">	<!-- 메인 연결 -->
          <img alt="logo" src="/resources/img/logo.png"  width="60" height="52" />
        </a>
        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
          <input type="search" class="form-control" placeholder="검색" aria-label="Search">
        </form>

        <div class="text-end">
          <input type="button" class="btn btn-outline-light me-2" src="/member/form" value="로그인" />
          <input type="button" class="btn btn-warning" src="/member/regForm" value="회원가입" />
        </div>
      </div>
    </div>
  </header>
  <nav class="p-4 text-primary-emphasis bg-primary-subtle border border-primary-subtle rounded-3">
  	<div class="container">
	  	<div class="d-flex justify-content-evenly">
	  		<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
	          <li><a href="#" class="nav-link px-2 text-white">산지제철</a></li>
	          <li><a href="#" class="nav-link px-2 text-white">실시간 차트</a></li>
	          <li><a href="#" class="nav-link px-2 text-white">카테고리</a></li>
	          <li><a href="#" class="nav-link px-2 text-white">지역별 농수산물</a></li>
	        </ul>
	  	</div>
  	</div>
  </nav>