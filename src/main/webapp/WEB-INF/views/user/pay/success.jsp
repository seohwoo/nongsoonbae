<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>결제성공</title>
	</head>
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
		<h2>success</h2>
		<div class="form">
		${kakaoApprove.cid }<br>
		${kakaoApprove.sid }<br>
		${kakaoApprove.partner_order_id }<br>
		${kakaoApprove.partner_user_id }<br>
		${kakaoApprove.item_name }<br>
		${kakaoApprove.item_code }<br>
		${kakaoApprove.quantity }<br>
		${kakaoApprove.created_at }<br>
		${kakaoApprove.approved_at }<br>
		${amount.total}<br />
		${amount.tax_free}<br />
		</div>
		<a href="/product/productMyShop?username=${username}">상점페이지로가기>></a>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>