<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<script type="text/javascript">
			const crypto = require('crypto');
			const hash = crypto.createHmac('sha256', Buffer.from("${secretKey}", 'hex'))
	        .update("${dto.email}")
	        .digest('hex');
		</script>
		<script>
		  (function(){var w=window;if(w.ChannelIO){return w.console.error("ChannelIO script included twice.");}var ch=function(){ch.c(arguments);};ch.q=[];ch.c=function(args){ch.q.push(args);};w.ChannelIO=ch;function l(){if(w.ChannelIOInitialized){return;}w.ChannelIOInitialized=true;var s=document.createElement("script");s.type="text/javascript";s.async=true;s.src="https://cdn.channel.io/plugin/ch-plugin-web.js";var x=document.getElementsByTagName("script")[0];if(x.parentNode){x.parentNode.insertBefore(s,x);}}if(document.readyState==="complete"){l();}else{w.addEventListener("DOMContentLoaded",l);w.addEventListener("load",l);}})();
		
		  ChannelIO('boot', {
		    "pluginKey": "${pluginKey}",
		    "memberId": "{$logged_info->user_id}",
		    "profile": {
		      "name": "{$logged_info->user_name}",
		      "email": "{$logged_info->email_address}"
		    }
		  });
		</script>
	</body>
</html>