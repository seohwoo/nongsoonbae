<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>판매 등록 홈페이지</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="/resources/summernote/summernote-lite.js"></script>
		<script src="/resources/summernote/summernote-ko-KR.js"></script>
		<link rel="stylesheet" href="/resources/summernote/summernote-lite.css">		
	</head>

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

		num = null;
		vnum = null;
 		function changes1Step(fr) {
 			if(fr=="-----"){
 				num=new Array("-----");
 				vnum=new Array("-----");
 			}
 			else if(fr=="과일") {
 				num = new Array("-----", "감/딸기/토마토", "감귤/배/방울토마토", "사과/멜론/아보카도", "수박/레몬/망고", "참외/복숭아/바나나", "포도/오렌지/체리", "키위/파인애플");
 				vnum = new Array("-----", "감/딸기/토마토", "감귤/배/방울토마토", "사과/멜론/아보카도", "수박/레몬/망고", "참외/복숭아/바나나", "포도/오렌지/체리", "키위/파인애플");
 			}
 			else if(fr=="채소") {
 				num = new Array("-----", "당근/마늘/파", "생강/양파/양배추", "상추/배추/얼갈이배추", "깻잎/피망/파프리카", "풋고추/건고추/붉은고추", "호박/갓/고춧가루", "무/미나리/브로콜리", "시금치/열무/오이");
 				vnum = new Array("-----", "당근/마늘/파", "생강/양파/양배추", "상추/배추/얼갈이배추", "깻잎/피망/파프리카", "풋고추/건고추/붉은고추", "호박/갓/고춧가루", "무/미나리/브로콜리", "시금치/열무/오이");
 			}
 			else if(fr=="식량작물") {
 				num = new Array("-----", "쌀/찹쌀", "콩/팥/녹두", "감자/고구마");
 				vnum = new Array("-----", "쌀/찹쌀", "콩/팥/녹두", "감자/고구마");
 			}
 			else if(fr=="수산물") {
 				num = new Array("-----", "멸치/건미역/건오징어", "갈치/조기/고등어", "멸치액젓/굵은소금/새우젓", "꽁치/명태/오징어", "새우/김", "굴/전복");
 				vnum = new Array("-----", "멸치/건미역/건오징어", "갈치/조기/고등어", "멸치액젓/굵은소금/새우젓", "꽁치/명태/오징어", "새우/김", "굴/전복");
 			}
 			else if(fr=="특용작물") {
 				num = new Array("-----", "버섯", "땅콩/아몬드", "호두/참깨");
 				vnum = new Array("-----", "버섯", "땅콩/아몬드", "호두/참깨");
 			}
 			for(i=form.Step2.length-1; i >= 0; i--) {
 				form.Step2.options[i]=null;
 			}
 			for(i=form.categorynum.length-1; i>=0; i--) {
 				form.categorynum.options[i] = null;
 			}
 			form.categorynum.options[0] = new Option("-----", "-----");
 			for(i=0; i< num.length; i++) {  
 				form.Step2.options[i] = new Option(num[i],vnum[i]);
 			}
 		}
 		
 		function changes2Step(fr) {
 			if(fr=="감/딸기/토마토") {
 				num = new Array("감", "딸기", "토마토");
 				vnum = new Array("111", "112", "113");
 			}
 			else if(fr=="감귤/배/방울토마토") {
 				num = new Array("감귤", "배", "방울토마토");
 				vnum = new Array("121", "122", "123");
 			}
 			else if(fr=="사과/멜론/아보카도") {
 				num = new Array("사과", "멜론", "아보카도");
 				vnum = new Array("131", "132", "133");
 			}
 			else if(fr=="수박/레몬/망고") {
 				num = new Array("수박", "레몬", "망고");
 				vnum = new Array("141", "142", "143");
 			}
 			else if(fr=="참외/복숭아/바나나") {
 				num = new Array("참외", "복숭아", "바나나");
 				vnum = new Array("151", "152", "153");
 			}
 			else if(fr=="포도/오렌지/체리") {
 				num = new Array("포도", "오렌지", "체리");
 				vnum = new Array("161", "162", "163");
 			}
 			else if(fr=="키위/파인애플") {
 				num = new Array("키위", "파인애플");
 				vnum = new Array("171", "172");
 			}
 			// 채소
 			else if(fr=="당근/마늘/파") {
 				num = new Array("당근", "마늘", "파");
 				vnum = new Array("211", "212", "213");
 			}
 			else if(fr=="생강/양파/양배추") {
 				num = new Array("생강", "양파", "양배추");
 				vnum = new Array("221", "222", "223");
 			}
 			else if(fr=="상추/배추/얼갈이배추") {
 				num = new Array("상추", "배추", "얼갈이배추");
 				vnum = new Array("231", "232", "233");
 			}
 			else if(fr=="깻잎/피망/파프리카") {
 				num = new Array("깻잎", "피망", "파프리카");
 				vnum = new Array("241", "242", "243");
 			}
 			else if(fr=="풋고추/건고추/붉은고추") {
 				num = new Array("풋고추", "건고추", "붉은고추");
 				vnum = new Array("251", "252", "253");
 			}
 			else if(fr=="호박/갓/고춧가루") {
 				num = new Array("호박", "갓", "고춧가루");
 				vnum = new Array("261", "262", "263");
 			}
 			else if(fr=="무/미나리/브로콜리") {
 				num = new Array("무", "미나리", "브로콜리");
 				vnum = new Array("271", "272", "273");
 			}
 			else if(fr=="시금치/열무/오이") {
 				num = new Array("시금치", "열무", "오이");
 				vnum = new Array("281", "282", "283");
 			}
 			// 식량작물
 			else if(fr=="쌀/찹쌀") {
 				num = new Array("쌀", "찹쌀");
 				vnum = new Array("311", "312");
 			}
 			else if(fr=="콩/팥/녹두") {
 				num = new Array("콩", "팥", "녹두");
 				vnum = new Array("321", "322", "323");
 			}
 			else if(fr=="감자/고구마") {
 				num = new Array("감자", "고구마");
 				vnum = new Array("331", "332");
 			}
 			// 수산물
 			else if(fr=="멸치/건미역/건오징어") {
 				num = new Array("멸치", "건미역", "건오징어");
 				vnum = new Array("411", "412", "413");
 			}
 			else if(fr=="갈치/조기/고등어") {
 				num = new Array("갈치", "조기", "고등어");
 				vnum = new Array("421", "422", "423");
 			}
 			else if(fr=="멸치액젓/굵은소금/새우젓") {
 				num = new Array("멸치액젓", "굵은소금", "새우젓");
 				vnum = new Array("431", "432", "433");
 			}
 			else if(fr=="꽁치/명태/오징어") {
 				num = new Array("꽁치", "명태", "오징어");
 				vnum = new Array("441", "442", "443");
 			}
 			else if(fr=="새우/김") {
 				num = new Array("새우", "김");
 				vnum = new Array("451", "452");
 			}
 			else if(fr=="굴/전복") {
 				num = new Array("굴", "전복");
 				vnum = new Array("461", "462");
 			}
 			// 특용작물
 			else if(fr=="버섯") {
 				num = new Array("느타리버섯", "팽이버섯", "송이버섯");
 				vnum = new Array("511", "513", "512");
 			}
 			else if(fr=="땅콩/아몬드") {
 				num = new Array("땅콩", "아몬드");
 				vnum = new Array("521", "522");
 			}
 			else if(fr=="호두/참깨") {
 				num = new Array("호두", "참깨");
 				vnum = new Array("531", "532");
 			}
 			else
 			{
 				num = new Array("고르시오");
 				vnum = new Array("고르시오");
 			} 
 			for(i=0; i<form.categorynum.length; i++) {
 				form.categorynum.options[i] = null;
 			}
 			for(i=0; i<num.length; i++) {
 				form.categorynum.options[i] = new Option(num[i],vnum[i]);
 			}
 		}
 		
 		$(function(){
 			$("#option").on("click",function(){
 				$("#op").append("<input type='text' name='optionname' placeholder='상품명' />"); 			
 				$("#op").append("<input type='number' name='optiontotalprice' placeholder='상품가격' />");
 				$("#op").append("<input type='number' name='optionProductCount' placeholder='상품재고' /> <br />");
 			}); 		
 		});
	</script>

	<body>
		<h3>${myName} 페이지</h3>
		<form action="/product/productWritePro" method="post" name="form" enctype="multipart/form-data" id = "addProduct">
			<table width="850" border="1">
				 		
				<select name='Step1' onchange='changes1Step(value)'>
					<option value="-----">-----</option>
					<option value="과일">과일</option>
					<option value="채소">채소</option>
					<option value="식량작물">식량작물</option>
					<option value="수산물">수산물</option>
					<option value="특용작물">특용작물</option>
				</select>
				
				<select name='Step2' onchange='changes2Step(value)'>
					<option>-----</option>
				</select>
				<select name="categorynum">
					<option>-----</option>
				</select>		
			    
			    <tr> 
					<td width="200">상품이름</td>
					<td width="400"> 
						<input type="text" name="productname" size="15" required="required">
					</td>
			    </tr>
			    
			    <textarea id="summernote" name="content"></textarea>
			    
			    <tr> 
					<td width="200">판매종료일</td>
					<td width="400"> 
						<input type="date" name="enddate" size="15" required="required">
					</td>
			    </tr>
		
			    <tr> 
				    <td width="200">옵션</td>
				    <td width="400">				   		
				   		<input type="button" value="추가" id="option" >
				   		<div id="op"></div>
				   		
				   		<input type="text" name="optionname" placeholder="상품명" /> 			
			 			<input type="number" name="optiontotalprice" placeholder="상품가격" />
			 			<input type="number" name="optionProductCount" placeholder="상품재고" /> <br />
				    </td>
				</tr>
		
			    <input type="hidden" name="username" value="${username}" />
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			    
			    <tr> 
					<td colspan="2" align="center"> 
						<input type="submit" name="confirm" value="상품등록">
						<input type="reset" name="reset" value="다시입력">
						<input type="button" value="등록안함" onclick="javascript:window.location='/product/productMain'">
					</td>
				</tr>			    
			</table>	
		</form>
	</body>
</html>