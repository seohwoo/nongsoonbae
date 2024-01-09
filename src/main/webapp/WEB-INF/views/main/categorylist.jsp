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
    <c:forEach var="cate" items="${menu}">
        <div style="margin-right: 10px;">
            <form action="/main/menu" method="post">
                <input type="hidden" name="cate1" value="${cate.cate1}" />
                <input type="hidden" name="cate2" value="${cate.cate2}" />
                <input type="hidden" name="cate3" value="${cate.cate3}" />

                <button type="submit">${cate.catename}</button>
            </form>
        </div>
    </c:forEach>
</div>
	</body>	
</html>















