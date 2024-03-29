<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지 작성</title>
		<link rel="icon" href="/resources/img/logo.png">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="/resources/summernote/summernote-lite.js"></script>
		<script src="/resources/summernote/summernote-ko-KR.js"></script>
		<link rel="stylesheet" href="/resources/summernote/summernote-lite.css">
	</head>
	<body>
	<%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
	<h1>공지 작성하기</h1>
		<form method="post" action="/admin/noticePro" enctype="multipart/form-data" id="notice">
			<input type="text"  placeholder="제목을 입력해주세요." name="title" style="width: 800px;" >
			<input type="hidden" value="${num}" name="num" />
			<textarea id="summernote" name="content"></textarea>
			<input type="submit" name="doit" value="작성하기" />	
			<button type="button" onclick="location.href='/admin/noticeList'">돌아가기</button>
		</form>
	<script>
		 $(document).ready(function () {
		        $('#summernote').summernote({
		            codeviewFilter: false, // 코드 보기 필터 비활성화
		            codeviewIframeFilter: false, // 코드 보기 iframe 필터 비활성화

		            height: 500, // 에디터 높이
		            minHeight: null, // 최소 높이
		            maxHeight: null, // 최대 높이
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
			         $('#notice').append("<input type='hidden' name='fileNames' value='"+data.fileName+"' />");
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
			$('#notice').on('submit', function() {
            // num 값 증가
            var numValue = parseInt($('input[name="num"]').val());
            $('input[name="num"]').val(numValue + 1);
        	});
			
			
		</script>	
		
	
	</body>
</html>