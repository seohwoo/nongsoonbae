<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>카테고리별</title>
	</head>
	<body>
	      <table>
	         <c:if test="${cnt == 0 }" >
	         <h1>조회하신 상품이 없습니다. </h1>
	         </c:if>
	         <c:if test="${cnt >  0 }" >
	            <c:forEach var="dto" items="${list}">
	               <h1>${dto.productname}</h1>
	            </c:forEach>
	         </c:if>
	      </table>
   </body>
</html>