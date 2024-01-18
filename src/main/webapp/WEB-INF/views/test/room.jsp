<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>room</title>
		<link href="/resources/css/chat.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="/resources/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/resources/js/socket.io.js"></script>
		<script type="text/javascript">

			$(function () {
				function get(selector, root = document) {
					  return root.querySelector(selector);
				}
				const msgerChat = get(".msger-chat");
				// 문서 전체의 스크롤바를 가장 아래로 이동
				var socket = io.connect("http://192.168.219.149:9999");
				msgerChat.scrollTop = msgerChat.scrollHeight;
				socket.emit("joinRoom", { username: '${username}', sendname: '${sendname}' });
				socket.on("response", function (message) {
					var arr = message.msg.split(',');
					var side = "left";
					// 마지막 빈 문자열 제거
					if (arr[arr.length - 1] === '') {
					  arr.pop();
					}
					if(arr[0] === `${username}`) {
						side = "right";
					}
					var msgHTML =
						  '<div class=\'msg ' + side + '-msg\'>' +
						  '  <div class=\'msg-img\' style=\'background-image: url()\'></div>' +
						  '' +
						  '  <div class=\'msg-bubble\'>' +
						  '    <div class=\'msg-info\'>' +
						  '      <div class=\'msg-info-name\'>' + arr[0] + '</div>' +
						  '      <div class=\'msg-info-time\'>' + arr[2] + '</div>' +
						  '    </div>' +
						  '    <div class=\'msg-text\'>' + arr[1] + '</div>' +
						  '  </div>' +
						  '</div>';
					$("#msgs").append(msgHTML);
					msgerChat.scrollTop = msgerChat.scrollHeight;
					
				});
				$("#sendBtn").on("click", function () {
					var m = $("#chat").val();
					if (m !== "") {
						var currentDate = new Date();
						var options = {
						  hour: '2-digit',
						  minute: '2-digit',
						  hour12: true, // 12시간 형식
						};
						var formattedTime = new Intl.DateTimeFormat('ko-KR', options).format(currentDate);
						msgerChat.scrollTop = msgerChat.scrollHeight;
						socket.emit("chatMsg", { msg: '${username}' + "," + m + "," + formattedTime +"," , username: '${username}', sendname: '${sendname}' });
					}
				});
				$(document).ready(function () {
					$('#sendBtn').click(function () {
						$('#chat').val('');
					});
				});
			});
		</script>
	</head>
	<body>
		<section class="msger">
		  <header class="msger-header">
		    <div class="msger-header-title">
		      <i class="fas fa-comment-alt"></i> ${sendname}
		    </div>
		    <div class="msger-header-options">
		      <span><i class="fas fa-cog"></i></span>
		    </div>
		  </header>
			<main class="msger-chat" id="msgs">
				${chat}
			</main>
			<div class="msger-inputarea">
			    <input type="text" class="msger-input" name="chat" id="chat" placeholder="Enter your message...">
			    <button type="submit" id="sendBtn" class="msger-send-btn">Send</button>
			</div>    
		</section>
	</body>
</html>
