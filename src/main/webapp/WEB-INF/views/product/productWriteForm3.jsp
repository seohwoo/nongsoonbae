<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<script>
		$(function() {
			$("#cate3").on("change",function(){
				$.ajax({
					url:"/product/productWriteForm",
					data:{cate1:$("#cate1").val(), cate2:$("#cate2").val(), cate3:$("#cate3").val()},
					success:function(data){
						$("#c4").html(data);
					}
				});
			});
		});
	</script>
	
	<body>
		<select id="cate3">
			<option value="-------">-------</option>
			<c:forEach var="cate3" items="${cate3}">
				<option value="${cate3.cate3}">${cate3.catename}</option>
			</c:forEach>
		</select>
		<div id="c4"></div>
	</body>
</html>