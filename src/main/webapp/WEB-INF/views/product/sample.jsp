<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SAMPLE</title>
</head>
<!--
<body>
    <form action="your_action_url" method="post"> 

		
		<select id="cate0Select" name="selectedCate0">
            <c:forEach var="category" items="${categoryDTO}">
                <option value="${category.cate1}">${category.cate1}</option>
            </c:forEach>
        </select>
        <hr />
		
        <select id="cate1Select" name="selectedCate1">
            <c:forEach var="cate1" items="${cate1}">
                <option value="${cate1}">${cate1}</option>
            </c:forEach>
        </select>
        <hr />

        <!-- Select for cate2 
        <label for="cate2Select">Select cate2:</label>
        <select id="cate2Select" name="selectedCate2">
            <c:forEach var="cate2" items="${cate2}">
                <option value="${cate2}">${cate2}</option>
            </c:forEach>
        </select>
        <hr />

        <!-- Select for cate3 
        <label for="cate3Select">Select cate3:</label>
        <select id="cate3Select" name="selectedCate3">
            <c:forEach var="cate3" items="${cate3}">
                <option value="${cate3}">${cate3}</option>
            </c:forEach>
        </select>
        <hr />

        <input type="submit" value="Submit">
    </form>
    
    <html>
    -->












<script>
 function changes1Step(fr) {
  if(fr=="과일") {
   num = new Array("감/딸기/토마토", "감귤/배/방울토마토", "사과/멜론/아보카도", "수박/레몬/망고", "참외/복숭아/바나나", "포도/오렌지/체리", "키위/파인애플");
   vnum = new Array("감/딸기/토마토", "감귤/배/방울토마토", "사과/멜론/아보카도", "수박/레몬/망고", "참외/복숭아/바나나", "포도/오렌지/체리", "키위/파인애플");
  }
  else if(fr=="채소") {
   num = new Array("당근/마늘/파", "생강/양파/양배추", "상추/배추/얼갈이배추", "깻잎/피망/파프리카", "풋고추/건고추/붉은고추", "호박/갓/고춧가루", "무/미나리/브로콜리", "시금치/열무/오이");
   vnum = new Array("당근/마늘/파", "생강/양파/양배추", "상추/배추/얼갈이배추", "깻잎/피망/파프리카", "풋고추/건고추/붉은고추", "호박/갓/고춧가루", "무/미나리/브로콜리", "시금치/열무/오이");
  }
  else if(fr=="식량작물") {
	   num = new Array("쌀/찹쌀", "콩/팥/녹두", "감자/고구마");
	   vnum = new Array("쌀/찹쌀", "콩/팥/녹두", "감자/고구마");
  }
  else if(fr=="수산물") {
	   num = new Array("멸치/건미역/건오징어", "갈치/조기/고등어", "멸치액젓/굵은소금/새우젓", "꽁치/명태/오징어", "새우/김", "굴/전복");
	   vnum = new Array("멸치/건미역/건오징어", "갈치/조기/고등어", "멸치액젓/굵은소금/새우젓", "꽁치/명태/오징어", "새우/김", "굴/전복");
  }
  else if(fr=="특용작물") {
	   num = new Array("버섯", "땅콩/아몬드", "호두/참깨");
	   vnum = new Array("버섯", "땅콩/아몬드", "호두/참깨");
  }
  
  
  
  for(i=0; i<form.Step2.length; i++) {
   form.Step2.options[i] = null;
  }
  
  for(i=0; i<form.Step3.length; i++) {
   form.Step3.options[i] = null;
  }
   form.Step3.options[0] = new Option("???", "???");
  for(i=0; i<num.length; i++) {
   form.Step2.options[i] = new Option(num[i],vnum[i]);
  }
 }
 
 
 function changes2Step(fr) {
  // 과일
  if(fr=="감/딸기/토마토") {
   num = new Array("감", "딸기", "토마토");
   vnum = new Array("감", "딸기", "토마토");
  }
  else if(fr=="감귤/배/방울토마토") {
   num = new Array("감귤", "배", "방울토마토");
   vnum = new Array("감귤", "배", "방울토마토");
  }
  else if(fr=="사과/멜론/아보카도") {
	   num = new Array("사과", "멜론", "아보카도");
	   vnum = new Array("사과", "멜론", "아보카도");
  }
  else if(fr=="수박/레몬/망고") {
	   num = new Array("수박", "레몬", "망고");
	   vnum = new Array("수박", "레몬", "망고");
  }
  else if(fr=="참외/복숭아/바나나") {
	   num = new Array("참외", "복숭아", "바나나");
	   vnum = new Array("참외", "복숭아", "바나나");
  }
  else if(fr=="포도/오렌지/체리") {
	   num = new Array("포도", "오렌지", "체리");
	   vnum = new Array("포도", "오렌지", "체리");
  }
  else if(fr=="키위/파인애플") {
	   num = new Array("키위", "파인애플");
	   vnum = new Array("키위", "파인애플");
  }
  // 채소
  else if(fr=="당근/마늘/파") {
   num = new Array("당근", "마늘", "파");
   vnum = new Array("당근", "마늘", "파");
  }
  else if(fr=="생강/양파/양배추") {
   num = new Array("생강", "양파", "양배추");
   vnum = new Array("생강", "양파", "양배추");
  }
  else if(fr=="상추/배추/얼갈이배추") {
   num = new Array("상추", "배추", "얼갈이배추");
   vnum = new Array("상추", "배추", "얼갈이배추");
  }
  else if(fr=="깻잎/피망/파프리카") {
   num = new Array("깻잎", "피망", "파프리카");
   vnum = new Array("깻잎", "피망", "파프리카");
  }
  else if(fr=="풋고추/건고추/붉은고추") {
	   num = new Array("풋고추", "건고추", "붉은고추");
	   vnum = new Array("풋고추", "건고추", "붉은고추");
  }
  else if(fr=="호박/갓/고춧가루") {
	   num = new Array("호박", "갓", "고춧가루");
	   vnum = new Array("호박", "갓", "고춧가루");
  }
  else if(fr=="무/미나리/브로콜리") {
	   num = new Array("무", "미나리", "브로콜리");
	   vnum = new Array("무", "미나리", "브로콜리");
  }
  else if(fr=="시금치/열무/오이") {
	   num = new Array("시금치", "열무", "오이");
	   vnum = new Array("시금치", "열무", "오이");
  }
  // 식량작물
  else if(fr=="쌀/찹쌀") {
	   num = new Array("쌀", "찹쌀");
	   vnum = new Array("쌀", "찹쌀");
  }
  else if(fr=="콩/팥/녹두") {
	   num = new Array("콩", "팥", "녹두");
	   vnum = new Array("콩", "팥", "녹두");
  }
  else if(fr=="감자/고구마") {
	   num = new Array("감자", "고구마");
	   vnum = new Array("감자", "고구마");
  }
  // 수산물
   else if(fr=="멸치/건미역/건오징어") {
	   num = new Array("멸치", "건미역", "건오징어");
	   vnum = new Array("멸치", "건미역", "건오징어");
   }
   else if(fr=="갈치/조기/고등어") {
	   num = new Array("갈치", "조기", "고등어");
	   vnum = new Array("갈치", "조기", "고등어");
   }
   else if(fr=="멸치액젓/굵은소금/새우젓") {
	   num = new Array("멸치액젓", "굵은소금", "새우젓");
	   vnum = new Array("멸치액젓", "굵은소금", "새우젓");
   }
   else if(fr=="꽁치/명태/오징어") {
	   num = new Array("꽁치", "명태", "오징어");
	   vnum = new Array("꽁치", "명태", "오징어");
   }
   else if(fr=="새우/김") {
	   num = new Array("새우", "김");
	   vnum = new Array("새우", "김");
   }
   else if(fr=="굴/전복") {
	   num = new Array("굴", "전복");
	   vnum = new Array("굴", "전복");
   }
  // 특용작물
  else if(fr=="버섯") {
	   num = new Array("느타리버섯", "팽이버섯", "송이버섯");
	   vnum = new Array("느타리버섯", "팽이버섯", "송이버섯");
  }
  else if(fr=="땅콩/아몬드") {
	   num = new Array("땅콩", "아몬드");
	   vnum = new Array("땅콩", "아몬드");
  }
  else if(fr=="호두/참깨") {
	   num = new Array("호두", "참깨");
	   vnum = new Array("호두", "참깨");
  }
  
  
  ////////
  else
  {
   num = new Array("고르시오");
   vnum = new Array("고르시오");
  } 
  
  for(i=0; i<form.Step3.length; i++) {
   form.Step3.options[i] = null;
  }
  for(i=0; i<num.length; i++) {
   form.Step3.options[i] = new Option(num[i],vnum[i]);
  }
 }
</script>
 
<div>
<form name='form'>
<select name='Step1' onchange='changes1Step(value)'>
<option value="과일">과일</option>
<option value="채소">채소</option>
<option value="식량작물">식량작물</option>
<option value="수산물">수산물</option>
<option value="특용작물">특용작물</option>
</select>
<select name='Step2' onchange='changes2Step(value)'>
<option>중분류 ???</option>
</select>
<select name='Step3'>
<option>대분류 ???</option>
</select>
</form>
</div>
</html> 
</body>
</html>
