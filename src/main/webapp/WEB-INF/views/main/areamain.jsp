<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>지역별</title>
		<script type="text/javascript">
            function checkAndRedirect(area1Value) {
                if (area1Value === '0') {
                    window.location.href = '/main/areamain';
                    return false; 
                }
                return true; // 폼 제출 허용
            }
        </script>
	</head>
	<body>
	<h1> 지역별 카테고리</h1>
	<div style="display: flex;">
			<form action="/main/areamain" method="get" >
        		<button type="submit">전체</button>
        	</form>
    <c:forEach var="dto" items="${arealist}">
        <div style="margin-right: 10px;">
           <form action="/main/areamain" method="get" onsubmit="return checkAndRedirect('${dto.area1}')">
           		<input type="hidden" name="areaNum" value="${areaNum}" />
                <input type="hidden" name="area1" value="${dto.area1}" />
                <button type="submit">${dto.areaname}</button>
            </form>
        </div>
    </c:forEach>  
	</div>
		<c:if test="${count > 0}">
		    <form action="/main/areamain" method="post">
		        <input type="hidden" name="areaNum" value="1"/>
		        <button type="submit">👈</button>
		    </form>
		    <form action="/main/areamain" method="get">
		        <input type="hidden" name="areaNum" value="2"/>
		        <button type="submit">👉</button>
		    </form>
		</c:if>
		
		
		<c:if test="${allCnt >  0 }" >
			    <c:forEach var="dto" items="${allprocuctList}">
			            <h1>${dto.productname}</h1>
			     </c:forEach>
		</c:if>		
		<div>
			<jsp:include page="/WEB-INF/views/main/arealist.jsp" />
		</div>	
		<div >
			<jsp:include page="/WEB-INF/views/main/arearesult.jsp" />
		</div>	
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>

