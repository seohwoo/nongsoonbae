<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<main class="col-9 py-md-3 pl-md-5 bd-content" role="main" style="margin-top: 30px;">
	<div class="container like">
      <h1>찜/구독 리스트</h1>
	    <div class='tabbed skin-nephritis round' id='skinable'>
			<ul>
				<li>내가 구독한 농부</li>
				<li class='active'>내가 찜한 상품</li>
			</ul>
		</div>
    </div>
    </main>
    <script>
   		document.addEventListener("DOMContentLoaded", function() {
    	  var tabs = document.querySelectorAll('.tabbed li');
    	  var switchers = document.querySelectorAll('.switcher-box a');
    	  var skinable = document.getElementById('skinable');

    	  for (var i = 0, len = tabs.length; i < len; i++) {
    	    tabs[i].addEventListener("click", function() {
    	      if (this.classList.contains('active'))
    	        return;

    	      var parent = this.parentNode,
    	          innerTabs = parent.querySelectorAll('li');

    	      for (var index = 0, iLen = innerTabs.length; index < iLen; index++) {
    	        innerTabs[index].classList.remove('active');
    	      }

    	      this.classList.add('active');
    	    });
    	  }

    	  for (var i = 0, len = switchers.length; i < len; i++) {
    	    switchers[i].addEventListener("click", function() {
    	      if (this.classList.contains('active'))
    	        return;

    	      var parent = this.parentNode,
    	          innerSwitchers = parent.querySelectorAll('a'),
    	          skinName = this.getAttribute('skin');

    	      for (var index = 0, iLen = innerSwitchers.length; index < iLen; index++) {
    	        innerSwitchers[index].classList.remove('active');
    	      }

    	      this.classList.add('active');
    	      skinable.className = 'tabbed round ' + skinName;
    	    });
    	  }
    	});
    </script>
</body>
</html>