<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
	<!-- header -->
	<header class="masthead">
        <div class="container position-relative" style="height: 250px;">
            <div class="row justify-content-center ">
                <div class="col-xl-12">
                    <div class="text-center text-white">
                        <!-- Page heading-->
                        <h1 class="mb-5">지역별 관광지 정보를 알려드립니다!</h1>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center ">
                <div class="col-xl-6">
                    <div class="text-center text-white">
                        <!-- Page heading-->
                        <form class="form-subscribe">
                            <!-- Email address input-->
                            <div class="row">
                                <div class="col">
                                    <input class="form-control form-control-lg" id="emailAddress" type="email" placeholder="원하는 지역을 검색하세요."  />
                                </div>
                                <div class="col-auto"><button class="btn btn-warning btn-lg disabled" id="submitButton"> Enter </button></div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </header>
	<!-- main  -->	
     <main>
        <div class="container position-relative" style="width: 100vw; margin-top: 50px;">
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="text-center text-white">
                        <div class="row justify-content-center">
                            <!-- First Row -->
                            <div class="col-md-6 mb-4">
                                <div class="card text-dark" style="margin-right: 20px;">
                                    <img class="card-img-top" src="./assets/img/feature1.png" alt="Card image" style="width:100%">
                                    <div class="card-body">
                                      <h4 class="card-title">지도로 한눈에 보아요</h4>
                                      <p class="card-text">궁금한 관광지를 검색하면 지도로 한눈에 볼 수 있어요</p>
                                      <a href="#" class="btn btn-primary">더보기</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 mb-4">
                                <div class="card text-dark" style="margin-right: 20px;">
                                    <img class="card-img-top" src="./assets/img/feature4.png" alt="Card image" style="width:100%">
                                    <div class="card-body">
                                      <h4 class="card-title">궁금한 지역을 검색해요</h4>
                                      <p class="card-text">궁금한 지역을 검색하면 해당 지역만 볼 수 있어요</p>
                                      <a href="#" class="btn btn-primary">더보기</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Second Row -->
                            <div class="col-md-6 mb-4">
                                <div class="card text-dark">
                                    <img class="card-img-top" src="./assets/img/feature3.png" alt="Card image" style="width:100%">
                                    <div class="card-body">
                                      <h4 class="card-title">원하는 키워드로 검색해요</h4>
                                      <p class="card-text">관광지, 음식점, 문화시설, 공연 등 원하는 키워드로 검색할 수 있어요</p>
                                      <a href="#" class="btn btn-primary">더보기</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 mb-4">
                                <div class="card text-dark">
                                    <img class="card-img-top" src="./assets/img/feature2.png" alt="Card image" style="width:100%">
                                    <div class="card-body">
                                      <h4 class="card-title">장소 정보를 디테일하게 보아요</h4>
                                      <p class="card-text">장소를 선택하면 주소, 전화번호, 사진 정보를 제공해줘요</p>
                                      <a href="#" class="btn btn-primary">더보기</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Placeholder for the fourth card; duplicate any card here as needed -->
                            <div class="col-md-6 mb-4">
                                <div class="card text-dark">
                                    <!-- Duplicate content of any card for the fourth card -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container position-relative" style="width: 100vw; margin-top: 50px; margin-bottom: 100px;">
            <div class="row justify-content-center ">
                <div class="col-xl-12">
                    <div class="text-center">
                        <!-- Page heading-->
                        <h2 class="mb-5">회원 등록시 더 많은 기능을 (추후) 제공합니다!</h2>
                        <div class="icon-buttons">
                            <a href="./page/member/join.html" id="signup-icon" class="icon-button">
                                <i class="bi bi-person-plus" style="font-size: 2rem;"></i>
                            </a>
                            <a href="./page/member/login.html" id="login-icon" class="icon-button">
                                <i class="bi bi-box-arrow-in-right" style="font-size: 2rem;"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container p-5 my-5 bg-dark text-white" style="padding-bottom: 5rem !important;">
        	<h3 class="text-center">바로가기 (사이트맵)</h3>
        	<br></br>
        	<div class="row">
				<div class="col"> 
					<p>• 회원관리</p>    
					<ul>
			        	<a href="${root}/member?action=mvjoin">회원가입</a><br>
			        	<a href="${root}/member?action=mypage">마이페이지</a><br>
			        	<a href="${root}/member?action=mvlogin">로그인</a><br>
		        	</ul>
				</div>
				<div class="col">
					<p>• 여행지 검색</p>
					<ul>
			        	<a href="${root}/trip">지역별 여행지 검색</a><br>
		        	</ul>			
				</div>
				<div class="col">
					<p>• 여행정보공유 (게시판)</p>
					<ul>
			        	<a href="${root}/board?action=list">게시글 목록</a><br>
			        	<a href="${root}/board?action=mvwrite">게시글 등록</a><br>
		        	</ul>			
				</div>
			</div>
        </div>
    </main>
</body>
<!-- footer -->
<%@ include file="/common/footer.jsp" %>