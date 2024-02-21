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
	<div style="width: 100%;">
		<div class="notice success">
			<h1><a href="/chat/admin">농순배 고객센터</a></h1>
		</div>
		<br />
		<c:if test="${cnt==0}">
			<h1>아직 채팅이 없어요..</h1>
		</c:if>
		<c:if test="${cnt>0}">
			<table style="width: 100%;">
				<tr>
					<td><h3 style="align-items: center; padding: 10px;">채팅방 리스트</h3></td>
				</tr>
				<c:forEach var="dto" items="${chatList}">
					<tr>
						<td>
							<div class="roomcell">
								<img src="/resources/file/profile/${dto.sendname_img}" width="100px" height="100px" />
								<a href="room?chatno=${dto.chatno}" style="font-size: 25px;">${dto.sendname_name}</a>
								<br />
								<c:if test="${dto.noread>0}">
									<span>${dto.noread}</span>
								</c:if>
							</div>
						</td>
					</tr>
					<br />
				</c:forEach>
			</table>
		</c:if>
	</div>
	</body>
</html>