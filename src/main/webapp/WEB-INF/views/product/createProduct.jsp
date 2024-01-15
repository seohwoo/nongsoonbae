<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h3>${username} 페이지</h3>
		<form action="/product/createProductPro" method="post" name="createProductPro">
			<table width="850" border="1">

			    <tr> 
					<td width="200">상점이름</td>
					<td width="400"> 
						<input type="text" name="shopname" size="15" required="required">
					</td>
			    </tr>
			    
			    <tr> 
					<td width="200">상점소개</td>
					<td width="400"> 
						<input type="text" name="shopcontent" required="required">
					</td>
			    </tr>
			    
			    <tr> 
					<td width="200">상점주소</td>
					<td width="400"> 
						<input type="text" name="address" size="15" required="required">
					</td>
			    </tr>
			    
			    <input type="hidden" name="username" value="${username}" />
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			    
			    <tr> 
					<td colspan="2" align="center"> 
						<input type="submit" value="나의 상점 만들기" >
						<input type="reset" name="reset" value="다시입력">
						<input type="button" value="등록안함" onclick="javascript:window.location='/product/product'">
					</td>
				</tr>			    
			</table>	
		</form>
	</body>
</html>