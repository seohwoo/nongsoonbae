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
		<h1><a href="/chat/admin">➕Q&A</a></h1>
		<c:if test="${cnt==0}">
			<h1>아직 채팅이 없어요..</h1>
		</c:if>
		<c:if test="${cnt>0}">
			<table border="1">
				<tr>
					<td>채팅방명</td>
				</tr>
				<c:forEach var="dto" items="${chatList}">
					<tr>
						<td>
							<img src="/resources/file/profile/${dto.sendname_img}" width="100px" height="100px" />
							<br />
							<a href="room?chatno=${dto.chatno}">${dto.sendname_name}</a>
							<br />
							<c:if test="${dto.noread>0}">
								<span>${dto.noread}</span>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</body>
</html>