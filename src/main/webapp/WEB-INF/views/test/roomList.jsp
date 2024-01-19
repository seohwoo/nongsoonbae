<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>채팅방목록</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<td>채팅방명</td>
			</tr>
			<c:forEach var="dto" items="${chatList}">
				<tr>
					<td>
						<img src="/resources/img/${dto.sendname_img}" width="100px" height="100px">
						<a href="room?sendname=${dto.sendname}&chatno=${dto.chatno}">${dto.sendname_name}</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>