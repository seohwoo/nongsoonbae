<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<main class="col-9 py-md-5 pl-md-5 bd-content" role="main" style="margin-top: 30px; margin-left: 17%">
	<div class="container like">
      <h1>구매내역</h1><br />
		  <c:if test="${likestatus==0 }">
		  	<div>
			<table cellspacing="0" class="g-table-list product">
				<thead>
					<tr>
						<th>아직 구매한 내역이 없습니다.</th>
					</tr>
				</thead>
			</table>
			</div>
		  </c:if>
		  
		  <c:if test="${likestatus != 0}">
		  <div>
			<table cellspacing="0" class="g-table-list product">
				<thead>
					<tr>
						<th class="g-table-list-col-title g-table-list-col-sku required ">이미지</th>
						<th class="g-table-list-col-title g-table-list-col-listing opt g-table-list-rwd">상품명</th>
						<th class="g-table-list-col-title g-table-list-col-desc required">판매자</th>
						<th class="g-table-list-col-title g-table-list-col-money required">가격</th>
						<th class="g-table-list-col-delete"></th>
					</tr>
				</thead>
				<c:forEach var="like" items="${likeList}">
					<tbody>
						<tr>
							<td><img src="/resources/img/${like.filename}" style="width: 50px; height: 50px;"></td>
							<td><a href="#">${like.optionname}</a></td>
							<td class="g-table-list-rwd"><a href="#">${like.username}</a></td>
							<td>${like.price}원</td>
							<td><button type="button" class="delete-like" data-productnum="${like.productnum}">🗑</button></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
		</c:if>
	</div>
 </main>
</html>