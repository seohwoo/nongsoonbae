<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div style="display: flex;">
		<table class="table table-hover">
			<thead>
			<tr>
				<td>
					<h1 class="h1 mb-4">${month}월</h1></td>
				<c:forEach var="dto" items="${categoryList}">
					<td>
						<img src="${dto.img}" width="100px" height="100px" class="bd-placeholder-img rounded-circle" />
						<br />
						<a href="/main/main?categoryNum=${categoryNum}&cate1=${dto.cate1}&cate2=${dto.cate2}&cate3=${dto.cate3}">
							<span>${dto.catename}</span>
						</a>
					</td>
				</c:forEach>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
					<c:if test="${categoryNum>1}">
						<button onclick="window.location='/main/main?categoryNum=${categoryNum-1}'">⏪</button>
					</c:if>
					<c:if test="${categoryNum==1}">
						<button onclick="window.location='/main/main?categoryNum=${categoryNum-1}'">⏸</button>
					</c:if>
					<button onclick="window.location='/main/main?categoryNum=${categoryNum+1}'">⏩</button>
					</td>
				</tr>
			</tbody>
		</table>
			
		</div>
		<br />
		
	</body>
</html>