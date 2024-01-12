<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>카테고리별</title>
		<script type="text/javascript">
            function checkAndRedirect(cate1Value) {
                if (cate1Value === '0') {
                    window.location.href = '/nsb/menu';
                    return false; 
                }
                return true; // 폼 제출 허용
            }
        </script>
	</head>
	<body>
		<%@include file="/WEB-INF/views/include/header.jsp"%>
			<h1>카테고리</h1>
			<div style="display: flex;">
		    <c:forEach var="dto" items="${catelist}">
		        <div style="margin-right: 10px;">
		           <form action="/nsb/menu" method="get" onsubmit="return checkAndRedirect('${dto.cate1}')">
			            <input type="hidden" name="cate1" value="${dto.cate1}" />
		 	            <button type="submit">
			 	            <img src="${dto.img}" border="0" width="100" height="100">
			 	            <br />
			                <span>${dto.catename}</span>
		                </button>
		            </form>
		        </div>
		    </c:forEach>
			</div>
			<c:if test="${allCnt >  0 }" >
				<c:forEach var="dto" items="${allprocuctList}">
					<h1>${dto.productname}</h1>
				</c:forEach>
			</c:if>	
			<div>
				<jsp:include page="/WEB-INF/views/all/main/categorylist.jsp" />
			</div>	
			<div>
				<jsp:include page="/WEB-INF/views/all/main/catelistDetail.jsp" />
			</div>
			<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>

