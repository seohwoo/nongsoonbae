<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>광고신청하기</title>
	     <script>
	        function calculatePrice() {
	            var selectElement = document.getElementById('periodSelect');
	            var value = selectElement.value;
	            var price = value * 10000;
	            var formattedPrice = price.toLocaleString();
	            document.getElementById('calculatedPrice').innerText = '광고단가 : ' + formattedPrice + '원';
	        }
	
	        function validateForm() {
	            var productSelect = document.getElementById('adSelect').value;
	            var periodSelect = document.getElementById('periodSelect').value;
	
	            if (productSelect === 'notChoice') {
	                alert('상품을 선택해주세요.');
	                return false;
	            }
	
	            if (periodSelect === 'notChoice2') {
	                alert('기간을 선택해주세요.');
	                return false;
	            }

	            // 사용자에게 최종 확인 요청
	            return confirm('신청하시겠습니까?');
	        }
	    </script>
	</head>
	<body>
	     <h2>신청폼 작성하기</h2>
		    <form id="adSubmitForm" action="/product/adFormPro" method="post" onsubmit="return validateForm()">
		        <select id="adSelect" name="adSelect">
		            <option value="notChoice">상품 선택</option>
		            <c:forEach var="my" items="${myproduct}">
		                <option value="${my.productnum}">${my.productname}</option> 
		            </c:forEach>
		        </select>
		        <select id="periodSelect" onchange="calculatePrice()">
		            <option value="notChoice2">기간 선택</option>
		            <option value="1">1일만</option>
		            <option value="7">1주</option>
		            <option value="14">2주</option>
		            <option value="30">한달</option>
		        </select>
		        <p id="calculatedPrice"></p>
		        <button type="submit">신청하기</button>  
		    </form>
	</body>
</html>

