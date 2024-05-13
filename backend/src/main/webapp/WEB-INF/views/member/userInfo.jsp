<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<body data-root="${root}">
   <section>
      <div class="container position-relative min-vh-100" style="max-width: 900px;">
        <div style="height:50px"></div>
        <div class="row">
          <div class="col-md-3">
            <h1 class="text-center">마이페이지</h1>
            <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
              <button class="nav-link active" id="v-pills-home-tab" data-bs-toggle="pill" data-bs-target="#v-pills-home" type="button" role="tab" aria-controls="v-pills-home" aria-selected="true">회원 정보</button>
              <button class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill" data-bs-target="#v-pills-profile" type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false">탈퇴</button>
           	  <button class="nav-link" id="v-pills-friend-tab" data-bs-toggle="pill" data-bs-target="#v-pills-friend" type="button" role="tab" aria-controls="v-pills-friend" aria-selected="false">친구 목록</button>
            </div>
          </div>
          <div class="col-md-9">
            <div class="tab-content container" id="v-pills-tabContent">
            
              <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
                <div class="container-fluid p-5 bg-light">
                  <!-- 아이디 -->
                  <div class="row my-2 align-items-center">
                    <div class="col-md-3">아이디</div>
                    <div class="col-md-6">
                      <input type="text" class="form-control" value="${userinfo.userId}" placeholder="현재 ID" readonly>
                    </div>
                  </div>
                  <!-- 비밀번호 수정 -->
                  <div class="row my-2 align-items-center">
                    <div class="col-md-12">
                      <form class="row" method="Post" action="${root}/member">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="menu" value="password">
                        <div class="col-md-3">비밀번호</div>
                        <div class="col-md-6">
                          <input type="text" class="form-control" name="password" placeholder="새로운 비밀번호를 입력해주세요">
                        </div>
                        <div class="col-md-3">
                          <button type="submit" class="btn btn-warning btn-sm">수정</button>
                        </div>
                      </form>
                    </div>
                  </div>
                  <!-- 이름 수정 -->
                  <div class="row my-2 align-items-center">
                    <div class="col-md-12">
                      <form class="row" method="Post" action="${root}/member">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="menu" value="userName">
                        <div class="col-md-3">이름</div>
                        <div class="col-md-6">
                          <input type="text" class="form-control" value="${userinfo.userName}" name="userName" placeholder="김싸피">
                        </div>
                        <div class="col-md-3">
                          <button type="submit" class="btn btn-warning btn-sm">수정</button>
                        </div>
                      </form>
                    </div>
                  </div>
                  <!-- 전화번호 수정 -->
                  <div class="row my-2 align-items-center">
                    <div class="col-md-12">
                      <form class="row" method="Post" action="${root}/member">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="menu" value="tel">
                        <div class="col-md-3">전화번호</div>
                        <div class="col-md-6">
                          <input type="text" class="form-control" value="${userinfo.tel}" name="tel" placeholder="010-1234-5678">
                        </div>
                        <div class="col-md-3">
                          <button type="submit" class="btn btn-warning btn-sm">수정</button>
                        </div>
                      </form>
                    </div>
                  </div>
                  <!-- 거주지역 수정 -->
                  <div class="row my-2 align-items-center">
                    <div class="col-md-12">
                      <form class="row" method="Post" action="${root}/member">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="menu" value="location">
                        <div class="col-md-3">거주지역</div>
                        <div class="col-md-6">
                          <input type="text" class="form-control" value="${userinfo.location}" name="location" placeholder="지역">
                        </div>
                        <div class="col-md-3">
                          <button type="submit" class="btn btn-warning btn-sm">수정</button>
                        </div>
                      </form>
                    </div>
                  </div>
                  <!-- 생성 일자 -->
                  <div class="row my-2 align-items-center">
                    <div class="col-md-3">생성일</div>
                    <div class="col-md-6">
                      <input type="text" class="form-control" value="${userinfo.joinDate}" placeholder="0000:00:00" readonly>
                    </div>
                  </div>
                </div>
              </div>
              
              <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                <div class="row">
                  <div class="container-fluid p-5 bg-light" style="max-width: 500px; display: flex; justify-content: center; flex-direction: column;">
                    <div class="row">
                      <img src="${root}/assets/img/foo.png">
                      <h2 class="p-2" style="text-align: center;">정말 탈퇴하시겠습니까?</h2>
                    </div>
                    <div class="row">
                      <div class="row">
                        <a class="btn btn-warning" href="${root}/member?action=delete" style="display: block; width: 100%; text-align: center;">탈퇴하기</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              
              <div class="tab-pane fade" id="v-pills-friend" role="tabpanel" aria-labelledby="v-pills-friend-tab">
                <div class="container-fluid p-5 bg-light">
                  <!-- 아이디 입력 -->
                  <div class="row my-2 align-items-centerv ">
                    <div class="col-md-12">
                      <form class="row" method="Post" action="${root}/friend">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="userId" value="${userinfo.userId}" >
                        <div class="col-md-10">
                          <input type="text" class="form-control" name="friendId" placeholder="친구ID 입력">
                        </div>
                        <div class="col-md-2">
                          <button type="submit" class="btn btn-warning btn-sm">추가</button>
                        </div>
                      </form>
                    </div>
                </div>
				<!-- 친구 리스트 -->
				<div class="row my-2 align-items-center">
				    <div class="col-md-10 mx-auto bg-light p-3">
				        <!-- 토글 버튼 -->
				        <div class="row align-items-center">
				            <div class="col mx-auto">
				                <button id="toggleFriendList" class="btn btn-secondary" style="width: 180px">친구 리스트 보기</button>
				            </div>
				            <div class="col mx-auto">
				                <button id="toggleRecommandList" class="btn btn-secondary" style="width: 180px">추천 친구 보기</button>
				            </div>
				        </div>
				        
				        <!-- 친구 리스트 -->
				        <div class="row my-2 align-items-center" style="display:none;" id="friendList">
				            <div class="col-md-12 bg-white border rounded">
				                <table class="table">
				                    <thead>
				                        <tr>
				                            <th scope="col" class="text-center">친구 ID</th>
				                        </tr>
				                    </thead>
				                    <tbody id="friendsTableBody">
				                        <!-- 친구 리스트는 AJAX를 통해 동적으로 여기에 삽입됩니다 -->
				                    </tbody>
				                </table>
				            </div>
				        </div>
				        
				        <!-- 추천 친구 리스트 -->
				        <div class="row my-2 align-items-center" style="display:none;" id="recommandList">
				            <div class="col-md-12 bg-white border rounded">
				                <table class="table">
				                    <thead>
				                        <tr>
				                            <th scope="col" class="text-center">추천친구 ID</th>
				                            <th scope="col" class="text-center">추천도</th>
				                        </tr>
				                    </thead>
				                    <tbody id="recommandsTableBody">
				                        <!-- 추천 친구 리스트는 AJAX를 통해 동적으로 여기에 삽입됩니다 -->
				                    </tbody>
				                </table>
				            </div>
				        </div>
				    </div>
				</div>

		    </div>
		</div>
    </section>
</body>

<script>
document.getElementById('toggleFriendList').addEventListener('click', function() {
    const root = document.body.getAttribute('data-root');
    const display = document.getElementById('friendList').style.display;
    if (display === 'none') {
        fetch(`${root}/friend?action=list`)
            .then(response => response.json())
            .then(data => {
                const friendsTableBody = document.getElementById('friendsTableBody');
                friendsTableBody.innerHTML = ''; 
                data.forEach(friend => {
                    const row = `<tr><td class="text-center">` +  String(friend.friendId) + `</td></tr>`;
                    friendsTableBody.innerHTML += row;
                });
                document.getElementById('friendList').style.display = 'block';
            });
    } else {
        document.getElementById('friendList').style.display = 'none';
    }
});

document.getElementById('toggleRecommandList').addEventListener('click', function() {
    const root = document.body.getAttribute('data-root');
    const display = document.getElementById('recommandList').style.display;
    if (display === 'none') {
        fetch(`${root}/friend?action=recommand`)
            .then(response => response.json())
            .then(data => {
                const friendsTableBody = document.getElementById('recommandsTableBody');
                friendsTableBody.innerHTML = ''; 
                data.forEach(friend => {
                    const row = `<tr><td class="text-center">` + String(friend.friendId) + `</td><td class="text-center">` + String(friend.relation) + `</td></tr>`;
                    friendsTableBody.innerHTML += row;
                });
                document.getElementById('recommandList').style.display = 'block';
            });
    } else {
        document.getElementById('recommandList').style.display = 'none';
    }
});


</script>
<%@ include file="/common/footer.jsp" %>

