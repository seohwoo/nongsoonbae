<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>판매 등록 홈페이지</title>
	</head>
	<script src="/resources/js/jquery-3.7.1.min.js"></script>

	<script>
		num = null;
		vnum = null;
 		function changes1Step(fr) {
 			if(fr=="-----"){
			num=new Array("-----");
			vnum=new Array("-----");
  }
  else if(fr=="과일") {
	  console.log("else111");
   num = new Array("-----", "감/딸기/토마토", "감귤/배/방울토마토", "사과/멜론/아보카도", "수박/레몬/망고", "참외/복숭아/바나나", "포도/오렌지/체리", "키위/파인애플");
   vnum = new Array("-----", "감/딸기/토마토", "감귤/배/방울토마토", "사과/멜론/아보카도", "수박/레몬/망고", "참외/복숭아/바나나", "포도/오렌지/체리", "키위/파인애플");
  }
  else if(fr=="채소") {
	  console.log("else222");
   num = new Array("-----", "당근/마늘/파", "생강/양파/양배추", "상추/배추/얼갈이배추", "깻잎/피망/파프리카", "풋고추/건고추/붉은고추", "호박/갓/고춧가루", "무/미나리/브로콜리", "시금치/열무/오이");
   vnum = new Array("-----", "당근/마늘/파", "생강/양파/양배추", "상추/배추/얼갈이배추", "깻잎/피망/파프리카", "풋고추/건고추/붉은고추", "호박/갓/고춧가루", "무/미나리/브로콜리", "시금치/열무/오이");
  }
  else if(fr=="식량작물") {
	  console.log("else333");
	   num = new Array("-----", "쌀/찹쌀", "콩/팥/녹두", "감자/고구마");
	   vnum = new Array("-----", "쌀/찹쌀", "콩/팥/녹두", "감자/고구마");
  }
  else if(fr=="수산물") {
	  console.log("else444");
	   num = new Array("-----", "멸치/건미역/건오징어", "갈치/조기/고등어", "멸치액젓/굵은소금/새우젓", "꽁치/명태/오징어", "새우/김", "굴/전복");
	   vnum = new Array("-----", "멸치/건미역/건오징어", "갈치/조기/고등어", "멸치액젓/굵은소금/새우젓", "꽁치/명태/오징어", "새우/김", "굴/전복");
  }
  else if(fr=="특용작물") {
	  console.log("else555");
	   num = new Array("-----", "버섯", "땅콩/아몬드", "호두/참깨");
	   vnum = new Array("-----", "버섯", "땅콩/아몬드", "호두/참깨");
  }

  for(i=form.Step2.length-1; i >= 0; i--) {
   	form.Step2.options[i]=null;
  }
 	
  for(i=form.cate3.length-1; i>=0; i--) {
	   form.cate3.options[i] = null;
	}
  form.cate3.options[0] = new Option("-----", "-----");
 
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
	  
	  
	  ////////
	  else
	  {
	   num = new Array("고르시오");
	   vnum = new Array("고르시오");
	  } 
	  
	  for(i=0; i<form.cate3.length; i++) {
	   form.cate3.options[i] = null;
	  }
	  for(i=0; i<num.length; i++) {
	   form.cate3.options[i] = new Option(num[i],vnum[i]);
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
		<h3>${username} 페이지</h3>
		<form action="/product/productWritePro" method="post" name="form" enctype="multipart/form-data">
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
				<select name="cate3">
					<option>-----</option>
				</select>		
			    
			    <tr> 
					<td width="200">상품이름</td>
					<td width="400"> 
						<input type="text" name="productname" size="15" required="required">
					</td>
			    </tr>
			    
			    <tr> 
					<td width="200">사진</td>
					<td width="400"> 
						<input type="file" name="files" multiple="multiple" onchange="displayFileCount()" required="required">
					</td>
			    </tr>
			    
			    <tr> 
					<td width="200">상품설명</td>
					<td width="400"> 
						<input type="text" name="content" size="15" required="required">
					</td>
			    </tr>
			    
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
						<input type="submit" name="confirm" value="상품등록" >
						<input type="reset" name="reset" value="다시입력">
						<input type="button" value="등록안함" onclick="javascript:window.location='/product/productMain'">
					</td>
				</tr>			    
			</table>	
		</form>
	</body>
</html>