<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<body> 
   <!-- 회원가입 틀 -->
   <section class="d-flex flex-column min-vh-100">
       <div style="height:50px"></div>
       <div class="row">
         <h1 class="text-center">회원가입</h1>
       </div>
       <div class="container p-5 my-5 bg-light border" style="width: 800px;">
           <div class="row">
             <div class="input-form col-md-12 mx-auto" >
			<form method="Post" action="${root}/member">
				<input type="hidden" name="action" value="join">
               	<fieldset>
             	  <div class="row  mt-4">
                   <div class="col-md-5 mb-3">
                     <label for="id">아이디</label>
                     <input type="text" class="form-control" id="id" name="id" required>
                   </div>
                   <div class="col-md-5 mb-3">
                     <label for="password">비밀번호</label>
                     <input type="password" class="form-control" name="password" id="password" required>
                   </div>
                   <div class="col-md-2 mb-3">
                     <label for="password-check"></label>
                     <input type="button" class="form-control" name="checkpw" id="checkpw" value="확인" onclick="checkPassword()" required>
                   </div>
                   <h6 style="color: #808080">⚠ 비밀번호 규칙: 4자 이상, 영문-숫자-특수문자(!@^&*) 포함, 아이디 포함x ⚠</h6>
                  </div>
                  <div class="mb-3 mt-4">
                     <label for="name">이름</label>
                     <input type="text" class="form-control" id="name" name="name" placeholder="ex) 서땡땡" required>
                   </div>
                   <div class="mb-3 mt-4">
                     <label for="tel">전화번호</label>
                     <input type="text" class="form-control" id="tel" name="tel" placeholder="ex) 010-4312-8761" required>
                   </div>
                   <div class="mb-3 mt-4">
	                  <label for="location">거주 지역</label>
	                  <select class="form-select d-block w-100" id="location" name="location" placeholder="지역을 선택하세요." required>
	                       <option value="none">지역을 선택하세요.</option>
	                       <option>서울시</option>
	                       <option>경기도</option>
	                       <option>경상도</option>
	                       <option>제주도</option>
	                       <option>그 외</option>
	                  </select>
                   </div>
                   <div class="custom-control custom-checkbox  mt-4">
                     <input type="checkbox" class="custom-control-input" id="aggrement" required>
                     <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
                   </div>
                   <div class="mb-4"></div>
                   <button class="btn btn-warning btn-lg btn-block" type="submit" id="joinButton">가입 완료</button>
               	</fieldset>
               </form>
             </div>
           </div>
       </div>
   </section>
   
   <script>
   function buildPrefixArray(pattern) {
       let prefixArray = new Array(pattern.length).fill(0);
       let j = 0;

       for (let i = 1; i < pattern.length; i++) {
           while (j > 0 && pattern[i] !== pattern[j]) {
               j = prefixArray[j - 1];
           }
           if (pattern[i] === pattern[j]) {
               j++;
           }
           prefixArray[i] = j;
       }

       return prefixArray;
   }

   function KMP(text, pattern) {
       let prefixArray = buildPrefixArray(pattern);
       let j = 0;

       for (let i = 0; i < text.length; i++) {
           while (j > 0 && text[i] !== pattern[j]) {
               j = prefixArray[j - 1];
           }
           if (text[i] === pattern[j]) {
               j++;
               if (j === pattern.length) {
                   return true; 
               }
           }
       }

       return false; 
   }

   function checkPassword() {
	    const id = document.getElementById('id').value;
	    const password = document.getElementById('password').value;

	    if (password.length < 4) {
	        alert('비밀번호는 4자 이상이어야 합니다.');
	        return;
	    }

	    const hasNumber = /\d/.test(password);
	    const hasLetter = /[a-zA-Z]/.test(password); 
	    const hasSpecialChar = /[!@^&*]/.test(password); 

	    // 아이디 포함 검사 (KMP 알고리즘 사용)
	    if (KMP(password, id)) {
	        alert('비밀번호에 아이디를 포함할 수 없습니다.');
	        return;
	    }
	    
	    if (!hasNumber || !hasLetter || !hasSpecialChar) {
	        alert('비밀번호는 숫자, 영문자, 특수문자를 반드시 포함해야 합니다.');
	        return;
	    }

	    alert('비밀번호가 유효합니다.');
	}

   </script>
</body>
<%@ include file="/common/footer.jsp" %>