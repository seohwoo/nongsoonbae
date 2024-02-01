<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>room</title>
      <link href="/resources/css/chat.css" rel="stylesheet" type="text/css">
      <script type="text/javascript" src="/resources/js/jquery-1.10.2.min.js"></script>
      <script type="text/javascript" src="/resources/js/socket.io.js"></script>
      <script type="text/javascript">

         $(function () {
            function get(selector, root = document) {
                 return root.querySelector(selector);
            }
            const msgerChat = get(".msger-chat");
            // 문서 전체의 스크롤바를 가장 아래로 이동
            var socket = io.connect("http://${ip}:9898");
            var cnt = '${sendnoread}';
            var joincnt = '${dto.isjoin}';
            console.log('시작 : '+joincnt);
            msgerChat.scrollTop = msgerChat.scrollHeight;
            socket.emit("joinRoom", { username: '${dto.username}', sendname: '${dto.sendname}', chatno : '${dto.chatno}' });
            socket.on("join", function (join) {
               cnt=0;
               if(joincnt <= 2) {
                  $.ajax({
                          type: 'POST',
                          url: '/test/updateJoin',
                          data: {
                                joincnt: joincnt,
                                chatno: '${dto.chatno}'
                             },
                         success: function(response) {
                            joincnt = parseInt(response);
                        console.log('들어옴 : '+joincnt);
                         }    
                      });
               }
            });
            $(window).on('beforeunload', function() {
               socket.emit("outRoom", { username: '${dto.username}', sendname: '${dto.sendname}', chatno : '${dto.chatno}' });
            });
            socket.on("out", function (out) {
               $.ajax({
                       type: 'POST',
                       url: '/test/updateOut',
                       data: {
                             joincnt: joincnt,
                             chatno: '${dto.chatno}'
                          },
                      success: function(response) {
                         joincnt = parseInt(response);
                     console.log('나감 : '+joincnt);
                      }    
                   });   
            });
            socket.on("response", function (message) {
               var arr = message.msg.split(',');
               var side = "left";
               var img = '${dto.sendname_img}';
               // 마지막 빈 문자열 제거
               if (arr[arr.length - 1] === '') {
                 arr.pop();
               }
               if(arr[0] === `${dto.username_name}`) {
                  side = "right";
                  img = '${dto.username_img}';
               }
               var msgHTML =
                    '<div class=\'msg ' + side + '-msg\'>' +
                    '  <div class=\'msg-img\' style=\'background-image: url(/resources/file/profile/'+img+')\'></div>' +
                    '' +
                    '  <div class=\'msg-bubble\'>' +
                    '    <div class=\'msg-info\'>' +
                    '      <div class=\'msg-info-name\'>' + arr[0] + '</div>' +
                    '      <div class=\'msg-info-time\'>' + arr[2] + '</div>' +
                    '    </div>' +
                    '    <div class=\'msg-text\'>' + arr[1] + '</div>' +
                    '  </div>' + 
                    '</div>';
               $("#msgs").append(msgHTML);
               msgerChat.scrollTop = msgerChat.scrollHeight;
               
            });
            $("#sendBtn").on("click", function () {
               var m = $("#chat").val();
               if (m !== "") {
                  var currentDate = new Date();
                  var options = {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: true, // 12시간 형식
                  };
                  var formattedTime = new Intl.DateTimeFormat('ko-KR', options).format(currentDate);
                  msgerChat.scrollTop = msgerChat.scrollHeight;
                  socket.emit("chatMsg", { msg: '${dto.username_name}' + "," + m + "," + formattedTime +"," , username: '${dto.username}', sendname: '${dto.sendname}', chatno : '${dto.chatno}' });
                  if(joincnt < 2) {
                         $.ajax({
                             type: 'POST',
                             url: '/test/updateCount',
                             data: {
                                   cnt: cnt+1,
                                   chatno: '${dto.chatno}',
                                   username: '${dto.username}'
                                },
                            success: function(response) {
                               cnt = parseInt(response);
                            }    
                         });
                     }
               }
            });
            $(document).ready(function () {
               $('#sendBtn').click(function () {
                  $('#chat').val('');
               });
            });
            
            <!-- 버튼에 대한 조작 -->
			$("#button1").on("click", function () {
				if('${isAdmin}') {
					side = "left";
					var img = '${dto.sendname_img}';
					var m1 = '1. 로그인 / 개인정보수정 / 탈퇴 / 정지 <br />'
					+ '★로그인이 되지 않는 경우는 다음 사항을 확인하시기 바랍니다.<br />'
					+ '아이디 비밀번호 확인 <br />'
					+ '로그인이 안 될 경우에는 “로그인” 페이지에 있는 아이디 찾기 또는 비밀번호 찾기에서 아이디와 비밀번호를 확인하여 주십시오. <br />'
					+ '★ 웹브라우저 확인사항 체크 <br />'
					+ '아이디, 비밀번호가 일치하지만 로그인이 되지 않을 경우에는 PC에서 웹브라우저의 확인사항을 체크해 주십시오. <br />'
					+ '★ 회원 탈퇴 하기 <br />'
					+ '계정을 탈퇴하고 싶은 경우 로그인 후 "마이페이지"에 가서 탈퇴할 수 있습니다. <br />'
					+ '탈퇴가 완료 된 계정은 다시 복구할 수 없으니 유의하시길 바랍니다. <br />'
					+ '★ 개인정보수정 <br />'
					+ '비밀번호를 바꾸고 싶거나 개인 정보를 수정/추가하고 싶은 경우 "로그인-마이페이지" 에서 직접 수정이 가능합니다. <br />'
					+ '★ 정지 된 회원 <br />'
					+ '일종의 이유로 관리자가 일시 정지된 계정입니다. 계정 사유를 알고 싶은 경우 1:1 관리자 채팅을 통해 문의해 주시길 바랍니다. <br />'
					;
					var currentDate = new Date();
					var options = {
					  hour: '2-digit',
					  minute: '2-digit',
					  hour12: true, // 12시간 형식
					};
					var formattedTime = new Intl.DateTimeFormat('ko-KR', options).format(currentDate);
					msgerChat.scrollTop = msgerChat.scrollHeight;
					var msgHTML1 =
						  '<div class=\'msg ' + side + '-msg\'>' +
						  '  <div class=\'msg-img\' style=\'background-image: url(/resources/file/profile/'+img+')\'></div>' +
						  '' +
						  '  <div class=\'msg-bubble\'>' +
						  '    <div class=\'msg-info\'>' +
						  '      <div class=\'msg-info-name\'>' + '${dto.sendname_name}' + '</div>' +
						  '      <div class=\'msg-info-time\'>' + formattedTime + '</div>' +
						  '    </div>' +
						  '    <div class=\'msg-text\'>' + m1 + '</div>' +
						  '  </div>' + 
						  '</div>';
					$("#msgs").append(msgHTML1);
					msgerChat.scrollTop = msgerChat.scrollHeight;
				}
			});
			
			// 2번째
			$("#button2").on("click", function () {
				if('${isAdmin}') {
					side = "left";
					var img = '${dto.sendname_img}';
					var m2 = '2. 주문/결제/배송관련 <br />'
					+ '★ 주문 취소<br />'
					+ '주문 후 다시 주문 취소는 판매자가 결제완료 단계여야 취소가 가능합니다. <br />'
					+ '배송준비중 상태로 넘어갔을 경우에는 취소가 불가능하며, 배송완료 후 판매자 문의를 통해 반품 문의를 하셔야 합니다. <br /><br />'
					+ '★ 반품 관련<br />'
					+ '구매자 단순 변심의 이유로 반품을 할 경우, 배송비는 본인 부담이며 농수산물 특성상 배송 완료 후 하루 이내로 운송장을 붙여주셔야 합니다. <br />'
					+ '그 외 상품 불량/파손/오배송 등 판매자의 귀책인 경우에는 고객에게 교환/반품 비용이 발생하지 않습니다. <br /><br />'
					+ '★ 결제 관련 <br />'
					+ '저희 농순배 사이트는 카카오페이를 통해 결제를 진행하고 있으며, 그 외 무통장입금, 핸드폰결제는 지원되지 않음을 알려드립니다. 카카오페이 결제 오류시 참고할 상황은 다음과 같습니다. <br /><br />'
					+ '- 타임아웃 : 일시적인 오류로 잠시 후 재결제 시도<br />'
					+ '- 카드정보 오류 : 카드번호, 비밀번호, CVC 정보 재확인<br />'
					+ '- 할부개월수 : 체크카드, 기프트카드, 법인카드는 일시불만 결제 가능<br />'
					+ '- 안심클릭, ISP결제 등 결제서비스 오류 : 해당 카드사 문의<br />'
					+ '- 카드오류 : 한도초과, 사용중지 등인 경우<br /><br />'
					+ '★ 배송 관련<br />'
					+ '일반적으로 배송은 2-3일 정도 소요되나 상품 수확 시기 , 기후 관련 등에 따라 배송이 미뤄질 수 있습니다. <br />'
					+ '배송 관련한 사항은 상품 판매자와의 1:1 문의를 통해 문의해 주시길 바랍니다.  <br />'
					;
					var currentDate = new Date();
					var options = {
					  hour: '2-digit',
					  minute: '2-digit',
					  hour12: true, // 12시간 형식
					};
					var formattedTime = new Intl.DateTimeFormat('ko-KR', options).format(currentDate);
					msgerChat.scrollTop = msgerChat.scrollHeight;
					var msgHTML2 =
						  '<div class=\'msg ' + side + '-msg\'>' +
						  '  <div class=\'msg-img\' style=\'background-image: url(/resources/file/profile/'+img+')\'></div>' +
						  '' +
						  '  <div class=\'msg-bubble\'>' +
						  '    <div class=\'msg-info\'>' +
						  '      <div class=\'msg-info-name\'>' + '${dto.sendname_name}' + '</div>' +
						  '      <div class=\'msg-info-time\'>' + formattedTime + '</div>' +
						  '    </div>' +
						  '    <div class=\'msg-text\'>' + m2 + '</div>' +
						  '  </div>' + 
						  '</div>';
					$("#msgs").append(msgHTML2);
					msgerChat.scrollTop = msgerChat.scrollHeight;
				}
			});
			
			// 3번째
			$("#button3").on("click", function () {
				if('${isAdmin}') {
					side = "left";
					var img = '${dto.sendname_img}';
					var m3 = '3. 포인트 사용 / 멤버쉽 <br />'
					+ '★ 포인트 사용<br />'
					+ '포인트 사용은 결제시 사용할 수 있습니다. 결제정보에서 보유한 포인트를 확인한 후 사용할 금액을 입력하고 적용하면 됩니다. <br />'
					+ '금액이 부족할 경우에는 남은 금액을 카카오페이를 통하여 결제할 수 있습니다. <br /><br />'
					+ '★ 멤버쉽 혜택 안내 <br />'
					+ '월 4,990원을 결제하면 멤버쉽 회원이 누릴 수 있는 혜택은 다음과 같습니다. <br /><br />'
					+ '- 상품 1개만 사도 무료배송 (최소금액X)<br />'
					+ '- 오후 1시 이전 주문 상품 오늘 밤 도착<br />'
					+ '- 오후 1시 이후 주문 상품 다음날 새벽 도착<br />'
					+ '- 멤버쉽 회원 전용 할인가 <br />'
					+ '- 상품별 시세 변동 상세 확인 가능 <br /><br />'
					+ '지역에 따라 새벽도착, 오늘밤도착 상품 구매 혜택이 적용되지 않을 수 있습니다.'
					;
					var currentDate = new Date();
					var options = {
					  hour: '2-digit',
					  minute: '2-digit',
					  hour12: true, // 12시간 형식
					};
					var formattedTime = new Intl.DateTimeFormat('ko-KR', options).format(currentDate);
					msgerChat.scrollTop = msgerChat.scrollHeight;
					var msgHTML3 =
						  '<div class=\'msg ' + side + '-msg\'>' +
						  '  <div class=\'msg-img\' style=\'background-image: url(/resources/file/profile/'+img+')\'></div>' +
						  '' +
						  '  <div class=\'msg-bubble\'>' +
						  '    <div class=\'msg-info\'>' +
						  '      <div class=\'msg-info-name\'>' + '${dto.sendname_name}' + '</div>' +
						  '      <div class=\'msg-info-time\'>' + formattedTime + '</div>' +
						  '    </div>' +
						  '    <div class=\'msg-text\'>' + m3 + '</div>' +
						  '  </div>' + 
						  '</div>';
					$("#msgs").append(msgHTML3);
					msgerChat.scrollTop = msgerChat.scrollHeight;
				}
			});
			
			// 4번째
			$("#button4").on("click", function () {
				if('${isAdmin}') {
					side = "left";
					var img = '${dto.sendname_img}';
					var m4 = '4. 나의 상점 관련 <br />'
					+ '★ 상점 개설 <br />'
					+ '나의 상점을 개설하고자 하는 경우, "마이페이지 - 상점 개설" 을 통하여 개설할 수 있으며, 별도의 정보를 추가 입력해야 하는 경우가 발생할 수 있습니다. 상점 개설을 통하여 상점 개설 후 나의 상품들을 등록하여 판매할 수 있습니다. <br /><br />'
					+ '★ 상점 관리 <br />'
					+ '나의 상점을 관리하고, 폐쇄 하기 위해서는 "내 상점 관리" 페이지에서 수정 / 폐쇄할 수 있습니다. <br />'
					+ '폐쇄한 상점은 다시 복구할 수 없습니다. (필요시 새로 개설)'
					;
					var currentDate = new Date();
					var options = {
					  hour: '2-digit',
					  minute: '2-digit',
					  hour12: true, // 12시간 형식
					};
					var formattedTime = new Intl.DateTimeFormat('ko-KR', options).format(currentDate);
					msgerChat.scrollTop = msgerChat.scrollHeight;
					var msgHTML4 =
						  '<div class=\'msg ' + side + '-msg\'>' +
						  '  <div class=\'msg-img\' style=\'background-image: url(/resources/file/profile/'+img+')\'></div>' +
						  '' +
						  '  <div class=\'msg-bubble\'>' +
						  '    <div class=\'msg-info\'>' +
						  '      <div class=\'msg-info-name\'>' + '${dto.sendname_name}' + '</div>' +
						  '      <div class=\'msg-info-time\'>' + formattedTime + '</div>' +
						  '    </div>' +
						  '    <div class=\'msg-text\'>' + m4 + '</div>' +
						  '  </div>' + 
						  '</div>';
					$("#msgs").append(msgHTML4);
					msgerChat.scrollTop = msgerChat.scrollHeight;
				}
			});
			
			// 5번째
			$("#button5").on("click", function () {
				if('${isAdmin}') {
					side = "left";
					var img = '${dto.sendname_img}';
					var m5 = '5. 계정이 정지 되는 이유를 알고 싶어요'
					+ '계정이 정지 될 수 있는 이유는 다음과 같습니다. <br />'
					+ '-부적절한 내용(폭력적,혐오적 댓글이나 게시글 or 스팸 게시)<br />'
					+ '-부정행위(가짜 계정 생성, 부정 혜택 이용, 해킹 등)<br />'
					+ '-사기 (거짓 정보 제공, 결제 사기, 물품 미배송)<br />'
					+ '-결제 관련 (반복된 결제 실패, 카드 도용, 환불 규정 위반)<br />'
					+ '-개인 정보 보호 위반 (다른 사용자의 정보를 무단으로 수집, 남용)<br />'
					+ '-서비스 악용 (시스템의 취약점 이용, 서버 부하 유발하는 행위)<br /><br />'
					+ '위와 같은 사유로 계정이 정지 될 수 있으며, 기타 문의 사항이 있을시 1:1 채팅을 통해 문의주시길 바랍니다.'
					;
					var currentDate = new Date();
					var options = {
					  hour: '2-digit',
					  minute: '2-digit',
					  hour12: true, // 12시간 형식
					};
					var formattedTime = new Intl.DateTimeFormat('ko-KR', options).format(currentDate);
					msgerChat.scrollTop = msgerChat.scrollHeight;
					var msgHTML5 =
						  '<div class=\'msg ' + side + '-msg\'>' +
						  '  <div class=\'msg-img\' style=\'background-image: url(/resources/file/profile/'+img+')\'></div>' +
						  '' +
						  '  <div class=\'msg-bubble\'>' +
						  '    <div class=\'msg-info\'>' +
						  '      <div class=\'msg-info-name\'>' + '${dto.sendname_name}' + '</div>' +
						  '      <div class=\'msg-info-time\'>' + formattedTime + '</div>' +
						  '    </div>' +
						  '    <div class=\'msg-text\'>' + m5 + '</div>' +
						  '  </div>' + 
						  '</div>';
					$("#msgs").append(msgHTML5);
					msgerChat.scrollTop = msgerChat.scrollHeight;
				}
			});	
            
            
         });
      </script>
   </head>
   <body>
      <section class="msger">
        <header class="msger-header">
          <div class="msger-header-title">
            <i class="fas fa-comment-alt"></i> ${dto.sendname_name}
          </div>
          <div class="msger-header-options">
            <a href="/chat/list">❌</a>
          </div>
        </header>
         <main class="msger-chat" id="msgs">
            ${chat}
         </main>
         <c:if test="${isAdmin}">
			<div style="width : auto; overflow: scroll;">
			   <div class="btn-group-vertical">
			      <button type="button" class="btn btn-outline-primary" id="button1">로그인 / 개인정보수정 / 탈퇴 / 정지 안내</button>
			      <button type="button" class="btn btn-outline-primary" id="button2">주문/결제/배송관련 안내</button>
			      <button type="button" class="btn btn-outline-primary" id="button3">포인트 사용 / 멤버쉽 안내</button>
			      <button type="button" class="btn btn-outline-primary" id="button4">나의 상점 관련 안내 </button>
			      <button type="button" class="btn btn-outline-primary" id="button5">계정이 정지 되는 이유를 알고 싶어요</button>
			   </div>
			</div>
		 </c:if>
         <div class="msger-inputarea">
             <input type="text" class="msger-input" name="chat" id="chat" placeholder="메세지를 입력하세요...">
             <button type="submit" id="sendBtn" class="msger-send-btn">전송</button>
         </div>    
      </section>
   </body>
</html>






