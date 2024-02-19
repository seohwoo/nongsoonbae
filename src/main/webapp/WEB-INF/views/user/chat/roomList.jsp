<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="/resources/css/chat.css" rel="stylesheet" type="text/css">
		<title>채팅방목록</title>
	</head>
	<body>
	<div>
		<h1><a href="/chat/admin">➕Q&A</a></h1>
		<br />
		<c:if test="${cnt==0}">
			<h1>아직 채팅이 없어요..</h1>
		</c:if>
		<c:if test="${cnt>0}">
			<table>
				<tr>
					<td><h3 style="align-items: center; padding: 10px;">채팅방 리스트</h3></td>
				</tr>
				<c:forEach var="dto" items="${chatList}">
					<tr>
						<td>
							<div class="roomcell">
								<img src="/resources/file/profile/${dto.sendname_img}" width="100px" height="100px" />
								<br />
								<a href="room?chatno=${dto.chatno}">${dto.sendname_name}</a>
								<br />
								<c:if test="${dto.noread>0}">
									<span>${dto.noread}</span>
								</c:if>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	</body>
</html>