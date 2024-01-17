<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원관리</title>
	</head>
	<body>
		<h1>일반 회원 목록 (${count} 명)</h1>
			<c:forEach var="list" items="${list}">
				<div class="notice">
					<input type="hidden" name="grade" value="${list.grade}">					    
						   <h2>${list.username}</h2>
						   <h3>${list.name}</h3>
					<input type="button" value="정지하기" onclick="toggleOptions(this)"/>
			        
			        <div class="options" style="display:none;">
			            <select>
			            	<option value="option">사유선택하기</option>
			                <option value="option1">부적절한 내용(폭력적,혐오적 댓글이나 게시글 or 스팸 게시)</option>
			                <option value="option2">부정행위(가짜 계정 생성, 부정 혜택 이용, 해킹 등)</option>
			                <option value="option3">사기 (거짓 정보 제공, 결제 사기, 물품 미배송)</option>
			                <option value="option4">결제 관련 (반복된 결제 실패, 카드 도용, 환불 규정 위반)</option>
			                <option value="option5">개인 정보 보호 위반 (다른 사용자의 정보를 무단으로 수집, 남용)</option>
			                <option value="option6">서비스 악용 (시스템의 취약점 이용, 서버 부하 유발하는 행위)</option>
			            </select>
			        </div>
					<div class="notice-meta">
							<p>이메일: ${list.email}</p>
					</div>
				</div>
			</c:forEach>	
				<div class="pagination">
				    <c:if test="${startPage > 10}">
				        <form action="/admin/userall" method="post">
				            <input type="hidden" name="pageNum" value="${startPage-10}">
				            <button type="submit">[이전]</button>
				        </form>
				    </c:if>
				    <c:forEach var="i" begin="${startPage}" end="${endPage}">
				        <form action="/admin/userall" method="post">
				            <input type="hidden" name="pageNum" value="${i}">
				            <button type="submit">[${i}]</button>
				        </form>
				    </c:forEach>
				    <c:if test="${endPage < pageCount}">
				        <form action="/admin/userall" method="post">
				            <input type="hidden" name="pageNum" value="${startPage+10}">
				            <button type="submit">[다음]</button>
				        </form>
				    </c:if>
	</body>
	<script>
    // 버튼을 클릭할 때 호출되는 함수
    function toggleOptions(button) {
        var optionsDiv = button.nextElementSibling;

        if (optionsDiv.style.display === "none") {
            optionsDiv.style.display = "block";
        } else {
            optionsDiv.style.display = "none";
        }
    }
</script>
	
	
	
</html>