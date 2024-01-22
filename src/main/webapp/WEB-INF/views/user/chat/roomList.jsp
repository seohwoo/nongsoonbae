<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>채팅방목록</title>
		<link href="/resources/css/chat.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<table border="1">
			<tr>
				<td>채팅방명</td>
			</tr>
			<c:forEach var="dto" items="${chatList}">
				<tr>
					<td>
						<div class='msg-img' style='background-image: url(/resources/file/profile/${dto.sendname_img})'></div>
						<a href="room?sendname=${dto.sendname}&chatno=${dto.chatno}">${dto.sendname_name}</a>
						<br />
						<c:if test="${dto.noread>0}">
							<span>${dto.noread}</span>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>