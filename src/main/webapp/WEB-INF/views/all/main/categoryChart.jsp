<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>실시간 차트</title>
		<link rel="icon" href="/resources/img/logo.png">
	</head>
	<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>
	<header class="intro">
	  <div class="intro-slideshow">
	    <img src="https://images.unsplash.com/photo-1519897831810-a9a01aceccd1?q=80&w=1931&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" style="opacity: 1;">
	  </div>
	  <div class="intro-back">
	  	<div class="intro-header">
	    	<h1>실시간 차트</h1>
	  	</div>
	  </div>
	</header>
	<div style="display: flex; flex-direction: column;">
		<table class="chart table-borderless main">
			<tr>
				<td>
					<div style="display: flex; align-items: center; justify-content: center;">
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
					<div style="display: flex; justify-content: center;">
						<c:if test="${isCate3==0}">
							<h1>해당 카테고리의 소분류를 준비중입니다..</h1>
						</c:if>
						<c:if test="${isCate3>0}">
							<c:forEach var="cate" items="${cateList}" >
								<div>
									<form action="/nsb/chart" method="get">
										<input type="hidden" name="categoryNum" value="${categoryNum}">
										<input type="hidden" name="cate1" value="${cate.cate1}">
										<input type="hidden" name="cate2" value="${cate.cate2}">
										<input type="hidden" name="cate3" value="${cate.cate3}">
										<button class="btn" type="submit">
											<img src="${cate.img}" border="0" width="60" height="60" class="bd-placeholder-img rounded-circle">
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