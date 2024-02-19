<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<script>
		$(function() {
			$("#cate3").on("change",function(){
				$.ajax({
					url:"/product/productWriteForm4",
					data:{cate1:$("#cate1").val(), cate2:$("#cate2").val(), cate3:$("#cate3").val()},
					success:function(data){
						$("#firstOption").html(data);
					}
				});
			});
		});
		
		// 카테고리 선택 여부 확인 함수
		function cate3Check() {
		    // 선택한 카테고리 확인
		    var selectedCategory = $("#cate3").val();
		    // 만약 값이 "-------"이면 알림 표시
		    if (selectedCategory === "-------") {
		        alert("카테고리를 선택하세요.");
		        return false; // 폼 제출 방지
		    }
		}		
	</script>


	<select id="cate3" name="cate3" onchange="cate3Check()">
		<option value="-------">-------</option>
		<c:forEach var="cate3" items="${cate3}">
			<option value="${cate3.cate3}">${cate3.catename}</option>
		</c:forEach>
	</select>
	<div id="c4"></div>