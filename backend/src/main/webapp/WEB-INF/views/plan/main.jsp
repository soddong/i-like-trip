<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/common/header.jsp" %>
    <section>
      <div class="container">
        <div style="height: 50px"></div>
        <div class="row justify-content-center">
          <div class="row">
            <h1 class="text-center">여행계획!!</h1>
          </div>
          <div class="row my-4">
            <div class="hstack" id="map" style="width: 100%; height: 800px">
              <div
                class="border border-dark bg-secondary bg-opacity-25"
                style="z-index: 2; height: 95%; width: 13rem; padding: 0 3px"
              >
                <div
                  id="choice-list"
                  class="d-flex flex-column"
                  style="overflow-y: unset; overflow-x: hidden; height: 700px"
                ><button
                        id="btn-TSP"
                        class="btn btn-outline-success btn-sm"
                        type="button"
                      >
                        최단경로 검색
                      </button>
                </div>
              </div>
              <div class="ms-auto" style="z-index: 2; height: 95%">
                <div class="d-flex flex-column" style="width: 22rem">
                  <!-- 관광지 검색 start -->
                  <div>
                    <form
                      class="d-flex my-1"
                      onsubmit="return false"
                      role="search"
                      style="height: 2.5rem"
                    >
                      <select id="search-content-id" class="form-select form-select-sm me-2">
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
                        class="form-control form-control-sm me-2 p-1"
                        type="search"
                        placeholder="검색어"
                        aria-label="검색어"
                      />
                      <button
                        id="btn-search"
                        class="btn btn-outline-success btn-sm"
                        type="button"
                        style="width: 8rem"
                      >
                        검색
                      </button>
                    </form>
                  </div>
                  <div
                    id="result-list"
                    class="d-flex flex-column"
                    style="overflow-y: unset; overflow-x: hidden; height: 700px"
                  ></div>
                </div>
              </div>
            </div>
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
    <script src="${root}/assets/js/plan/main.js"></script>
  </body>
<%@ include file="/common/footer.jsp" %>
