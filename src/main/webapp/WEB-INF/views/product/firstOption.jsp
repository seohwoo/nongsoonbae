<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
    $(function () {
        $("#option").on("click", function () {
            $("#op").append("<div class='optionContainer'>" +
                "<input type='text' name='optionname' placeholder='상품명' required />" +
                "<input type='number' name='optionunit' placeholder='단위' required />" +
                "${catedto.unit}" +
                "<input type='number' name='optiontotalprice' placeholder='상품가격' required />" +
                "<input type='number' name='optionProductCount' placeholder='상품재고' required />" +
                "<br />" +
                "<button type='button' class='removeOptionBtn'>삭제</button>" +
                "</div>");

            // 삭제 버튼 클릭 이벤트 처리
            $(".removeOptionBtn").off("click").on("click", function () {
                $(this).closest('.optionContainer').remove();
            });
        });
    });

    // 초기에 존재하는 삭제 버튼 클릭 이벤트 처리
    $(document).on("click", ".removeOptionBtn", function () {
        $(this).closest('.optionContainer').remove();
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