<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
	<main class="col-9 py-md-5 pl-md-5 bd-content" role="main" style="margin-top: 30px; margin-left: 17%">
	<div class="container like">
      <h1>찜/구독 리스트</h1><br />
	    <nav>
		  <div class="nav nav-tabs" id="nav-tab" role="tablist">
		    <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">내가 찜한 상품</button>
		    <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab" data-bs-target="#nav-profile" type="button" role="tab" aria-controls="nav-profile" aria-selected="false">구독한 상점</button>
		  </div>
		</nav>
		<div class="tab-content" id="nav-tabContent">
		  <c:if test="${status==0 }">
		  	<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
			<table cellspacing="0" class="g-table-list product">
				<thead>
					<tr>
						<th>찜한 상품이 없습니다.</th>
					</tr>
				</thead>
			</table>
			</div>
		  </c:if>
		  <c:if test="${status==1}">
		  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
			<table cellspacing="0" class="g-table-list product">
				<thead>
					<tr>
						<th class="g-table-list-col-edit"></th>
						<th class="g-table-list-col-title g-table-list-col-sku required ">이미지</th>
						<th class="g-table-list-col-title g-table-list-col-listing opt g-table-list-rwd">상품명</th>
						<th class="g-table-list-col-title g-table-list-col-desc required">가게</th>
						<th class="g-table-list-col-title g-table-list-col-money required">가격</th>
					</tr>
				</thead>
				<c:forEach var="like" items="${likeList}">
				<tbody>
					<tr>
						<td><a href="#"><i class="fa fa-fw fa-pencil"></i></a></td>
						<td><img src="/resources/img/sample.jpg" style="width: 50px; height: 50px;"></td>
						<td><a href="#">${like.optionname}</a></td>
						<td class="g-table-list-rwd"><a href="#">${like.shopname}</a></td>
						<td>${like.totalprice}원</td>
					</tr>
				</tbody>
				</c:forEach>
			</table>
			<div class="g-table-list-pagination">
				<div class="g-table-list-pagination-col">
					<span class="g-body-copy">Page</span>
					<form><input type="text" class="g-table-list-pagination-current" value="${likeNum}"></form>
					<span class="g-body-copy">of ${likeMaxNum}</span>
				</div>
				<div class="g-table-list-pagination-col">
					<a href="#" class="g-actions-button g-actions-button-pager"><i class="fa fa-fw fa-caret-left right-4"></i>Prev</a>
					<a href="#" class="g-actions-button g-actions-button-pager g-table-list-pager">Next<i class="fa fa-fw fa-caret-right left-4"></i></a>
				</div>
			</div>
		</div>
		</c:if>
		<div class="tab-content" id="nav-tabContent">
		  <c:if test="${status==0 }">
		  	<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
			<table cellspacing="0" class="g-table-list product">
				<thead>
					<tr>
						<th>구독한 농부가 없습니다.</th>
					</tr>
				</thead>
			</table>
			</div>
		  </c:if>
		<div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
			<table cellspacing="0" class="g-table-list product">
				<thead>
					<tr>
						<th class="g-table-list-col-edit"></th>
						<th class="g-table-list-col-title g-table-list-col-sku required ">이미지</th>
						<th class="g-table-list-col-title g-table-list-col-listing opt g-table-list-rwd">카테고리</th>
						<th class="g-table-list-col-title g-table-list-col-desc required">가게</th>
						<th class="g-table-list-col-title g-table-list-col-money required">위치</th>
					</tr>
				</thead>
				<c:forEach var="farmer" items="${farmerList}">
				<tbody>
					<tr>
						<td><a href="#"><i class="fa fa-fw fa-pencil"></i></a></td>
						<td><img src="/resources/img/farmer.jpg" style="width: 50px; height: 50px;"></td>
						<td><a href="#">${farmer.shopname}</a></td>
						<td class="g-table-list-rwd"><a href="#">${farmer.shopcontent}</a></td>
						<td>${farmer.address}</td>
						
					</tr>
				</tbody>
				</c:forEach>
			</table>
			<div class="g-table-list-pagination">
				<div class="g-table-list-pagination-col">
					<span class="g-body-copy">Page</span>
						<form><input type="text" class="g-table-list-pagination-current" value="${farmerNum}"></form>
					<span class="g-body-copy">of ${farmerMaxNum}</span>
				</div>
				<div class="g-table-list-pagination-col">
					<a href="#" class="g-actions-button g-actions-button-pager"><i class="fa fa-fw fa-caret-left right-4"></i>Prev</a>
					<a href="#" class="g-actions-button g-actions-button-pager g-table-list-pager">Next<i class="fa fa-fw fa-caret-right left-4"></i></a>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
 </main>
</body>
</html>