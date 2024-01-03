<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    
    <script>
        function populateCate2Options() {
            var cate1Select = document.getElementById("cate1Select");
            var cate2Select = document.getElementById("cate2Select");

            // Get the selected value from cate1Select
            var selectedCate1 = cate1Select.value;

            // Options for cate2 based on the selectedCate1
            var cate2Options;

            // Set the options for cate2 based on selectedCate1
            if (selectedCate1 === "과일") {
                cate2Options = ["감/딸기/토마토", "감귤/배/방울토마토", "사과/멜론/아보카도", "수박/레몬/망고", "참외/복숭아/바나나", "포도/오렌지/체리", "키위/파인애플"];
            } else {
                // Handle other cate1 values if needed
                cate2Options = [];
            }

            // Clear previous options
            cate2Select.innerHTML = "";

            // Add new options to cate2Select
            for (var i = 0; i < cate2Options.length; i++) {
                var option = document.createElement("option");
                option.value = cate2Options[i];
                option.text = cate2Options[i];
                cate2Select.add(option);
            }

            // Populate cate3 options based on the selected cate2
            populateCate3Options();
        }

        function populateCate3Options() {
            var cate2Select = document.getElementById("cate2Select");
            var cate3Select = document.getElementById("cate3Select");

            // Get the selected value from cate2Select
            var selectedCate2 = cate2Select.value;

            // Options for cate3 based on the selectedCate2
            var cate3Options;

            // Set the options for cate3 based on selectedCate2
            if (selectedCate2 === "감/딸기/토마토") {
                cate3Options = ["감", "딸기", "토마토"];
            } else {
                // Handle other cate2 values if needed
                cate3Options = [];
            }

            // Clear previous options
            cate3Select.innerHTML = "";

            // Add new options to cate3Select
            for (var i = 0; i < cate3Options.length; i++) {
                var option = document.createElement("option");
                option.value = cate3Options[i];
                option.text = cate3Options[i];
                cate3Select.add(option);
            }
        }
    </script>
</head>

<body>
    <form action="/product/product" method="post"> 

        <!-- cate1 -->
        <label for="cate1Select">Select cate1:</label>
        <select id="cate1Select" name="selectedCate1" onchange="populateCate2Options()">
            <c:forEach var="cate1" items="${cate1}">
                <option value="${cate1}">${cate1}</option>
            </c:forEach>
        </select>
        <hr />

        <!-- cate2 -->
        <label for="cate2Select">Select cate2:</label>
        <select id="cate2Select" name="selectedCate2">
            <!-- Options will be dynamically populated based on the selection of cate1 -->
        </select>
        <hr />

        <!-- cate3 -->
        <label for="cate3Select">Select cate3:</label>
        <select id="cate3Select" name="selectedCate3">
            <!-- Options will be dynamically populated based on the selection of cate2 -->
        </select>
        <hr />

        <input type="submit" value="Submit">
    </form>
</body>
</html>
