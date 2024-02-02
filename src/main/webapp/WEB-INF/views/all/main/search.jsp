<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script type="text/javascript" src="/resources/js/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<form action="/nsb/result" method="post">
			<div style="display: flex; margin-bottom: 10px;">
				<input type="text" name="userSearch" id="userSearch" class="form-control" placeholder="검색어를 입력하세요😁😁😁" style="width: 500px;"/>
				<input type="submit" id="searchButton" value="🔎" style="border-radius: var(--bs-border-radius);">
			</div>
		</form>
	</body>
	<script>
	  $(document).ready(function() {
	    $("#searchButton").click(function() {
	      var userInput = $("#userSearch").val();
	
	      if (userInput === "") {
	        alert("검색어를 입력하세요😠😠😠");
	        return false;
	      }
	    });
	  });
	</script>
</html>