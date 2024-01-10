<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>지역별 상품 찾기</title>
	</head>
	<body>
		<h2>검색결과</h2>
		<c:if test="${cnt == 0 }" >
	         <h1> 상품이 없거나 검색 결과가 없습니다. </h1>
	         </c:if>
	         <c:if test="${cnt >  0 }" >
	            <c:forEach var="dto" items="${list}">
	               <h1>${dto.productname}</h1>
	            </c:forEach>
	         </c:if>	
	</body>
</html>