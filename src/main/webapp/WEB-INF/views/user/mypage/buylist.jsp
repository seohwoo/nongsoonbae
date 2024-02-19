<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<main class="col-9 py-md-5 pl-md-5 bd-content" role="main" style="margin-top: 30px; margin-left: 17%">
	<div class="container like">
      <h1>구매내역</h1><br />
		  <c:if test="${buystatus eq 0 }">
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
		  
		  <!-- 
		  function openReviewWindow() {
			var pdtoVal= $("#Pdto").val();
			if('-------' == pdtoVal){
				alert("상품 옵션을 선택해주세요.");
			}else{
			var optionnum = $("#selectedOptionNum").val();
			var productnum = ${productnum};
			var reviewWindow = window.open('/product/productReview?optionnum='+optionnum + '&productnum='+productnum, '_blank', 'width=400,height=300,resizable=yes');
			}
		}
		  
		   -->
		  
		  
		  <c:if test="${buystatus eq 1}">
		  <div>
			<table cellspacing="0" class="g-table-list product">
				<thead>
					<tr>
						
						<th class="g-table-list-col-title g-table-list-col-listing opt g-table-list-rwd">상품명</th>
						<th class="g-table-list-col-title g-table-list-col-desc required">상점</th>
						<th class="g-table-list-col-title g-table-list-col-money required">가격</th>
						<th class="g-table-list-col-title g-table-list-col-date">구매일</th>
						<th class="g-table-list-col-title g-table-list-col-date">리뷰작성</th>
					</tr>
				</thead>
				<c:forEach var="pay" items="${paylist}">
					<tbody>
						<tr>
							
							<td><a href="#">${pay.productname}</a><p class="g-table-list-col-small-copy">${pay.optionname}</p></td>
							<td class="g-table-list-rwd"><a href="#">${pay.shopname}</a></td>
							<td>${pay.realprice}원</td>
							<td class="g-table-list-date"><fmt:formatDate value="${pay.orderdate}" dateStyle="short" type="date"/></td>
							<td><button onclick="window.open('/product/productReview?optionnum=${pay.optionnum}&productnum=${pay.productnum}', '_blank', 'width=400,height=300,resizable=yes')">리뷰작성</button></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
		</c:if>
	</div>
 </main>
</html>