<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<body>
    <form action="your_action_url" method="post"> 


        <select id="cate1Select" name="selectedCate1">
            <c:forEach var="cate1" items="${cate1}">
                <option value="${cate1}">${cate1}</option>
            </c:forEach>
        </select>
        <hr />

        <!-- Select for cate2 -->
        <label for="cate2Select">Select cate2:</label>
        <select id="cate2Select" name="selectedCate2">
            <c:forEach var="cate2" items="${cate2}">
                <option value="${cate2}">${cate2}</option>
            </c:forEach>
        </select>
        <hr />

        <!-- Select for cate3 -->
        <label for="cate3Select">Select cate3:</label>
        <select id="cate3Select" name="selectedCate3">
            <c:forEach var="cate3" items="${cate3}">
                <option value="${cate3}">${cate3}</option>
            </c:forEach>
        </select>
        <hr />

        <input type="submit" value="Submit">
    </form>
</body>
</html>
