<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>정보</title>
	</head>
	<body>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<c:if test="${!isUser}">
			<script type="text/javascript">
				alert("상점주인만 확인할 수 있습니다..");
				history.go(-1);
			</script>
		</c:if>
		<img src="/resources/file/profile/${shopDTO.image}" width="100px" height="100px">
		<h1>${shopDTO.name}</h1>
		<span><fmt:formatDate value="${shopDTO.regdate}" pattern="yyyy년 MM월 dd일" /></span>
		<hr />
		<h1>전체판매량 : ${shopDTO.total_payment}</h1>
		<h1>오늘판매량 : ${todayPrice}</h1>
		<h1>상품등록개수 : ${shopDTO.product_count}</h1>
		<h1>팔로워 : ${shopDTO.followers}</h1>
		<hr />
		<jsp:include page="/WEB-INF/views/user/myshop/shopchart.jsp" />
		<br />
		<br />
		<br />
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>