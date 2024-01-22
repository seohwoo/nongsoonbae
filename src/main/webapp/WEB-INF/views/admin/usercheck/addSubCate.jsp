<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
	<h3>${showSelectCate1} 의 기존 분류 항목 (기존에 있는 항목은 추가할 수 없습니다. 두개 항목 이상의 물품을 정해서 넣어주세요. ex. 키위/파인애플)</h3> <br />
		<c:forEach var="subCate" items="${subcatelist}">
	    ${subCate.catename} <br/>
		</c:forEach>
		<h3>중분류에 해당하는 각각의 품목을 입력해주세요</h3>
		<form action="/admin/addSubCatePro" method="post" id="addSubCate" enctype="multipart/form-data"> 
	        <input type="text" name="addSubCate" placeholder="중분류 추가하기">
	        <input type="submit" value="추가">
   			<input type="text" name="addCate1" placeholder="품목적기">
        	<input type="file" name="categoryImage1" accept="image/*">
        	<input type="text" name="addCate2" placeholder="품목적기">
        	<input type="file" name="categoryImage2" accept="image/*">
   	 	</form>	
	</body>
</html>