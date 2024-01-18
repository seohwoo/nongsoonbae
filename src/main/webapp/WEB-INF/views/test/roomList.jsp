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
					<td><a href="room?sendname=${dto.sendname}&num=${dto.num}">${dto.sendname}</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>