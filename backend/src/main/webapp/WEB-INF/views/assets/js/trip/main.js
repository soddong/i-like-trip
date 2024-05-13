// 검색 버튼을 누르면..
// 지역, 유형, 검색어 얻기.
// 위 데이터를 가지고 공공데이터에 요청.
// 받은 데이터를 이용하여 화면 구성.
document.getElementById("btn-search").addEventListener("click", () => {
	let baseUrl = location.pathname;
	let areaCode = document.getElementById("search-area").value;
	let areaLevel2Code = document.getElementById("search-area-level2").value;
	let contentTypeId = document.getElementById("search-content-id").value;
	let keyword = document.getElementById("search-keyword").value;
	let queryString='';
	if (parseInt(areaCode)) queryString += `&sido=${areaCode}`;
	if (parseInt(areaLevel2Code)) queryString += `&gugun=${areaLevel2Code}`;
	if (parseInt(contentTypeId)) queryString += `&contentType=${contentTypeId}`;

	if (keyword) {
		queryString += `&keyword=${keyword}`;
	} 
	let searchUrl = baseUrl + "?" + queryString+"&action=search";
	console.log(searchUrl);
	fetch(searchUrl)
		.then((response) => response.json())
		.then((data) => makeList(data));
});

function makeList(data) {
	console.log(data);
	displayMarker(data);
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
	mapOption = {
		center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
		level: 5, // 지도의 확대 레벨
	};

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);
var overlay = new kakao.maps.CustomOverlay({
	content: "",
	position: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
	yAnchor: 1.3,
});
kakao.maps.event.addListener(map, "dragstart", function() {
	overlay.setMap(null);
});
var markerList = [];
var dataList = [];
function displayMarker(data) {
	if (markerList.length) {
		markerList.forEach((e) => {
			e.setMap(null);
			overlay.setMap(null);
		});
	}
	markerList = [];
	dataList = data;
	for (let i = 0; i < data.length; i++) {
		// 마커 이미지의 이미지 크기 입니다
		var imageSize = new kakao.maps.Size(24, 35);

		// 마커 이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(
			`assets/img/marker/type${data[i].contentType}.png`,
			imageSize
		);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			map: map, // 마커를 표시할 지도
			position: new kakao.maps.LatLng(data[i].lat, data[i].lng), // 마커를 표시할 위치
			title: data[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
			image: markerImage, // 마커 이미지
		});
		marker.id = i;

		// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
		kakao.maps.event.addListener(marker, "click", openOverlay);

		markerList.push(marker);
	}

	// 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
	map.setCenter(new kakao.maps.LatLng(data[0].lat, data[0].lng));
}

function moveCenter(lat, lng) {
	map.setCenter(new kakao.maps.LatLng(lat, lng));
}

// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다
function closeOverlay() {
	overlay.setMap(null);
}

function openOverlay() {
	let content = `
  <div class="container border rounded" style="width: 300px; height: fit-content">
    <div class="row bg-warning">
      <div class="col fw-bold text-center">${dataList[this.id].title}</div>
      <div class="col" onclick="closeOverlay(this)" id="overlay-${this.id}">X</div>
    </div>
    <div class="row bg-light align-items-center">
      <div class="col-5 gx-3">
        <img
          class="img-fluid rounded"
          src="${dataList[this.id].img}"
          alt=""
        />
      </div>
      <div class="col-7 my-1">
        <p class="text-nowrap w-100 mb-1" style="text-overflow: ellipsis; overflow: hidden">
          ${dataList[this.id].addr1}
        </p>
        <p style="font-size: 0.8rem" class="mb-1">${dataList[this.id].zipcode}</p>
        <p style="font-size: 0.8rem" class="mb-1">${dataList[this.id].tel ? dataList[this.id].tel : "전화번호 없음"
		}</p>
      </div>
    </div>
  </div>
  `;
	overlay.setContent(content);
	overlay.setPosition(markerList[this.id].getPosition());
	overlay.setMap(map);
}
