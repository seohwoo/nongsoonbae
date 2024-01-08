<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>지역별</title>
	</head>
	<body>
	<h1> 지역별 카테고리</h1>
	<div style="display: flex;">
    <c:forEach var="dto" items="${area}">
        <div style="margin-right: 10px;">
           <form action="/main/areamain" method="get">
                <input type="hidden" name="area1" value="${dto.area1}" />
                <button type="submit">${dto.areaname}</button>
            </form>
        </div>
    </c:forEach>
	</div>
		<div style="display: flex;">
			<jsp:include page="/WEB-INF/views/main/arealist.jsp" />
		</div>	
		<div style="display: flex;">
			<jsp:include page="/WEB-INF/views/main/arearesult.jsp" />
		</div>	
	</body>
</html>

