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
				var socket = io.connect("http://${ip}:9999");
				var cnt = '${sendnoread}';
				var joincnt = '${dto.isjoin}';
				console.log('시작 : '+joincnt);
				msgerChat.scrollTop = msgerChat.scrollHeight;
				socket.emit("joinRoom", { username: '${dto.username}', sendname: '${dto.sendname}', chatno : '${dto.chatno}' });
				socket.on("join", function (join) {
					cnt=0;
					if(joincnt <= 2) {
						$.ajax({
		                    type: 'POST',
		                    url: '/test/updateJoin',
		                    data: {
		                    		joincnt: joincnt,
		                    		chatno: '${dto.chatno}'
		                    	},
			                success: function(response) {
			                	joincnt = parseInt(response);
								console.log('들어옴 : '+joincnt);
			                } 	
		                });
					}
				});
				$(window).on('beforeunload', function() {
					socket.emit("outRoom", { username: '${dto.username}', sendname: '${dto.sendname}', chatno : '${dto.chatno}' });
				});
				socket.on("out", function (out) {
					$.ajax({
	                    type: 'POST',
	                    url: '/test/updateOut',
	                    data: {
	                    		joincnt: joincnt,
	                    		chatno: '${dto.chatno}'
	                    	},
		                success: function(response) {
		                	joincnt = parseInt(response);
							console.log('나감 : '+joincnt);
		                } 	
	                });	
				});
				socket.on("response", function (message) {
					var arr = message.msg.split(',');
					var side = "left";
					var img = '${dto.sendname_img}';
					// 마지막 빈 문자열 제거
					if (arr[arr.length - 1] === '') {
					  arr.pop();
					}
					if(arr[0] === `${dto.username_name}`) {
						side = "right";
						img = '${dto.username_img}';
					}
					var msgHTML =
						  '<div class=\'msg ' + side + '-msg\'>' +
						  '  <div class=\'msg-img\' style=\'background-image: url(/resources/file/profile/'+img+')\'></div>' +
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
						socket.emit("chatMsg", { msg: '${dto.username_name}' + "," + m + "," + formattedTime +"," , username: '${dto.username}', sendname: '${dto.sendname}', chatno : '${dto.chatno}' });
						if(joincnt < 2) {
			                $.ajax({
			                    type: 'POST',
			                    url: '/test/updateCount',
			                    data: {
			                    		cnt: cnt+1,
			                    		chatno: '${dto.chatno}',
			                    		username: '${dto.username}'
			                    	},
				                success: function(response) {
				                	cnt = parseInt(response);
				                } 	
			                });
		            	}
					}
				});
				$(document).ready(function () {
					$('#sendBtn').click(function () {
						$('#chat').val('');
					});
				});
				$(window).on('beforeunload', function (e) {
			        var isReloading = performance.navigation.type === 1;
			        if (isReloading) {
			        	socket.on("out", function (out) {
							$.ajax({
			                    type: 'POST',
			                    url: '/test/updateOut',
			                    data: {
			                    		joincnt: joincnt,
			                    		chatno: '${dto.chatno}'
			                    	},
				                success: function(response) {
				                	joincnt = parseInt(response);
									console.log('나감 : '+joincnt);
				                } 	
			                });	
						});
			        }
			    });
			});
		</script>
	</head>
	<body>
		<section class="msger">
		  <header class="msger-header">
		    <div class="msger-header-title">
		      <i class="fas fa-comment-alt"></i> ${dto.sendname_name}
		    </div>
		    <div class="msger-header-options">
		      <a href="/test/roomList">❌</a>
		    </div>
		  </header>
			<main class="msger-chat" id="msgs">
				${chat}
			</main>
			<div class="msger-inputarea">
			    <input type="text" class="msger-input" name="chat" id="chat" placeholder="메세지를 입력하세요...">
			    <button type="submit" id="sendBtn" class="msger-send-btn">전송</button>
			</div>    
		</section>
	</body>
</html>



