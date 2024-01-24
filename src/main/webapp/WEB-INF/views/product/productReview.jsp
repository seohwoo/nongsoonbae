<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    	<link rel="stylesheet" href="/resources/css/mainstyle.css">
    	<link rel="stylesheet" href="/resources/css/formstyle.css">
    	<link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet" /> 
    	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />
	</head>
	<body>
		<form action="/product/productReviewPro" method="post">
			<input type="textarea" name="content" size="15" required="required" placeholder="리뷰작성">
			
			<tr> 
				<td width="200">사진</td>
				<td width="200"> 
					<input type="text" name="imagecount" size="15" required="required">
				</td>
			</tr>

			<div class="stars1">
				<label for="star1" class="star" data-rating="1"> <i class="far fa-star" style="color: #ffc83d;"></i></label>
				<label for="star2" class="star" data-rating="2"> <i class="far fa-star" style="color: #ffc83d;"></i></label>
			    <label for="star3" class="star" data-rating="3"> <i class="far fa-star" style="color: #ffc83d;"></i></label>
			    <label for="star4" class="star" data-rating="4"> <i class="far fa-star" style="color: #ffc83d;"></i></label>
			    <label for="star5" class="star" data-rating="5"> <i class="far fa-star" style="color: #ffc83d;"></i></label>
			</div>
				
			<div>
			    <input type="radio" name="stars" value="1" id="star1" style="display: none;">
			    <input type="radio" name="stars" value="2" id="star2" style="display: none;">
			    <input type="radio" name="stars" value="3" id="star3" style="display: none;">
			    <input type="radio" name="stars" value="4" id="star4" style="display: none;">
			    <input type="radio" name="stars" value="5" id="star5" style="display: none;">
			</div>
	

			    
		    <input type="hidden" name="name" value="${myNickname}" />
		    <input type="hidden" name="productnum" value="${productnum}" />
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			    
		    <tr> 
				<td colspan="2" align="center">
		    		<input type="submit" name="confirm" value="리뷰쓰기" />
		    	</td>
		    </tr>		
		</form>
	</body>
</html>

<script>
	const stars = document.querySelectorAll('.stars1 .star');
	const radioButtons = document.querySelectorAll('input[name="stars"]');
	
	for (let i = 0; i < stars.length; i++) {
	    if (i > -1) {
	        stars[i].innerHTML = '<i class="fas fa-star" style="color: #ffc83d;"></i>';
	    } else {
	        stars[i].innerHTML = ' <i class="far fa-star" style="color: #ffc83d;"></i>';
	    }
	}
	
	radioButtons.forEach((radio, index) => {
	    radio.addEventListener('change', () => {
	        const starsValue = radio.value;
	        for (let i = 0; i < stars.length; i++) {
	            const sRating = parseInt(stars[i].getAttribute("data-rating"));
	            if (sRating <= starsValue) {
	                stars[i].innerHTML = '<i class="fas fa-star" style="color: #ffc83d;"></i>';
	            } else {
	                stars[i].innerHTML = ' <i class="far fa-star" style="color: #ffc83d;"></i>';
	            }
	        }
	    });
	});
	
	function validateAndSubmit() {
	    const selectedStars = document.querySelector('input[name="stars"]:checked');
	    const reviewTextarea = document.querySelector('textarea[name="review"]');
	    if (!selectedStars || reviewTextarea.value.trim() === '') {
	        alert("평점과 리뷰를 모두 입력했는지 확인해주세요.");
	        return false;
	    } else {
	        return true;
	    }
	}
	
	
</script>

<style>
	.stars1 {
    	font-size: 30px;
    	cursor: pointer;
	}
	.stars1 .star {
		color: #FFA500;
    	transition: color 0.3s;
	}
</style>