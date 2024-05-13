<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<body>
    <!-- 로그인 틀 -->
    <section class="d-flex flex-column min-vh-100">
        <div style="height:50px"></div>
        <div class="row">
          <h1 class="text-center">로그인</h1>
        </div>
        <div class="container p-5 my-5 bg-light border" style="width: 500px;">
            <div class="row">
              <div class="input-form col-md-12 mx-auto" >
				<form method="Post" action="${root}/member">
				  <input type="hidden" name="action" value="login">
                  <div class="row"> 
                    <div class="mb-3 mt-4">
                      <label for="id">아이디</label>
                      <input type="text" class="form-control" id="id" name="id" required>
                    </div>
                    <div class="mb-3 mt-4">
                      <label for="password">비밀번호</label>
                      <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <div class="mb-3 mt-4 form-check">
                      <input type="checkbox" class="form-check-input" id="saveid" name="saveid">
                      <label class="form-check-label" for="saveid">로그인 유지</label>
                    </div>
                  </div>
                  <div class="mb-3 mt-4"></div>
                  
                  <a class="nav-link" style="color: gray" onclick="getIdToFind()">비밀번호 찾기</a>
                  <button class="btn btn-warning btn-lg btn-block w-100" type="submit" id="loginButton">로그인</button>
                </form>
              </div>
            </div>
        </div>
    </section>
</body>
<script type="text/javascript">
	window.onload = function() {
        var loggedIn = "<%= session.getAttribute("findPassword") %>";
        var state = "<%= session.getAttribute("findState") %>";
		console.log(loggedIn);
		console.log(state);
                    		if (state != 'null' && loggedIn == 'null') {
	        window.alert("등록되지 않은 회원 입니다.");
	        let form = document.createElement('form');
	        form.method = 'GET';
	        form.action = '${root}/member';
	        
	        let hiddenField = document.createElement('input');
	        hiddenField.type = 'hidden';
	        hiddenField.name = 'action';
	        hiddenField.value = 'removeSession';
	        form.appendChild(hiddenField);
	        
	        document.body.appendChild(form);
	        form.submit();
	    } else if (state != 'null' && loggedIn != 'null') {
	    	window.alert("당신의 비밀번호는 " + loggedIn + "입니다.");
	        let form = document.createElement('form');
	        form.method = 'GET';
	        form.action = '${root}/member';
	        
	        let hiddenField = document.createElement('input');
	        hiddenField.type = 'hidden';
	        hiddenField.name = 'action';
	        hiddenField.value = 'removeSession';
	        form.appendChild(hiddenField);
	        
	        document.body.appendChild(form);
	        form.submit();
	    } 
		
	};
    function getIdToFind() {
        let id = window.prompt("아이디를 입력하세요.");
        if (id) {
            let form = document.createElement('form');
            form.method = 'POST';
            form.action = '${root}/member';
            
            let hiddenField = document.createElement('input');
            hiddenField.type = 'hidden';
            hiddenField.name = 'action';
            hiddenField.value = 'find';
            form.appendChild(hiddenField);
            
            let idField = document.createElement('input');
            idField.type = 'hidden';
            idField.name = 'id';
            idField.value = id;
            form.appendChild(idField);
            
            document.body.appendChild(form);
            form.submit();
        }
    }
</script>
                    