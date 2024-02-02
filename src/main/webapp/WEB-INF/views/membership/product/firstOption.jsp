<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
var result = 0;
$(function(){
		$("#option").on("click",function(){
			$("#op").append("<input type='text' name='optionname' placeholder='상품명' />");
			$("#op").append("<input type='number' name='optionunit' placeholder='단위' />");
			$("#op").append("${catedto.unit}");
			$("#op").append("<input type='number' name='optiontotalprice' placeholder='상품가격' />");
			$("#op").append("<input type='number' name='optionProductCount' placeholder='상품재고' /> <br />");
		}); 		
	});

$(function() {
	$("#optiontotalprice").on("change",function(){
		if($("#optiontotalprice").val()<100000000) {
			$.ajax({
				url:"/membership/changePrice",
				data:{
						optionPrice :$("#optiontotalprice").val(),
						optionunit: $("#optionunit").val(),
						cateunit: '${catedto.amount}',
						catename: '${catedto.catename}'
					},
				success:function(data){
					result = parseInt(data);
					if(result > 0) {
						$("#avg").html("<h6 style='color: blue;'>평균가격보다 " + result +"원 싸다</h6>");
					}else {
						$("#avg").html("<h6 style='color: red;'>평균가격보다 " + -result +"원 비싸다</h6>");
					}
				}
			});
		}else{
			$("#avg").html("<h6 style='color: red;'>값이 너무 큽니다!!</h6>");
		}
	});
});

</script>    
    
<input type="button" value="추가" id="option" >
<div id="op"></div>
<div id="avg"></div>
<input type="text" name="optionname" placeholder="상품명" /> 			
<input type="number" name="optionunit" id="optionunit" placeholder="단위" value="${catedto.amount}" />
${catedto.unit} 
<input type="hidden" name="optionamount" value="${catedto.amount}"/>
<input type="hidden" name="optionrealunit" value="${catedto.unit}"/><br />
<input type="number" name="optiontotalprice" id="optiontotalprice" placeholder="상품가격" />
<input type="number" name="optionProductCount" placeholder="상품재고" /> <br />