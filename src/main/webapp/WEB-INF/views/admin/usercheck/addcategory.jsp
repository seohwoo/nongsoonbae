<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>물품 카테고리 추가하기</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        td {
            vertical-align: top;
            border: 1px solid #ddd;
            padding: 10px;
        }
        .scrollable {
            max-height: 200px; 
            overflow-y: auto; 
        }
    </style>
</head>
<body>
    <%@include file="/WEB-INF/views/admin/usercheck/usernav.jsp"%>
    <h1>물품별 카테고리 추가하기</h1>
    <table>
        <tr>
            <td>
                <h2>대분류</h2>
                <div class="scrollable">
                    <c:forEach var="cate" items="${catelist}">
                        <c:if test="${cate.cate2 == 0 && cate.cate3 == 0}">
                            ${cate.catename} <br/>
                        </c:if>
                    </c:forEach>
                </div>
            </td>
            <td>
                <h2>중분류</h2>
                <div class="scrollable">
                    <c:forEach var="cate" items="${catelist}">
                        <c:if test="${cate.cate2 > 0 && cate.cate3 == 0}">
                            ${cate.catename} <br/>
                        </c:if>
                    </c:forEach>
                </div>
            </td>
        </tr>
    </table>
    <h2>대분류 추가하기</h2>
    <form action="/admin/addCatePro" method="post" id="addCate" enctype="multipart/form-data"> 
        <input type="text" name="addCate" placeholder="대분류 추가하기">
        <input type="file" name="categoryImage" accept="image/*">
        <input type="submit" value="추가">
        <input type="hidden" value="${num}" name="num" />
    </form>
    
    <h2>중분류 추가하기</h2>
    <form id="cateSelectForm" action="/admin/addSubCate" method="post">
        <select id="cate1Select" name="cate1Select">
            <c:forEach var="cate" items="${catelist}">
                <c:if test="${cate.cate2 == 0 && cate.cate3 == 0}">
                    <option value="${cate.cate1}">${cate.catename}</option> 
                </c:if>
            </c:forEach>
        </select>
        <button id="submitBtn">선택하기</button>
    </form>
    
    <div id="subCateContainer" style="display:none;">
    	<jsp:include page="/WEB-INF/views/admin/usercheck/addSubCate.jsp" />
    </div>
    
    <script>
    $(document).ready(function() {
        var existingCateNames = [];
        <c:forEach var="cate" items="${catelist}">
            <c:if test="${cate.cate2 == 0 && cate.cate3 == 0}">
                existingCateNames.push("${cate.catename}");
            </c:if>
        </c:forEach>

        $('#addCate').on('submit', function(e) {
            var newCateName = $('input[name="addCate"]').val().trim();

            // 입력 필드가 비어 있을 경우
            if (!newCateName) {
                alert('항목을 입력해주세요.');
                e.preventDefault(); // 폼 제출 방지
                return;
            }

            // 중복 체크
            if (existingCateNames.includes(newCateName)) {
                alert('중복된 항목은 추가할 수 없습니다.');
                e.preventDefault(); // 폼 제출 방지
            } else {
                var numValue = parseInt($('input[name="num"]').val());
                $('input[name="num"]').val(numValue + 1);
                alert('대분류 카테고리가 정상적으로 추가되었습니다.');
            }
        });


        $('#submitBtn').on('click', function(e) {
            e.preventDefault();
            var cate1SelectValue = $('#cate1Select').val();
            $.ajax({
                url: '/admin/addSubCate',
                type: 'POST',
                data: $('#cateSelectForm').serialize(),
                success: function(response) {
                    $('#subCateContainer').html(response).show();
                },
                error: function(xhr, status, error) {
                    console.error("오류 발생: " + error);
                }
            });
        });
    });
    </script>
</body>
</html>


