<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>지역검색</title>
    <script type="text/javascript">
        function refreshParentAndClose() {
            // 선택된 값을 가져오기
            var selectedRadio = document.querySelector('input[name="area1"]:checked');
            
            
            if (selectedRadio) {
                var selectedValue = selectedRadio.value;
                document.getElementById('selectedValue').value = selectedValue;
                document.forms["areaForm"].submit();

                // 부모 창 새로고침
                window.opener.location.href = '/main/areamain?selectedValue=' + encodeURIComponent(selectedValue);
                // 현재 창 닫기
                window.close();
            } else {
                alert("지역을 선택해주세요."); // 사용자에게 메시지 표시
            }
        }
    </script>
</head>
<body>
    <form method="post" action="/main/areamain" id="areaForm">
        <c:forEach var="item" items="${list}">
            <input type="radio" name="area1" value="${item.area1}" id="${item.areaname}">
            <label for="${item.areaname}">${item.areaname}</label><br>
        </c:forEach>
        <input type="hidden" name="selectedValue" id="selectedValue">
        <button type="button" onclick="refreshParentAndClose()">찾기</button>
    </form>
</body>
</html>
