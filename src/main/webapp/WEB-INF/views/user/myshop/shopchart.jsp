<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<br />
<canvas id="myChart" style="width: 100%; height: 500px"></canvas>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>		
<script>
	var originalArray = '${sellDateList}';
	var labelsArray = JSON.parse('[' + originalArray.replaceAll(/([^,]+)/g, '"$1"') + ']');
	// 대괄호 제거
	labelsArray = labelsArray.map(function(label) {
	    return label.replace(/\[|\]/g, '');
	});
	const ctx = document.getElementById('myChart');
	var myChart = new Chart(ctx, {
	  type: 'doughnut',
	  data: {
	    labels: labelsArray,
	    datasets: [{
	      label: '${shopname}',
	      data: ${sellPriceList},
	      backgroundColor: [
	          'teal',
	          'violet',
	          'blue'
	      ],
	      borderWidth: 3
	    },
	    ]
	  },
	  options: {
	    responsive: false,	
	  }
	});
	 
</script>