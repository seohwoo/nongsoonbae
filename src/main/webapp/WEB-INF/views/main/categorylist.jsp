<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div style="display: flex;">
			<c:forEach var="cate" items="${menu}" >
				<div style="margin-right: 10px;">
					<a href="/main/menu?cate1=${cate.cate1}&cate2=${cate.cate2}&cate3=${cate.cate3}">${cate.catename}</a> </br>
				</div>
			</c:forEach>
		</div>
	</body>	
</html>















