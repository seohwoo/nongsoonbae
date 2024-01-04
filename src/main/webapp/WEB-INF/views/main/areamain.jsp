<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>지역별 검색하기</title>
    <script type="text/javascript">
        function openNewWindow() {
            // 새로운 창을 열기
            window.open('areafind', 'areafind', 'width=600,height=600');
        }
    </script>
</head>
<body>
    <h2>지역별로 상품 찾기</h2>
    <button onclick="openNewWindow()">검색</button>
    <h1>${areaname}</h1>
 	<jsp:include page="/WEB-INF/views/main/arearesult.jsp"/>
</body>
</html>
