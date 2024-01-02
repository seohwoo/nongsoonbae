<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<h1>${month}월</h1>
		<div style="display: flex;">
			<c:forEach var="dto" items="${categoryList}">
				<div style="margin-right: 10px; text-align: center;">
					<img src="${dto.img}" width="100px" height="100px" /> <br />
					<a href="/main/main?categoryNum=${categoryNum}&cate1=${dto.cate1}&cate2=${dto.cate2}&cate3=${dto.cate3}">
						<span>${dto.catename}</span>
					</a>
				</div>
			</c:forEach>
		</div>
		<br />
		<c:if test="${categoryNum>1}">
			<button onclick="window.location='/main/main?categoryNum=${categoryNum-1}'">⏪</button>
		</c:if>
		<c:if test="${categoryNum==1}">
			<button onclick="window.location='/main/main?categoryNum=${categoryNum-1}'">⏸</button>
		</c:if>
		<button onclick="window.location='/main/main?categoryNum=${categoryNum+1}'">⏩</button>
	</body>
</html>