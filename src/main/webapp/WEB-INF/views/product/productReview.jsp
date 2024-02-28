<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		<link rel="icon" href="/resources/img/logo.png">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    	<link rel="stylesheet" href="/resources/css/mainstyle.css">
    	<link rel="stylesheet" href="/resources/css/formstyle.css">
    	<link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet" /> 
		<link rel="stylesheet" href="/resources/summernote/summernote-lite.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    	<script src="/resources/summernote/summernote-lite.js"></script>
		<script src="/resources/summernote/summernote-ko-KR.js"></script>		 
    	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />


	</head>

	<script>
		$(document).ready(function () {
	        $('#summernote').summernote({
	            codeviewFilter: false, // 코드 보기 필터 비활성화
	            codeviewIframeFilter: false, // 코드 보기 iframe 필터 비활성화
	
	            height: 500, // 에디터 높이
	            minHeight: null, // 최소 높이
	            maxHeight: 100, // 최대 높이
	            focus: true, // 에디터 로딩 후 포커스 설정
	            lang: 'ko-KR', // 언어 설정 (한국어)
	
	            toolbar: [
	                ['style', ['style']], // 글자 스타일 설정 옵션
	                ['fontsize', ['fontsize']], // 글꼴 크기 설정 옵션
	                ['font', ['bold', 'underline', 'clear']], // 글자 굵게, 밑줄, 포맷 제거 옵션
	                ['color', ['color']], // 글자 색상 설정 옵션
	                ['table', ['table']], // 테이블 삽입 옵션
	                ['para', ['ul', 'ol', 'paragraph']], // 문단 스타일, 순서 없는 목록, 순서 있는 목록 옵션
	                ['height', ['height']], // 에디터 높이 조절 옵션
	                ['insert', ['picture', 'link', 'video']], // 이미지 삽입, 링크 삽입, 동영상 삽입 옵션
	                ['view', ['codeview', 'fullscreen', 'help']], // 코드 보기, 전체 화면, 도움말 옵션
	            ],
	
	            fontSizes: [
	                '8', '9', '10', '11', '12', '14', '16', '18',
	                '20', '22', '24', '28', '30', '36', '50', '72',
	            ], // 글꼴 크기 옵션
	
	            styleTags: [
	                'p',  // 일반 문단 스타일 옵션
	                {
	                    title: 'Blockquote',
	                    tag: 'blockquote',
	                    className: 'blockquote',
	                    value: 'blockquote',
	                },  // 인용구 스타일 옵션
	                'pre',  // 코드 단락 스타일 옵션
	                {
	                    title: 'code_light',
	                    tag: 'pre',
	                    className: 'code_light',
	                    value: 'pre',
	                },  // 밝은 코드 스타일 옵션
	                {
	                    title: 'code_dark',
	                    tag: 'pre',
	                    className: 'code_dark',
	                    value: 'pre',
	                },  // 어두운 코드 스타일 옵션
	                'h1', 'h2', 'h3', 'h4', 'h5', 'h6',  // 제목 스타일 옵션
	            ],
	
	            callbacks: {   //여기 부분이 이미지를 첨부하는 부분
	                onImageUpload : function(files) {   
	                   for(i = 0 ; i < files.length ; i++){
	                      uploadSummernoteImageFile(files[i],this);
	                   }
	                },
	                onPaste: function (e) {
	                   var clipboardData = e.originalEvent.clipboardData;
	                   if (clipboardData && clipboardData.items && clipboardData.items.length) {
	                      var item = clipboardData.items[0];
	                      if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
	                         e.preventDefault();
	                      }
	                   }
	                }
	             }
	           });
	       });
		
		function uploadSummernoteImageFile(file, editor) {
		   data = new FormData();
		   data.append("file", file);
		   $.ajax({
		      data : data,
		      type : "POST",
		      url : "/test/uploadSummernoteImageFile",
		      contentType : false,
		      processData : false,
		      dataType: 'json',
		      success : function(data) {
		         $('#addProduct').append("<input type='hidden' name='fileNames' value='"+data.fileName+"' />");
		         console.log(data.fileName);
		           //항상 업로드된 파일의 url이 있어야 한다.
		         $(editor).summernote('insertImage', data.url);;
		      }
		   });
		}
		
		$("div.note-editable").on('drop',function(e){
		    for(i=0; i< e.originalEvent.dataTransfer.files.length; i++){
		       uploadSummernoteImageFile(e.originalEvent.dataTransfer.files[i],$("#summernote")[0]);
		    }
		   e.preventDefault();
		})
	</script>

	<body style="background-color: #f5f5f5;">
	<h2>리뷰 작성</h2>
		<form action="/product/productReviewPro" method="post" name="form" enctype="multipart/form-data" id = "addProduct">
			
			<textarea placeholder="리뷰 작성" name="content" style="width:350px;height:250px"></textarea>
			<br />
			<input type="file" class="btns2" name="filelist"  multiple="multiple" value="사진 첨부"  onchange="displayFileCount()" />
			<br />
			상품의 별점을 남겨주세요.
			<br />
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
			    
		    <input type="hidden" name="name" value="${name}" />
		    <input type="hidden" name="productnum" value="${productnum}" />
		    <input type="hidden" name="optionnum" value="${optionnum}" />
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			    
		    <tr> 
				<td colspan="2" align="center">
		    		<input type="submit" name="confirm" style="border: none;background-color: #369F36;color: #fff;" value="리뷰쓰기" />
		    	</td>
		    </tr>		
		</form>
	</body>
</html>

<script>
    const stars = document.querySelectorAll('.stars1 .star');
    const radioButtons = document.querySelectorAll('input[name="stars"]');
    
    // 초기에 5개 별을 채워진 상태로 표시
    for (let i = 0; i < stars.length; i++) {
        const sRating = parseInt(stars[i].getAttribute("data-rating"));
        if (sRating <= 5) {
            stars[i].innerHTML = '<i class="fas fa-star" style="color: #ffc83d;"></i>';
        } else {
            stars[i].innerHTML = '<i class="far fa-star" style="color: #ffc83d;"></i>';
        }
    }

    radioButtons.forEach((radio, index) => {
        radio.addEventListener('change', () => {
            const starsValue = parseInt(radio.value);
            for (let i = 0; i < stars.length; i++) {
                const sRating = parseInt(stars[i].getAttribute("data-rating"));
                if (sRating <= starsValue) {
                    stars[i].innerHTML = '<i class="fas fa-star" style="color: #ffc83d;"></i>';
                } else {
                    stars[i].innerHTML = '<i class="far fa-star" style="color: #ffc83d;"></i>';
                }
            }
        });
    });

    // 페이지 로딩 시 별점 값이 없다면 기본값으로 5점 설정
    document.addEventListener("DOMContentLoaded", function () {
        const selectedStars = document.querySelector('input[name="stars"]:checked');
        if (!selectedStars) {
            radioButtons[4].click();  // 5번째 별을 클릭하여 5점으로 설정
        }
    });

    function validateAndSubmit() {
        const selectedStars = document.querySelector('input[name="stars"]:checked');
        const reviewTextarea = document.querySelector('textarea[name="content"]');
        
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