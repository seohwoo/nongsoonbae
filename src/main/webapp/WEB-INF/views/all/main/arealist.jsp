<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	    <script>
	        $(document).ready(function() {
	            $('.uniqueAreaSelectForm').on('submit', function(e) {
	                e.preventDefault(); // 기본 폼 제출 동작 방지
	                var form = $(this);
	                var url = form.attr('action');
	                $.ajax({
	                    url: url,
	                    type: 'post',
	                    data: form.serialize(),
	                    success: function(response) {
	                        $('#resultDiv').html(response);
	                    },
	                    error: function(xhr, status, error) {
	                        console.error("오류 발생: " + error);
	                    }
	                });
	            });
	        });
	    </script>
	</head>
	<body>
	<div style="display: flex;">
	    <c:forEach var="area" items="${arealistdetail}">
	        <div style="margin-right: 10px;">
	            <form class="uniqueAreaSelectForm" action="/nsb/arearesult" method="post">
	            	<input type="hidden" name="areaNum" value="${areaNum}" />
	                <input type="hidden" name="area1" value="${area.area1}" />
	                <input type="hidden" name="area2" value="${area.area2}" />
	                <button type="submit">${area.areaname}</button>
	            </form>
	        </div>
	    </c:forEach>
	</div> 
		<c:if test="${areacnt > 0}">
		    <form action="/nsb/area" method="get">
		        <input type="hidden" name="areaNum" value="1"/>
		        <button type="submit">👈</button>
		    </form>
		    <form action="/nsb/area" method="get">
		        <input type="hidden" name="areaNum" value="2"/>
		        <button type="submit">👉</button>
		    </form>
		</c:if>
	<div style="display: flex;">
		<c:if test="${cnt == 0 }" >
			   <h1> 없음 </h1>
		</c:if>
	</div>
	<div style="display: flex;">
		<c:if test="${cnt >  0 }" >
			<c:forEach var="dto" items="${productlist}">
			      <h1>${dto.productname}</h1>
			</c:forEach>  
		</c:if>	
	</div>
	<div id="resultDiv">
		<jsp:include page="/WEB-INF/views/all/main/arearesult.jsp" />
	</div>
	</body>	
</html>
