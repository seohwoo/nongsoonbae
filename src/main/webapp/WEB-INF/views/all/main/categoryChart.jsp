<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>실시간 차트</title>
	</head>
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<div style="display: flex; flex-direction: column;">
		<table class="chart table-borderless main">
			<tr>
				<td>
					<h1 class="title">실시간 차트</h1><br />
				</td>
			</tr>
			<tr>
				<td>
					<div style="display: flex;">
						<c:forEach var="dto" items="${catemenu}" >
						<div style="margin-right: 10px;">
						<form action="/nsb/chart" method="get">
							<input type="hidden" name="cate1" value="${dto.cate1}">
							<input type="hidden" name="cate2" value="${dto.cate2+1}">
							<input type="hidden" name="cate3" value="${dto.cate3+1}">
							<button class="btn" type="submit">
								<img src="${dto.img}" border="0" width="100" height="100">
							<br />
							<span>${dto.catename}</span>
							</button>
						</form>
						</div>
						</c:forEach>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div style="display: flex;">
						<c:if test="${isCate3==0}">
							<h1>해당 카테고리의 소분류를 준비중입니다..</h1>
						</c:if>
						<c:if test="${isCate3>0}">
							<c:forEach var="cate" items="${cateList}" >
								<div style="margin-right: 10px;">
									<form action="/nsb/chart" method="get">
										<input type="hidden" name="categoryNum" value="${categoryNum}">
										<input type="hidden" name="cate1" value="${cate.cate1}">
										<input type="hidden" name="cate2" value="${cate.cate2}">
										<input type="hidden" name="cate3" value="${cate.cate3}">
										<button class="btn" type="submit">
											<img src="${cate.img}" border="0" width="100" height="100" class="bd-placeholder-img rounded-circle">
											<br />
											<span>${cate.catename}</span>
										</button>
									</form>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</td>
			</tr>	
			</table>
			<table class="chartsub table-borderless main">
			<tr>
				<td class="page">
					<c:if test="${cate1!=null}">
						<c:if test="${categoryNum>1}">
							<form action="/nsb/chart" method="get">
								<input type="hidden" name="categoryNum" value="${categoryNum-1}">
								<input type="hidden" name="cate1" value="${cate1}">
								<input type="hidden" name="cate2" value="${prevCate.cate2}">
								<input type="hidden" name="cate3" value="${prevCate.cate3}">
								<button class="btn btn-success" type="submit">«</button>
							</form>
						</c:if>
						<c:if test="${categoryNum==1 || categoryNum>=maxCategoryNum}">
							<button class="btn btn-success" onclick="window.location='#'">＝</button>
						</c:if>
						<c:if test="${categoryNum<maxCategoryNum}">
							<form action="/nsb/chart" method="get">
								<input type="hidden" name="categoryNum" value="${categoryNum+1}">
								<input type="hidden" name="cate1" value="${cate1}">
								<input type="hidden" name="cate2" value="${nextCate.cate2}">
								<input type="hidden" name="cate3" value="${nextCate.cate3}">
								<button class="btn btn-success" type="submit">»</button>
							</form>
						</c:if>
					</c:if>
				</td>
			</tr>
			</table>
			<table class="chartsub table-borderless main">
			<tr>
				<td>
					<c:if test="${isChart==0 }">
						<h1>차트를 준비중입니다...</h1>
					</c:if>
					<c:if test="${isChart>0 }">
						<jsp:include page="/WEB-INF/views/all/main/chart.jsp" />
					</c:if>
				</td>
			</tr>
		</table>
		</div>
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>