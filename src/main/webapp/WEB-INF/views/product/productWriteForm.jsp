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
						<select id="productname" name="productname">
				            <c:forEach var="cate1" items="${cate1}">
				                <option value="${cate1}">${cate1}</option>
				            </c:forEach>
				        </select>
        
				        <select id="productname" name="productname">
				            <c:forEach var="cate2" items="${cate2}">
				                <option value="${cate2}">${cate2}</option>
				            </c:forEach>
				        </select>

				        <select id="productname" name="productname">
				            <c:forEach var="cate3" items="${cate3}">
				                <option value="${cate3}">${cate3}</option>
				            </c:forEach>
				        </select>
			    
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
					<td width="200">사진</td>
					<td width="400"> 
						<input type="text" name="imagecount" size="15" required="required">
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
			    
			    <input type="hidden" name="username" value="${username}" />
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