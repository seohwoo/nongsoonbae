<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var pageNum =${categoryNum};
    function updatePageNum(status) {
        $.ajax({
            type: 'POST',
            url: '/nsb/pageNum',
            data: {
                pageNum: pageNum,
                status: status
            },
            success: function(response) {
                pageNum = parseInt(response);
            }    
        });
    }
</script>     
<c:if test="${pageNum>1}">
    <button class="bton" id="prev" onclick="updatePageNum(0)">⏪</button>
</c:if>
<c:if test="${pageNum==1 || pageNum>=maxCategoryNum}">
    <button class="bton" onclick="window.location='#'">⏸</button>
</c:if>
<c:if test="${pageNum<maxCategoryNum}">
    <button class="bton" id="next" onclick="updatePageNum(1)">⏩</button>
</c:if>