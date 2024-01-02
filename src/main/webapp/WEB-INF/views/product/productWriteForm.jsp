<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>판매 등록 홈페이지</title>
	</head>
	
	<body>
		<h3>${username} 페이지</h3>
		<form action="/product/productWritePro" method="post">
			<table width="600" border="1">
				
				
				
				
				<tr>
					<td>
						<label for="productname">상품이름:</label>
					    <select name="productname" id="productname">
					        <option value="딸기">딸기</option>
					        <option value="배">배</option>
					        <option value="수박">수박</option>
					        <option value="복숭아">복숭아</option>
					    </select>
				    </td>
			    </tr>
			    
			    <tr> 
					<td width="200">가격</td>
					<td width="400"> 
						<input type="text" name="totalprice" size="15" required="required">
					</td>
			    </tr>
			    
			    <tr> 
					<td width="200">상품재고</td>
					<td width="400"> 
						<input type="text" name="productcount" size="15" required="required">
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
					<td width="200">옵션상태유무</td>
					<td width="400"> 
						<input type="text" name="optionstatus" size="15" required="required">
					</td>
			    </tr>
			    
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			    
			    <tr> 
					<td colspan="2" align="center"> 
						<input type="submit" name="confirm" value="상품등록" >
						<input type="reset" name="reset" value="다시입력">
						<input type="button" value="등록안함" onclick="javascript:window.location='/product/product'">
					</td>
				</tr>			    
			</table>	
		</form>
	</body>
</html>