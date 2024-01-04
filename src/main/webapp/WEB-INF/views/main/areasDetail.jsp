<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>지역별</title>
	</head>
	<body>
	<h1>지역별 상품</h1>
	     
	         <c:if test="${cnt == 0 }" >
	         <h2>조회하신 상품이 없습니다. </h2>
	         </c:if>
	         <c:if test="${cnt >  0 }" >
	            <c:forEach var="dto" items="${list}">
	               <h1>${dto.productname}</h1>
	            </c:forEach>
	         </c:if>
	     
   </body>
   
 
   
</html>