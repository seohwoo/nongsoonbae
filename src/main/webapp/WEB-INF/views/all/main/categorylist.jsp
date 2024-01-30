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
    <c:forEach var="cate" items="${catelistdetail}">
        <div style="margin-right: 10px;">
            <form action="/nsb/menu" method="post">
                <input type="hidden" name="cate1" value="${cate.cate1}" />
                <input type="hidden" name="cate2" value="${cate.cate2}" />
                <button class="btn" type="submit">${cate.catename}</button>
            </form>
        </div>
    </c:forEach>
	</div>
		<div style="display: flex;">
			<c:if test="${cnt == 0 }" >
				   <h1> 없음 </h1>
			</c:if>
			</div>
			<div style="display: flex;">
			<c:if test="${cnt >  0 }" >
				    <c:forEach var="dto" items="${productlist}">
				    	<%@include file="/WEB-INF/views/all/main/listComponent.jsp"%> 
				    </c:forEach>  
			</c:if>	
		</div>
		<div>
			<jsp:include page="/WEB-INF/views/all/main/catelistDetail.jsp" />
		</div>
	</body>	
</html>















