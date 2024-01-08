<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>SummerNoteExample</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="/resources/summernote/summernote-lite.js"></script>
  <script src="/resources/summernote/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="/resources/summernote/summernote-lite.css">
  <!--  -->
  
</head>
<body>
<div class="container">
	<form action="editorPro" enctype="multipart/form-data">
  		<textarea class="summernote" name="editordata"></textarea>  
  		<input type="submit" name="doit" value="저장" /> 
  	</form> 
</div>
<script>
$('.summernote').summernote({
	  height: 450,
	  lang: "ko-KR"
	});
</script>
</body>
</html>