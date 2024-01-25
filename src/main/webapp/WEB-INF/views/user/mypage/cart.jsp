<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script>
	var check = false;
	
	function changeVal(el) {
	  var qt = parseFloat(el.parent().children(".qt").html());
	  var price = parseFloat(el.parent().children(".price").html());
	  var eq = Math.round(price * qt * 100) / 100;
	  
	  el.parent().children(".full-price").html( eq + "원" );
	  
	  changeTotal();			
	}
	
	function changeTotal() {
	  
	  var price = 0;
	  
	  $(".full-price").each(function(index){
	    price += parseFloat($(".full-price").eq(index).html());
	  });
	  
	  price = Math.round(price * 100) / 100;
	 
	  var shipping = parseFloat($(".shipping span").html());
	  var fullPrice = Math.round((price + shipping) *100) / 100;
	  
	  if(price == 0) {
	    fullPrice = 0;
	  }
	  
	  $(".subtotal span").html(price);
	 
	  $(".total span").html(fullPrice);
	}
	
	$(document).ready(function(){
	  
	  $(".remove").click(function(){
	    var el = $(this);
	    el.parent().parent().addClass("removed");
	    window.setTimeout(
	      function(){
	        el.parent().parent().slideUp('fast', function() { 
	          el.parent().parent().remove(); 
	          if($(".product").length == 0) {
	            if(check) {
	              $("#cart").html("<h1>장바구니에 담은 상품이 없습니다.</h1>");
	            }
	          }
	          changeTotal(); 
	        });
	      }, 200);
	  });
	  
	  $(".qt-plus").click(function(){
	    $(this).parent().children(".qt").html(parseInt($(this).parent().children(".qt").html()) + 1);
	    
	    $(this).parent().children(".full-price").addClass("added");
	    
	    var el = $(this);
	    window.setTimeout(function(){el.parent().children(".full-price").removeClass("added"); changeVal(el);}, 150);
	  });
	  
	  $(".qt-minus").click(function(){
	    
	    child = $(this).parent().children(".qt");
	    
	    if(parseInt(child.html()) > 1) {
	      child.html(parseInt(child.html()) - 1);
	    }
	    
	    $(this).parent().children(".full-price").addClass("minused");
	    
	    var el = $(this);
	    window.setTimeout(function(){el.parent().children(".full-price").removeClass("minused"); changeVal(el);}, 150);
	  });
	  
	  window.setTimeout(function(){$(".is-open").removeClass("is-open")}, 1200);
	  
	  $(".btn").click(function(){
	    check = true;
	    $(".remove").click();
	  });
	});
</script>
<main class="col-9 py-md-5 pl-md-5 bd-content" role="main" style="margin-bottom:5%; margin-left: 17%;  overflow: scroll;ss">
	<div class="container like">
      <h1>장바구니</h1><br />
	 
		  <div class="cart">
		  	<div class="container-cart">
				<section id="cart"> 
					<article class="product">
						<div class="cart-image">
							<a class="remove">
								<img src="http://www.astudio.si/preview/blockedwp/wp-content/uploads/2012/08/1.jpg" alt="">
								<h3>Remove product</h3>
							</a>
						</div>
						<div class="content">
							<h1>상품명</h1>
							상품 설명이나 옵션 적으면 될듯
						</div>
						<div class="content footer-content">
							<span class="qt-minus">-</span>
							<span class="qt">1</span>
							<span class="qt-plus">+</span>
		
							<h2 class="full-price">
								1000원
							</h2>
		
							<h2 class="price">
								1000원
							</h2>
						</div>
					</article>
		
					<article class="product">
						<div class="cart-image">
							<a class="remove">
								<img src="http://www.astudio.si/preview/blockedwp/wp-content/uploads/2012/08/3.jpg" alt="">
		
								<h3>Remove product</h3>
							</a>
						</div>
		
						<div class="content">
		
							<h1>Lorem ipsum dolor</h1>
		
							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Soluta, numquam quis perspiciatis ea ad omnis provident laborum dolore in atque.
		
						</div>
		
						<div class="content footer-content">
							
							<span class="qt-minus">-</span>
							<span class="qt">1</span>
							<span class="qt-plus">+</span>
		
							<h2 class="full-price">
								5000원
							</h2>
							<h2 class="price">
								5000원
							</h2>
						</div>
					</article>
		
					<article class="product">
						<div class="cart-image">
							<a class="remove">
								<img src="http://www.astudio.si/preview/blockedwp/wp-content/uploads/2012/08/5.jpg" alt="">
		
								<h3>Remove product</h3>
							</a>
						</div>
		
						<div class="content">
							<h1>Lorem ipsum dolor ipsdu</h1>
							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Soluta, numquam quis perspiciatis ea ad omnis provident laborum dolore in atque.
						</div>
						<div class="content footer-content">					
							<span class="qt-minus">-</span>
							<span class="qt">3</span>
							<span class="qt-plus">+</span>
		
							<h2 class="full-price">
								6000원
							</h2>
							<h2 class="price">
								2000원
							</h2>
						</div>
					</article>
		
				</section>
		
			</div>
			<div id="site-footer">
				<div class="container clearfix">
					<div class="left">
						<h3 class="subtotal">합계: <span>12000</span>원</h3>
						<h3 class="shipping">배송비: <span>3000</span>원</h3>
					</div>
		
					<div class="right">
						<h1 class="total">총 합계: <span>15000</span>원</h1>
						<a class="btn">결제하기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
 </main>
</body>
</html>
