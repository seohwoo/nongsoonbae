<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <style type="text/css">
      .custom-btn {
          width: 100%;
          height: 40px;
          color: #fff;
          border-radius: 5px;
          padding: 10px 25px;
          font-family: 'Lato', sans-serif;
          font-weight: 500;
          background: transparent;
          cursor: pointer;
          transition: all 0.3s ease;
          position: relative;
          display: inline-block;
           box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
           7px 7px 20px 0px rgba(0,0,0,.1),
           4px 4px 5px 0px rgba(0,0,0,.1);
          outline: none;
        }
      .btn-5 {
          width: 100%;
          height: 40px;
          line-height: 42px;
          padding: 0;
          border: none;
          background: rgb(255,27,0);
        background: linear-gradient(0deg, rgba(255,27,0,1) 0%, rgba(251,75,2,1) 100%);
        }
        .btn-5:hover {
          color: #f0094a;
          background: transparent;
           box-shadow:none;
        }
        .btn-5:before,
        .btn-5:after{
          content:'';
          position:absolute;
          top:0;
          right:0;
          height:2px;
          width:0;
          background: #f0094a;
          box-shadow:
           -1px -1px 5px 0px #fff,
           7px 7px 20px 0px #0003,
           4px 4px 5px 0px #0002;
          transition:400ms ease all;
        }
        .btn-5:after{
          right:inherit;
          top:inherit;
          left:0;
          bottom:0;
        }
        .btn-5:hover:before,
        .btn-5:hover:after{
          width:100%;
          transition:800ms ease all;
        }
   </style>
</head>
<body>
   <div style="height: 300px;">
   버튼테스트중
   </div>
   
   <div style="height : 100px; width : auto; overflow: scroll;">
      <div class="btn-group-vertical">
         <button type="button" class="custom-btn btn-5" id="button">질문드립니다아아아아아아아아아아</button>
         <button type="button" class="custom-btn btn-5" id="button">여보세요오오오오오오오오오오오오</button>
         <button type="button" class="custom-btn btn-5" id="button">이렇게 저렇게 하면 됩니다 굿~~~</button>
         <button type="button" class="custom-btn btn-5" id="button">주문 후 취소 및 환불은 어렵습니다!!!!</button>
         <button type="button" class="custom-btn btn-5" id="button">문의 전 고객센터 자주묻는질문 게시판을 확인해주세용</button>
         <button type="button" class="custom-btn btn-5" id="button">주문해주셔서 감사합니당당당당당당</button>
      </div>
   </div>
</body>