<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

	<script>
		$(function() {
			$("#cate2").on("change",function(){
				$.ajax({
					url:"/product/productWriteForm3",
					data:{cate1:$("#cate1").val(), cate2:$("#cate2").val()},
					success:function(data){
						$("#c3").html(data);
					}
				});
			});
		});
		
		
		// 카테고리 선택 여부 확인 함수
		function cate2Check() {
		    // 선택한 카테고리 확인
		    var selectedCategory = $("#cate2").val();
		    // 만약 값이 "-------"이면 알림 표시
		    if (selectedCategory === "-------") {
		        alert("카테고리를 선택하세요.");
		        return false; // 폼 제출 방지
		    }
		}		
	</script>
	
	<body>
		<select id="cate2" name="cate2" onchange="cate2Check()">
			<option value="-------">-------</option>
			<c:forEach var="cate2" items="${cate2}">
				<option value="${cate2.cate2}">${cate2.catename}</option>
			</c:forEach>
		</select>
		<div id="c3"></div>
	</body>
