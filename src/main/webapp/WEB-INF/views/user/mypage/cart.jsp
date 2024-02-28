<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style type="text/css">
			.custom-button {
			    width: 100px; /* 버튼의 너비 */
			    height: 40px; /* 버튼의 높이 */
			    background-image: url('/resources/img/kakaoBtn.png'); /* 이미지 경로 */
			    background-size: cover; /* 이미지를 버튼에 맞게 조정 */
			    background-color: white;
			    border: none; /* 테두리 제거 */
			    cursor: pointer; /* 커서를 포인터로 변경하여 클릭 가능한 상태 표시 */
			}
		</style>
		<script>
			$(function(){
				$("#btn_kakao-pay").click(function(){
					// 카카오페이 결제전송
					$.ajax({
						type:'POST'
						,url:'/user/pay/ready'
						,data: {
							isMembership: '${isMembership}'
						}
						,success:function(response){
							location.href = response.next_redirect_pc_url;			
						}
					})
					// 버튼 클릭이벤트 해제
				})
			})	
		</script>
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
	  
	    $(".full-price").each(function(index) {
	        price += parseFloat($(".full-price").eq(index).html());
	    });
	  
	    price = Math.round(price * 100) / 100;
	    var shipping = price >= 50000 ? 0 : 3000;
	    var fullPrice = Math.round((price + shipping) * 100) / 100;

	    if (price == 0) {
	        fullPrice = 0;
	    }
	    $(".subtotal span").html(price);
	    $(".shipping span").html(shipping); 
	    $(".total span").html(fullPrice);

	    $("#subtotalAmount").html(price);
	    $("#totalAmount").html(fullPrice);
	}
	
	$(document).ready(function(){
		changeTotal();
		$(".remove").click(function () {
            var el = $(this);
            var optionnum = el.data("optionnum");

            $.ajax({
                type: "POST",
                url: "/user/deleteCartItem",
                data: { optionnum: optionnum },
                success: function(response) {
                    el.parent().parent().addClass("removed");
                    window.setTimeout(function() {
                        el.parent().parent().slideUp('fast', function() {
                            el.parent().parent().remove();
                            if ($(".product").length == 0) {
                                if (check) {
                                    $("#cart").html("<h1>장바구니에 담은 상품이 없습니다.</h1>");
                                }
                            }
                            changeTotal();
                        });
                    }, 200);
                },
                error: function(error) {
                    console.log("Error deleting item:", error);
                }
            });
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
	  $(".btn").click(function() {
	        check = true;
	        $(".remove").click();
	    });
	  
	  function updateQuantity(element, price, optionnum, action) {
		    var quantityElement = $(element).parent().children(".qt");
		    var count = parseInt(quantityElement.html());

		    if (action === 'plus') {
		        quantityElement.html(count + 1);
		    } else if (action === 'minus' && count > 1) {
		        quantityElement.html(count - 1);
		    }

		    var newCount = parseInt(quantityElement.html());

		    var totalPriceElement = $(element).parent().children(".full-price");
		    var eq = Math.round(price * newCount * 100) / 100;
		    totalPriceElement.html(eq + "원");

		    changeTotal();
		}
	  
	  $(".btn").click(function(){
	    check = true;
	    $(".remove").click();
	  });
	});
</script>
<main class="col-9 bd-content" role="main" style="margin-bottom:10%; margin-left: 17%;">
	<div class="container like">
      <h1>장바구니</h1><br />
	 
		  <div class="cart">
		  	<div class="container-cart">
				<section id="cart"> 
				<c:if test="${cartstatus eq 0}">
					<article class="product">
						<div class="content">
							<p>장바구니에 담긴 상품이 없습니다.</p>
						</div>
					</article>
				</c:if>
				<c:if test="${cartstatus eq 1}">
					<c:forEach var="cart" items="${MyCart}">
						<article class="product">
							<div class="cart-image">
									<img src="/resources/realImage/${cart.filename}" alt="">
							</div>
							<div class="content">
								<h1><a href="/product/productInfo?productnum=${cart.productnum}&follow=${cart.username}">${cart.productname}</a></h1><button type="button" class="remove" data-optionnum="${cart.optionnum}">🗑</button>
								<p style="color: #FFBF00;">${cart.optionname}</p>
								<p style="color: green; font-size: 11px;"> ${cart.shopname}</p>
							</div>
							<div class="content footer-content">					
							    <span class="qt-minus" onclick="updateQuantity(this, ${cart.price}, '${cart.optionnum}', 'minus')">-</span>
							    <span class="qt">${cart.count}</span>
							    <span class="qt-plus" onclick="updateQuantity(this, ${cart.price}, '${cart.optionnum}', 'plus')">+</span>
							    <h2 class="full-price">${cart.count * cart.price}원</h2>
							    <h2 class="price">${cart.price}원</h2>
							</div>
						</article>
					</c:forEach>
					<div id="site-footer">
						<div class="container clearfix">
							<div class="left">
								<h3 class="subtotal">합계: <span id="subtotalAmount"></span>원</h3>
								<h3 class="shipping">배송비: <span>3000</span>원</h3>
							</div>
				
							<div class="right">
								<h1 class="total">총 합계: <span id="totalAmount"></span>원</h1>
								<button id="btn_kakao-pay" class="custom-button"></button>
							</div>
						</div>
					</div>
				</c:if>	
				</section>
			</div>
		</div>
	</div>
 </main>
</body>
</html>
