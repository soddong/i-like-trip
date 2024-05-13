<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/common/header.jsp" %>
<c:set var="data" value="${result}"></c:set>
    <section>
      <div class="container">
        <div style="height:50px"></div>
        <div class="row justify-content-center">
          <div class="row">
            <h1 class="text-center">지역별 관광정보</h1>
          </div>
          <div class="row">
            <!-- 관광지 검색 start -->
            <form class="d-flex my-3">
              <%@ include file="/trip/address.jsp" %>
              
              <select id="search-content-id" class="form-select me-2" name="contentType">
                <option value="0" selected>관광지 유형</option>
                <option value="12">관광지</option>
                <option value="14">문화시설</option>
                <option value="15">축제공연행사</option>
                <option value="25">여행코스</option>
                <option value="28">레포츠</option>
                <option value="32">숙박</option>
                <option value="38">쇼핑</option>
                <option value="39">음식점</option>
              </select>
              <input
                id="search-keyword"
                class="form-control me-2"
                type="search"
                placeholder="검색어"
                aria-label="검색어"
                name="keyword"
              />
              <input type="hidden" value="search" name="action"/>
              <button id="btn-search" class="btn btn-outline-success" type="button">검색</button>
            </form>
          </div>
          <div class="row my-4">
            <div id="map" style="width: 100%; height: 800px"></div>
          </div>
        </div>
      </div>
    </section>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=962951121973d7e10dc82aefe1ed6a08"
    ></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=962951121973d7e10dc82aefe1ed6a08&libraries=services,clusterer,drawing"
    ></script>
    <script src="${root}/assets/js/trip/main.js" ></script>
  </body>
<!-- footer -->
<%@ include file="/common/footer.jsp" %>
