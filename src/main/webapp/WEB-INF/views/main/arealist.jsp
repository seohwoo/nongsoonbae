<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
	<div style="display: flex;">
    <c:forEach var="area" items="${list}">
        <div style="margin-right: 10px;">
            <form action="/main/areamain" method="get">
                <input type="hidden" name="area1" value="${area.area1}" />
                <input type="hidden" name="area2" value="${area.area2}" />
                <button type="submit">${area.areaname}</button>
            </form>
        </div>
    </c:forEach>
	</div>
	</body>	
</html>
