<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
			<table cellspacing="0" class="g-table-list product">
				<thead>
					<tr>
						<th class="g-table-list-col-edit"></th>
						<th class="g-table-list-col-title g-table-list-col-sku required ">이미지</th>
						<th class="g-table-list-col-title g-table-list-col-listing opt g-table-list-rwd">상품명</th>
						<th class="g-table-list-col-title g-table-list-col-desc required">가게</th>
						<th class="g-table-list-col-title g-table-list-col-money required">가격</th>
						<th class="g-table-list-col-title g-table-list-col-date required">찜한 날짜</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#"><i class="fa fa-fw fa-pencil"></i></a></td>
						<td><img src="/resources/img/sample.jpg" style="width: 50px; height: 50px;"></td>
						<td><a href="#">[설날 세일]사과 청송 늘봄 햇부사 흠과 가정용 1.5kg 3Kg 5Kg [원산지:국산(경상북도 청송군)]</a><br><div class="g-table-list-col-small-copy">1kg</div></td>
						<td class="g-table-list-rwd"><a href="#">유미네 사과농장</a></td>
						<td>1000원</td>
						<td><div class="g-table-list-col-small-copy">2024/01/18</div></td>
					</tr>
				</tbody>
			</table>
			<div class="g-table-list-pagination">
				<div class="g-table-list-pagination-col">
					<span class="g-body-copy">Page</span>
					<form><input type="text" class="g-table-list-pagination-current" placeholder="3"></form>
					<span class="g-body-copy">of 32</span>
				</div>
				<div class="g-table-list-pagination-col">
					<a href="#" class="g-actions-button g-actions-button-pager"><i class="fa fa-fw fa-caret-left right-4"></i>Prev</a>
					<a href="#" class="g-actions-button g-actions-button-pager g-table-list-pager">Next<i class="fa fa-fw fa-caret-right left-4"></i></a>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
			<table cellspacing="0" class="g-table-list product">
				<thead>
					<tr>
						<th class="g-table-list-col-edit"></th>
						<th class="g-table-list-col-title g-table-list-col-sku required ">이미지</th>
						<th class="g-table-list-col-title g-table-list-col-listing opt g-table-list-rwd">카테고리</th>
						<th class="g-table-list-col-title g-table-list-col-desc required">가게</th>
						<th class="g-table-list-col-title g-table-list-col-money required">위치</th>
						<th class="g-table-list-col-title g-table-list-col-date required">구독일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#"><i class="fa fa-fw fa-pencil"></i></a></td>
						<td><img src="/resources/img/farmer.jpg" style="width: 50px; height: 50px;"></td>
						<td><a href="#">과일 > 사과/멜론/아보카도 > 사과</a></td>
						<td class="g-table-list-rwd"><a href="#">유미네 사과농장</a></td>
						<td>충청남도 당진</td>
						<td><div class="g-table-list-col-small-copy">2024/01/18</div></td>
					</tr>
				</tbody>
			</table>
			<div class="g-table-list-pagination">
				<div class="g-table-list-pagination-col">
					<span class="g-body-copy">Page</span>
						<form><input type="text" class="g-table-list-pagination-current" placeholder="1"></form>
					<span class="g-body-copy">of 5</span>
				</div>
				<div class="g-table-list-pagination-col">
					<a href="#" class="g-actions-button g-actions-button-pager"><i class="fa fa-fw fa-caret-left right-4"></i>Prev</a>
					<a href="#" class="g-actions-button g-actions-button-pager g-table-list-pager">Next<i class="fa fa-fw fa-caret-right left-4"></i></a>
				</div>
			</div>
		</div>
	</div>
</div>
 </main>
</body>
</html>