<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>room</title>
		<script type="text/javascript" src="/resources/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/resources/js/socket.io.js"></script>
		<script type="text/javascript">
			$(function () {
				var socket = io.connect("http://192.168.219.149:9999")	
					
				// 채팅 내용 받는 부분
				socket.on("response", function (message) {
					$("#msgs").append(message.msg);	// append 추가
				})			
				// 채팅 내용 보내는 부분				
				$("#sendBtn").on("click", function () {
					var m = $("#chat").val();
					if(m !== "") {
						socket.emit("chatMsg", {msg : '${username}' + " : " + m +"<br />" 
							, username : '${username}', sendname : '${sendname}', num : '${num}'});	// 보내기
					}
				})
			})
			$(document).ready(function(){
		        $('#sendBtn').click(function(){
		            $('#chat').val('');	// chat input의 값을 지움
		        });
		    });
			
			document.getElementById('sendBtn').addEventListener('click', function() {
			    var numValue = ${num};

			    var xhr = new XMLHttpRequest();
			    xhr.open("POST", "MessageChat", true);
			    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			    xhr.send("num=" + encodeURIComponent(numValue));
			});
		</script>
	</head>
	<body>
		<h1>chat..!!${num}</h1>
		<div class="fixed-element">
			<input type="text" name="chat" id="chat"/>
			<input type="button" value="send" id="sendBtn" />
		</div>
		<hr color="red"/>
		<div id="msgs">
			<span>==chat start==</span> 
			<br />
			<span>${chat}</span>
		</div>
	</body>
</html>