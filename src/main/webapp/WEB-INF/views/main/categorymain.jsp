<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>카테고리별</title>
	</head>
	<body>
	<div style="display: flex;">
    <c:forEach var="dto" items="${dto}">
        <div style="margin-right: 10px;">
            <img src="${dto.img}" border="0" width="100" height="100"> <br />
           <form action="/main/menu" method="get">
                <input type="hidden" name="cate1" value="${dto.cate1}" />
                <input type="hidden" name="cate2" value="${dto.cate2}" />
                <input type="hidden" name="cate3" value="${dto.cate3}" />
                <button type="submit">${dto.catename}</button>
            </form>
        </div>
    </c:forEach>
</div>
		<div style="display: flex;">
			<jsp:include page="/WEB-INF/views/main/categorylist.jsp" />
		</div>
		<div style="display: flex;">
			<jsp:include page="/WEB-INF/views/main/catelistDetail.jsp" />
		</div>
	</body>
</html>

