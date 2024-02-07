<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 광고</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            line-height: 1.0; /* 줄 간격 조정 */
        }

        .content-box {
            border: 2px solid #ddd; /* 테두리 추가 */
            padding: 20px; /* 패딩 추가 */
            margin: 20px auto; /* 상하 마진 추가 및 좌우 자동 */
            width: 80%; /* 너비 설정 */
            background-color: white; /* 박스 배경색 */
        }

        h2, h3, h4, h5 {
            color: #333;
        }

        h2 {
            margin-top: 50px;
        }

        h3, h4, h5 {
            margin-top: 20px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 20px 0;
            cursor: pointer;
            border: none;
            border-radius: 5px;
        }

        button:hover {
            background-color: #45a049;
        }
        .highlight-box { /* 새로운 박스 스타일 */
        border: 2px solid #ddd;
        padding: 20px;
        margin: 20px auto;
        width: 60%;
        background-color: #FFFFE4;
        box-shadow: 0px 0px 10px #ccc; /* 박스에 그림자 추가 */
    }
    </style>
	<script type="text/javascript">
        // "나의 광고 보기" 버튼 클릭 시 호출될 함수
        function viewMyAds() {
            window.location.href = '/product/myAd'; // 지정된 경로로 이동
        }
    </script>
</head>
<body>
    <div class="content-box"> 
        <h2>📰내 상품 광고하기!📰</h2><br />
        <h3>일주일에 7만원만 내면 내 상품이 최상단에 노출되어 판매자들이 제일 먼저 상품을 확인할 수 있습니다!</h3><br />
        <div class="highlight-box"> 
            <h4>💚 적은 비용으로 내 상품 매출 올리고 싶을 때 </h4><br />
            <h4>💚 상품 등록한지 오래지나 노출이 잘 안될 때</h4><br />
            <h4>💚 판매량이 저조해 고민일 때 </h4><br />
        </div>
        <h5>광고 등록 과정 : 판매자 신청 폼 작성 - 관리자 승인 - 승인 후 바로 판매자가 등록한 광고 일수 만큼 광고 시작</h5> <br />
        <h5>광고 상품은 사이트의 광고 일정에 따라 신청이 반려될 수 있음을 알립니다. </h5> <br />
        <form action="/user/adForm" method="get">
            <input type="hidden" name="userId" value="${username}" /> 
            <button type="submit">신청하기</button>
        </form>
        <button onclick="viewMyAds()">나의 광고 보기</button>
    </div>
</body>
</html>
