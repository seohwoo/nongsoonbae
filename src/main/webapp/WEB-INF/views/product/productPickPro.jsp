<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<script>
	if(${pickCount} === 0) {
		alert("상품 찜하기가 완료되었습니다.");
		window.location='/product/productInfo?productnum=' + "${productnum}" + '&follow=' +"${follow}";
	}else {
		alert("상품 찜하기가 취소되었습니다.");
		window.location='/product/productInfo?productnum=' + "${productnum}" + '&follow=' +"${follow}";
	}
	
</script>