<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	alert("장바구니에 상품이 담겼습니다..");
	window.location='/product/productInfo?productnum=' + "${productnum}" + '&follow=' +"${follow}";
</script>