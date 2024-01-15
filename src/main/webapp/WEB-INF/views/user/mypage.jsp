<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
<link href="/resources/css/mypage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
<div class="container-fluid">
  <div class="row flex-nowrap">
    <div class="col-3 bd-sidebar">
      <ul class="nav">
        <li><a>Side 1</a></li>
        <li><a>Side 2</a></li>
        <li><a>Side 3</a></li>
        <li><a>Side 4</a></li>
        <li><a>Side 5</a></li>
        <li><a>Side 6</a></li>
        <li><a>Side 7</a></li>
        <li><a>Side 8</a></li>
      </ul>
      <br>
    </div>
    <main class="col-9 py-md-3 pl-md-5 bd-content" role="main">
      <h1>Main 1</h1>
      <h1>Main 2</h1>
      <h1>Main 3</h1>
      <h1>Main 4</h1>
      <h1>Main 5</h1>
      <h1>Main 6</h1>
      <h1>Main 7</h1>
      <h1>Main 8</h1>
    </main>
  </div>
</div>
<%@include file="/WEB-INF/views/include/footer.jsp"%>

</body>
</html>