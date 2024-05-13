<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
 
        <!-- 등록 틀 -->
        <section class="d-flex flex-column min-vh-100">
            <div style="height:50px"></div>
            <div class="row">
              <h1 class="text-center">게시글 작성</h1>
            </div>
            <div class="container p-5 my-5" style="width: 800px;">
                <div class="row">
                  <div class="input-form col-md-12 mx-auto" >
                    <form id="form-register" class="validation-form" method="POST" action="" novalidate>
                      <input type="hidden" name="action" value="write">
                      <fieldset style="width: auto">
                        <div class="mb-3 mt-4">
                          <label for="userId">작성자 ID: </label>
                          <input type="text" class="form-control" id="userId" name="userId" placeholder="ID" required>
                        </div>
                        <div class="mb-3 mt-4">
                          <label for="subject">제목 : </label>
                          <input type="text" class="form-control" id="subject" name="subject" placeholder="제목을 입력하세요." required>
                        </div>
                        <div class="mb-3 mt-4">
                          <label for="content">내용 : </label>
                          <input type="text" id="content" name="content" class="form-control" style="height: 200px;" id="tel" placeholder="내용을 입력하세요." required>
                        </div>       
                      </fieldset>    
                      <div class="mb-4"></div>
                      <button class="btn btn-warning btn-lg btn-block" type="submit" id="btn-write">등록</button>
                      <button class="btn btn-warning btn-lg btn-block" type="reset" id="btn-reset">취소</button>
                    </form>
                  </div>
                </div>
            </div>
        </section>
    </body>
        <script>
      document.querySelector("#btn-write").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-register");
          form.setAttribute("action", "${root}/board");
          form.submit();
        }
      });
    </script>
<%@ include file="/common/footer.jsp" %>