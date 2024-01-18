<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<main class="col-9 py-md-5 pl-md-5 bd-content" role="main" style="margin-top: 30px; margin-left: 17%">
	<div class="container like">
      <h1>찜/구독 리스트</h1><br />
	    <div class='tabbed skin-nephritis round' id='skinable'>
			<ul>
				<li class="subscription">내가 구독한 농부</li>
				<li class="like active">내가 찜한 상품</li>
			</ul>
		</div>
		<div class="content like">
			<div class="g-page">
				<div class="g-table-body">
					<table cellspacing="0" class="g-table-list product">
						 <thead>
							<tr>
								<th class="g-table-list-col-edit"></th>
								<th class="g-table-list-col-title g-table-list-col-sku required ">이미지</th>
								<th class="g-table-list-col-title g-table-list-col-listing opt g-table-list-rwd">상품명</th>
								<th class="g-table-list-col-title g-table-list-col-desc required">가게</th>
								<th class="g-table-list-col-title g-table-list-col-money required">가격</th>
								<th class="g-table-list-col-title g-table-list-col-date required">등록일</th>
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
			</div>
		</div>
		<div class="content subscription" style="display: none;">
			<div class="g-page">
				<div class="g-table-body">
					<table cellspacing="0" class="g-table-list product">
						 <thead>
							<tr>
								<th class="g-table-list-col-edit"></th>
								<th class="g-table-list-col-title g-table-list-col-sku required ">이미지</th>
								<th class="g-table-list-col-title g-table-list-col-listing opt g-table-list-rwd">상품명</th>
								<th class="g-table-list-col-title g-table-list-col-desc required">가게</th>
								<th class="g-table-list-col-title g-table-list-col-money required">가격</th>
								<th class="g-table-list-col-title g-table-list-col-date required">등록일</th>
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
			</div>
		</div>
    </div>
    </main>
    <script>
   		document.addEventListener("DOMContentLoaded", function() {
    	  var tabs = document.querySelectorAll('.tabbed li');
    	  var contentContainers = document.querySelectorAll('.content .like > div');

    	  for (var i = 0, len = tabs.length; i < len; i++) {
    	    tabs[i].addEventListener("click", function() {
    	      if (this.classList.contains('active'))
    	        return;

    	      var parent = this.parentNode, innerTabs = parent.querySelectorAll('li');

    	      for (var index = 0, iLen = innerTabs.length; index < iLen; index++) {
    	        innerTabs[index].classList.remove('active');
    	      }

    	      this.classList.add('active');
    	     
    	      var tabIndex = Array.from(parent.children).indexOf(this);

    	      for (var j = 0, jLen = contentContainers.length; j < jLen; j++) {
    	        contentContainers[j].style.display = 'none';
    	      }
    	      if (contentContainers[tabIndex]) {
    	        contentContainers[tabIndex].style.display = 'block';
    	      }
    	    });
    	  }
    	});
    </script>
</body>
</html>