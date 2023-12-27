<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>차트</title>
	</head>
	<body>
		<center><h1>토마토</h1></center>
		<div>
		  <canvas id="myChart" style="width: 100%; height: 500px"></canvas>
		</div>
		
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		
		<script>
		  const ctx = document.getElementById('myChart');
		
		  new Chart(ctx, {
		    type: 'line',
		    data: {
		      labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
		      datasets: [{
		        label: '2022',
		        data: [12, 19, 33, 15, 222, 30],
		        borderWidth: 1
		      },
		      {
		          label: '2023',
		          data: [20, 70, 90, 100, 21, 55],
		          borderWidth: 1
		        }
		      
		      ]
		    },
		    options: {
		      scales: {
		        y: {
		          beginAtZero: true
		        }
		      }
		    }
		  });
		</script>
	</body>
</html>