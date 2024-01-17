<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div style="display: flex; flex-direction: column;">
		<table class="season table-borderless">
			<tr>
				<td>
					<h1 class="h1 mb-4">${month}월</h1></td>
				<c:forEach var="dto" items="${categoryList}">
					<td>
						<form action="/nsb/main" method="get">
							<input type="hidden" name="categoryNum" value="${categoryNum}"/>
							<input type="hidden" name="cate1" value="${dto.cate1}"/>
							<input type="hidden" name="cate2" value="${dto.cate2}"/>
							<input type="hidden" name="cate3" value="${dto.cate3}"/>
							<button class="btn" type="submit">
								<img src="${dto.img}" width="100px" height="100px" class="bd-placeholder-img rounded-circle" />
								<br />
								<span>${dto.catename}</span>						
							</button>
						</form>
					</td>
				</c:forEach>
				</tr>
			</table>
		<br><div></div>
			<table class="table-borderless">
				<tr>
					<td class="page">
					<c:if test="${categoryNum>1}">
						<form action="/nsb/main" method="get">
							<input type="hidden" name="categoryNum" value="${categoryNum-1}"/>
							<button class="bton" type="submit">⏪</button>
						</form>
					</c:if>
					<c:if test="${categoryNum==1 || categoryNum>=maxCategoryNum}">
						<button class="bton" onclick="window.location='#'">⏸</button>
					</c:if>
					<c:if test="${categoryNum<maxCategoryNum}">
						<form action="/nsb/main" method="get">
							<input type="hidden" name="categoryNum" value="${categoryNum+1}"/>
							<button class="bton" type="submit">⏩</button>
						</form>
					</c:if>
					</td>
				</tr>		
		</table>
		</div>			
		
		<br />		
	</body>
</html>