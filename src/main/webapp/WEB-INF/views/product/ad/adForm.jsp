<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>광고신청하기</title>
	    <link rel="icon" href="/resources/img/logo.png">
	    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .form-box {
            border: 2px solid #ddd;
            background-color: white;
            padding: 20px;
            margin: 20px auto;
            width: 50%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        select, button {
            margin: 10px 0;
            padding: 10px;
            width: 90%;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        #calculatedPrice {
            margin-top: 20px;
        }
    	</style>
		     <script>
		     function calculatePrice() {
		    	    var selectElement = document.getElementById('daysSelect');
		    	    var value = selectElement.value;
		    	    var price = 0; // 기본 가격을 0으로 초기화
	
		    	    if (value !== 'notChoice2') {
		    	        price = parseInt(value) * 10000;
		    	    }
	
		    	    var formattedPrice = price.toLocaleString();
	
		    	    document.getElementById('calculatedPrice').innerText = price > 0 ? '광고단가 : ' + formattedPrice + '원' : ''; // 가격이 0보다 크면 표시, 아니면 빈 문자열
		    	    document.getElementById('hiddenPrice').value = price;        
		    	}

	
	        function validateForm() {
	            var productSelect = document.getElementById('adSelect').value;
	            var periodSelect = document.getElementById('daysSelect').value;
	
	            if (productSelect === 'notChoice') {
	                alert('상품을 선택해주세요.');
	                return false;
	            }
	
	            if (periodSelect === 'notChoice2') {
	                alert('기간을 선택해주세요.');
	                return false;
	            }

	         
	            return confirm('신청하시겠습니까?');
	        }
	    </script>
	</head>
	<body onload="calculatePrice()">
	
	    <div class="form-box">
        <h2>신청폼 작성하기</h2>
	        <form id="adSubmitForm" action="/user/adFormPro" method="post" onsubmit="return validateForm()">
	            <select id="adSelect" name="adSelect">
	                <option value="notChoice">상품 선택</option>
	                <c:forEach var="my" items="${myproduct}">
	                    <option value="${my.productnum}">${my.productname}</option> 
	                </c:forEach>
	            </select>
	            <select id="daysSelect" name="daysSelect" onchange="calculatePrice()">
	                <option value="notChoice2">기간 선택</option>
	                <option value="1">1일만</option>
	                <option value="7">1주</option>
	                <option value="14">2주</option>
	                <option value="30">한달</option>
	            </select>
	            <p id="calculatedPrice"></p>
	            <input type="hidden" value="${username}" name="username" />
	            <input type="hidden" id="hiddenPrice" name="price" value="0" />
	            <button type="submit">신청하기</button>  
	        </form>
	        <form action="/user/adMain" method="get">
       			 <button type="submit" style="margin-top: 20px;">돌아가기</button>
   			</form>
    	</div>
	</body>
</html>

