<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Counter Example</title>
   	<script type="text/javascript" src="/resources/js/jquery-3.7.1.min.js"></script>
    <script>
        $(document).ready(function() {
            var cnt = 0;

            $('#increaseButton').click(function() {
                $.ajax({
                    type: 'POST',
                    url: '/test/increaseCount',
                    data: {
                    		cnt: cnt,
                    		chatno: 1
                    	},
                    success: function(response) {
                        cnt = parseInt(response);
                        $('#countDisplay').text(cnt);
                    }
                });
            });
        });
    </script>
</head>
<body>
    <h1>Counter</h1>
    <button id="increaseButton">Increase Count</button>
    <p>Count: <span id="countDisplay">0</span></p>
</body>
</html>
