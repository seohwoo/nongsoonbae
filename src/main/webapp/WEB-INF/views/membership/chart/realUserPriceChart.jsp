<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${catename} 유처차트</title>
		<link rel="icon" href="/resources/img/logo.png">
	</head>
	<body>
		<c:if test="${isChart==0}">
			<h1>상품이 없습니다.</h1>
		</c:if>
		<c:if test="${isChart>0}">
			<canvas id="myChart" style="width: 100%; height: 500px"></canvas>
			<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>		
			<script>
			var originalArray = '${userMarketList}';
			var labelsArray = JSON.parse('[' + originalArray.replaceAll(/([^,]+)/g, '"$1"') + ']');
			// 대괄호 제거
			labelsArray = labelsArray.map(function(label) {
			    return label.replace(/\[|\]/g, '');
			});
			  const ctx = document.getElementById('myChart');
			  var myChart = new Chart(ctx, {
			    type: 'line',
			    data: {
			      labels: labelsArray,
			      datasets: [{
			        label: '${catename}',
			        data: ${userPriceList},
			        borderWidth: 3
			      },
			      ]
			    },
			    options: {
			      responsive: false,	
			    }
			  });
			 
			</script>
		</c:if>
	</body>
</html>