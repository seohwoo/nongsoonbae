
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TEST SAMPLE</title>
</head>
<script>
 function changes1Step(fr) {
  if(fr=="용병") {
   num = new Array("--2단계--", "소드맨","아처","매지션");
   vnum = new Array("--2단계--", "소드맨","아처", "매지션");
  }
  else if(fr=="아이템") {
   num = new Array("--2단계--", "무기","방어구","장신구", "잡화", "합성석");
   vnum = new Array("--2단계--", "무기","방어구","장신구", "잡화", "합성석");
  }
  
  for(i=0; i<form.Step2.length; i++) {
   form.Step2.options[i] = null;
  }
  
  for(i=0; i<form.Step3.length; i++) {
   form.Step3.options[i] = null;
  }
   form.Step3.options[0] = new Option("--3단계--", "--3단계--");
  for(i=0; i<num.length; i++) {
   form.Step2.options[i] = new Option(num[i],vnum[i]);
  }
 }
 
 
 function changes2Step(fr) {
  //용병 선택
  if(fr=="소드맨") {
   num = new Array("--3단계--", "버서커", "팔라딘");
   vnum = new Array("--3단계--", "버서커", "팔라딘");
  }
  else if(fr=="아처") {
   num = new Array("--3단계--", "헌터", "레인저");
   vnum = new Array("--3단계--", "헌터", "레인저");
  }
  else if(fr=="매지션") {
   num = new Array("--3단계--", "소서러","위자드");
   vnum = new Array("--3단계--", "소서러","위자드");
  }
  //아이템 선택
  else if(fr=="무기") {
   num = new Array("--3단계--", "검","활","지팡이");
   vnum = new Array("--3단계--", "검","활", "지팡이");
  }
  else if(fr=="방어구") {
   num = new Array("--3단계--", "갑옷","투구","장갑", "신발");
   vnum = new Array("--3단계--", "갑옷","투구","장갑", "신발");
  }
  else if(fr=="장신구") {
   num = new Array("--3단계--", "반지","목걸이");
   vnum = new Array("--3단계--", "반지","목걸이");
  }
  else if(fr=="잡화") {
   num = new Array("--3단계--", "");
   vnum = new Array("--3단계--", "");
  }
  else if(fr=="합정석") {
   num = new Array("--3단계--", "");
   vnum = new Array("--3단계--", "");
  }
  else
  {
   num = new Array("--3단계--");
   vnum = new Array("--3단계--");
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
<option>--1단계--</option>
<option value="용병">용병</option>
<option value="아이템">아이템</option>
</select>
<select name='Step2' onchange='changes2Step(value)'>
<option>--2단계--</option>
</select>
<select name='Step3'>
<option>--3단계--</option>
</select>
</form>
</div>
</html> 
</body>
</html>