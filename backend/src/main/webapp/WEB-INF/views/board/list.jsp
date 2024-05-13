<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ include file="/common/header.jsp"%>
<!-- 게시글 리스트 -->
<section class="d-flex flex-column min-vh-100">
	<div style="height: 50px"></div>
	<div class="row">
		<h1 class="text-center">여행정보공유</h1>
	</div>
	<div class="row justify-content-center">
		<a class="btn me-2" href="${root}/board?action=mvwrite">게시글 등록하기</a>
	</div>
	<div class="container p-5 my-5">
		<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" class="text-center">글번호</th>
						<th scope="col" class="text-center">제목</th>
						<th scope="col" class="text-center">글쓴이</th>
						<th scope="col" class="text-center">조회수</th>
						<th scope="col" class="text-center">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="article" items="${articles}">
						<tr>
							<td scope="col" class="text-center">${article.articleNo }</td>
							<td scope="col" class="text-center">
								                  <a
	                    href="#"
	                    class="article-title link-dark"
	                    data-no="${article.articleNo}"
	                    style="text-decoration: none"
	                  >
	                    ${article.subject}
	                  </a>
							</td>
							<td scope="col" class="text-center">${article.userId }</td>
							<td scope="col" class="text-center">${article.hit }</td>
							<td scope="col" class="text-center">${article.registerTime }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>
    <script>
      let titles = document.querySelectorAll(".article-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
          console.log(this.getAttribute("data-no"));
          location.href = "${root}/board?action=view&articleno=" + this.getAttribute("data-no");
        });
      });
    </script>
</body>
<%@ include file="/common/footer.jsp"%>
