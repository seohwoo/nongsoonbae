<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

	<script>
		$(function() {
			$("#cate2").on("change",function(){
				$.ajax({
					url:"/membership/productWriteForm3",
					data:{cate1:$("#cate1").val(), cate2:$("#cate2").val()},
					success:function(data){
						$("#c3").html(data);
					}
				});
			});
		});
	</script>
	
	<body>
		<select id="cate2" name="cate2">
			<option value="-------">-------</option>
			<c:forEach var="cate2" items="${cate2}">
				<option value="${cate2.cate2}">${cate2.catename}</option>
			</c:forEach>
		</select>
		<div id="c3"></div>
	</body>
