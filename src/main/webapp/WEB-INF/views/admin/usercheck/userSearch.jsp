<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <script type="text/javascript">
            function validateForm() {
                var searchInput = document.forms["searchForm"]["userSearch"].value;
                if (searchInput == "") {
                    alert("사용자의 ID 혹은 이름을 입력해주세요.");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <form name="searchForm" action="/admin/searchResult" method="get" onsubmit="return validateForm()">
            <input type="text" name="userSearch" placeholder="회원 검색하기">
            <input type="submit" value="검색">
        </form>
    </body>
</html>
