<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
$(function(){
		$("#option").on("click",function(){
			$("#op").append("<input type='text' name='optionname' placeholder='상품명' />");
			$("#op").append("<input type='number' name='optionunit' placeholder='단위' />");
			$("#op").append("${catedto.unit}");
			$("#op").append("<input type='number' name='optiontotalprice' placeholder='상품가격' />");
			$("#op").append("<input type='number' name='optionProductCount' placeholder='상품재고' /> <br />");
		}); 		
	});
</script>    
    
<input type="button" value="추가" id="option" >
<div id="op"></div>

<input type="text" name="optionname" placeholder="상품명" /> 			
<input type="number" name="optionunit" placeholder="단위" />
${catedto.unit} 
<input type="hidden" name="optionamount" value="${catedto.amount}"/>
<input type="hidden" name="optionrealunit" value="${catedto.unit}"/><br />
<input type="number" name="optiontotalprice" placeholder="상품가격" />
<input type="number" name="optionProductCount" placeholder="상품재고" /> <br />