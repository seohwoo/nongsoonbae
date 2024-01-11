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
			<input type="text" name="userSearch" id="userSearch" placeholder="검색어를 입력하세요😁😁😁"/>
			<input type="submit" id="searchButton" value="🔎">
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