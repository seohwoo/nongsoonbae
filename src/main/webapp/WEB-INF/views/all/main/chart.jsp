<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<h1>${catename}</h1>
		  	<canvas id="myChart" style="width: 100%; height: 500px"></canvas>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>		
		<script>
		  const ctx = document.getElementById('myChart');
		
		  var myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		      labels: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		      datasets: [{
		        label: '${lastYear}',
		        data: ${lastlist},
		        borderWidth: 1
		      },
		      {
		          label: '${thisYear}',
		          data: ${thislist},
		          borderWidth: 1
		        }
		      
		      ]
		    },
		    options: {
		      responsive: false,	
		      scales: {
		    	y: {
		    		ticks: {
		    			suggestedMin: 0,
	                    suggestedMax: '${yValue}'
		    		}
		    	}
		      }
		    }
		  });
		 
		  if(${isMembership}) {
			  document.getElementById("myChart").onclick = function clickHandler(evt) {
				    var points = myChart.getElementsAtEventForMode(evt, 'nearest', { intersect: true }, true);
					console.log(points);
				    if (points.length) {
				        var firstPoint = points[0];
				        var month = myChart.data.labels[firstPoint.index];
				        var year = myChart.data.datasets[firstPoint.datasetIndex].label + '년';
				        var value = myChart.data.datasets[firstPoint.datasetIndex].data[firstPoint.index];
				        var name = '${catename}';
				        window.location.href = '/membership/detailChart?year=' + year +'&month=' + month+'&value=' + value+'&name=' + name; 
				    }
				}
		  }
		  
		  
		  
		</script>
	</body>
</html>