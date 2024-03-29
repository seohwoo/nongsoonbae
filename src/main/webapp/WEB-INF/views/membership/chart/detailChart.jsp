<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>멤버쉽차트</title>
		<link rel="icon" href="/resources/img/logo.png">
	</head>
	<body>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
		<h1>${name}</h1>
		<canvas id="myChart" style="width: 100%; height: 500px"></canvas>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>		
		<script>
		var originalArray = '${monthList}';
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
		        label: '${day}',
		        data: ${valueList},
		        borderWidth: 3
		      },
		      ]
		    },
		    options: {
		      responsive: false,	
		      scales: {
		    	y: {
		    		ticks: {
		    			suggestedMin: 0,
	                    suggestedMax: '${max}'
		    		}
		    	}
		      }
		    }
		  });
		 
		</script>
		<br />
		<br />
		<br />
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>