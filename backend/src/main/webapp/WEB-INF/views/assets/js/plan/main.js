// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
	mapOption = {
		center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
		level: 5, // 지도의 확대 레벨
	};

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

window.onload = () => {
	document.getElementById("btn-search").addEventListener("click", searchByKeyword);
};

var curSearchResult = [];
var markerList = [];
var choiceList = {};
function searchByKeyword() {
	let baseUrl = location.pathname;
	let queryString = '';
	let keyword = document.getElementById("search-keyword").value;
	document.getElementById("search-keyword").value = "";
	let contentTypeId = document.getElementById("search-content-id").value;
	if (parseInt(contentTypeId)) queryString += `&contentType=${contentTypeId}`;
	if (keyword) {
		queryString += `&keyword=${keyword}`;
	}
	let searchUrl = baseUrl + "?" + queryString + "&action=search";

	fetch(searchUrl)
		.then((response) => response.json())
		.then((data) => makeResultList(data));
}

function makeResultList(data) {
	let items = data;
	curSearchResult = items;
	let resultList = document.getElementById("result-list");
	while (resultList.hasChildNodes()) {
		resultList.removeChild(resultList.firstChild)
	}
	for (let i = 0; i <markerList.length; i++ ){
		markerList[i].setMap(null);
	}
	markerList = [];
	for (let i = 0; i < items.length; i++) {
		let newItem = document.createElement("div");
		newItem.setAttribute("id", i);
		newItem.setAttribute("class", "row p-2 position-relative");
		newItem.setAttribute("style", "font-size : 0.7rem");
		newItem.addEventListener("click", moveToTarget);
		newItem.innerHTML = `
    <button
    style="width: fit-content"
    class="rounded position-absolute end-0 bottom-0 m-3"
    type="button"
    onclick="choice(this)"
    >
      추가
    </button>
    <div class="row rounded-top bg-warning g-0">
    <div class="col fw-bold text-center" style="font-size: 1.2em">
      ${items[i].title}
    </div>
  </div>
  <div class="row rounded-bottom bg-light align-items-center g-0">
    <div class="col-5 gx-3">
      <img class="img-fluid rounded my-1" style="width:7rem; height:5rem" src="${items[i].img
			}" alt="" />
    </div>
    <div class="col-7 my-1">
      <p
        class="text-nowrap w-100 mb-1"
        style="text-overflow: ellipsis; overflow: hidden"
      >
      ${items[i].addr1}
      </p>
      <p style="font-size: 0.8em" class="mb-1">${items[i].zipcode}</p>
      <p style="font-size: 0.8em" class="mb-1">${items[i].tel == "" ? "전화번호없음" : items[i].tel
			}</p>
    </div>
  </div>
    `;
		resultList.appendChild(newItem);

		// 마커 이미지의 이미지 크기 입니다
		var imageSize = new kakao.maps.Size(24, 35);

		// 마커 이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(
			`assets/img/marker/type${items[i].contentType}.png`,
			imageSize
		);

		// 마커를 생성합니다
		let marker = new kakao.maps.Marker({
			map: map, // 마커를 표시할 지도
			position: new kakao.maps.LatLng(items[i].lat, items[i].lng), // 마커를 표시할 위치
			title: items[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
			image: markerImage, // 마커 이미지
		});
		marker.id = i;

		markerList.push(marker);
	}
	map.setCenter(new kakao.maps.LatLng(items[0].lat, items[0].lng));
}

function moveToTarget() {
	map.setCenter(markerList[this.getAttribute("id")].getPosition());
}

function deleteChoise(e) {
	shortPathOrderId = []
	let contentId = e.parentElement.getAttribute("contentId");
	let choiceElement = document.getElementById("choice-list");
	choiceElement.removeChild(e.parentElement);
	delete choiceList[contentId];
}

function choice(e) {
	shortPathOrderId = []
	let id = e.parentElement.getAttribute("id");
	if (!(curSearchResult[id].contentId in choiceList)) {
		let newChoice = { data: curSearchResult[id] };
		choiceList[curSearchResult[id].contentId] = newChoice;
	}
	draw();
}

function draw() {
	let choiceElement = document.getElementById("choice-list");
	let shortButton = document.createElement('button');
	shortButton.setAttribute("id", "btn-TSP")
	shortButton.setAttribute("class", "btn btn-outline-success btn-sm")
	shortButton.setAttribute("type", "button")
	shortButton.appendChild(document.createTextNode("최단경로 검색"))
	shortButton.addEventListener("click", tsp);
	while (choiceElement.hasChildNodes()) {
		choiceElement.removeChild(choiceElement.firstChild)
	}
	choiceElement.appendChild(shortButton)
	if (shortPathOrderId.length) {
		for (let idx = 0; idx < shortPathOrderId.length; idx++) {
			let curLoc = choiceList[shortPathOrderId[idx]].data;
			let newItem = document.createElement("div");
			newItem.setAttribute("contentId", shortPathOrderId[idx]);
			newItem.setAttribute("class", "row p-2 position-relative");
			newItem.setAttribute("style", "font-size : 0.7rem");
			newItem.addEventListener("click", moveToTarget);
			newItem.innerHTML = `
<button
			style="width: fit-content"
			class="rounded position-absolute end-0 bottom-0 m-3"
			type="button"
			onclick="deleteChoise(this)"
			>
			  삭제
			</button>
      <div class="row rounded-bottom rounded-4 bg-warning g-0">
      <div class="col fw-bold text-center" style="font-size: 1.2em">
        ${curLoc.title}
      </div>
    </div>
    <div class="row rounded-top rounded-4 bg-light align-items-center g-0">
      <div class="col-5 gx-3">
        <img class="img-fluid rounded my-1" style="width:7rem; height:5rem" src="${curLoc.img
				}" alt="" />
      </div>
      <div class="col-7 my-1">
        <p
          class="text-nowrap w-100 mb-1"
          style="text-overflow: ellipsis; overflow: hidden"
        >
        ${curLoc.addr1}
        </p>
        <p style="font-size: 0.8em" class="mb-1">${curLoc.zipcode}</p>
        <p style="font-size: 0.8em" class="mb-1">${curLoc.tel == "" ? "전화번호없음" : curLoc.tel
				}</p>
      </div>
    </div>
      `;

			choiceElement.appendChild(newItem);
		}
	} else {
		for (const key in choiceList) {
			let curLoc = choiceList[key].data;
			let newItem = document.createElement("div");
			newItem.setAttribute("contentId", key);
			newItem.setAttribute("class", "row p-2 position-relative");
			newItem.setAttribute("style", "font-size : 0.7rem");
			newItem.addEventListener("click", moveToTarget);
			newItem.innerHTML = `
			<button
			style="width: fit-content"
			class="rounded position-absolute end-0 bottom-0 m-3"
			type="button"
			onclick="deleteChoise(this)"
			>
			  삭제
			</button>
		  <div class="row rounded-bottom rounded-4 bg-warning g-0">
		  <div class="col fw-bold text-center" style="font-size: 1.2em">
			${curLoc.title}
		  </div>
		</div>
		<div class="row rounded-top rounded-4 bg-light align-items-center g-0">
		  <div class="col-5 gx-3">
			<img class="img-fluid rounded my-1" style="width:7rem; height:5rem" src="${curLoc.img
				}" alt="" />
		  </div>
		  <div class="col-7 my-1">
			<p
			  class="text-nowrap w-100 mb-1"
			  style="text-overflow: ellipsis; overflow: hidden"
			>
			${curLoc.addr1}
			</p>
			<p style="font-size: 0.8em" class="mb-1">${curLoc.zipcode}</p>
			<p style="font-size: 0.8em" class="mb-1">${curLoc.tel == "" ? "전화번호없음" : curLoc.tel
				}</p>
		  </div>
		</div>
		  `;

			choiceElement.appendChild(newItem);
		}
	}
}

var curDrawedLine = []
var shortPathOrderId = []
function tsp() {
	const dis = makeDisArray(choiceList);
	const n = dis.length;
	const graph = dis;
	let min = 1e9;
	let shortPathOrder = [];


	function get_dist(depth, start, cost, order) {	// 최소 비용을 구하는 함수
		if (depth === n) {
			if (cost + graph[start][0].getLength() < min) {
				shortPathOrder = order;
				min = cost + graph[start][0].getLength();
			}
			return;
		}
		for (let i = 0; i < n; i++) {
			if (start == i) continue;
			let isContinue = false;
			for (const num in order) {
				if (order[num] == i) {
					isContinue = true;
				}
			}
			if (isContinue) continue;
			get_dist(depth + 1, i, cost + graph[start][i].getLength(), [...order, i]);
		}
	}
	get_dist(0, 0, 0, [])
	for (let i = 0; i < curDrawedLine.length; i++) {
		curDrawedLine[i].setMap(null);
	}
	curDrawedLine = []
	shortPathOrderId = [graph[shortPathOrder[0]][shortPathOrder[0]].contentId]
	for (let i = 1; i < shortPathOrder.length; i++) {
		graph[shortPathOrder[i - 1]][shortPathOrder[i]].setMap(map);
		shortPathOrderId.push(graph[shortPathOrder[i - 1]][shortPathOrder[i]].contentId)
		curDrawedLine.push(graph[shortPathOrder[i - 1]][shortPathOrder[i]]);
	}
	draw()
}

function makeDisArray(locations) {
	let dis = [];
	for (const loc1 in locations) {
		let line = [];
		for (const loc2 in locations) {
			let path = []
			if (loc1 == loc2) {
				path = [
					new kakao.maps.LatLng(locations[loc1].data.lat, locations[loc1].data.lng),
					new kakao.maps.LatLng(locations[loc1].data.lat, locations[loc1].data.lng)
				]
			} else {
				path = [
					new kakao.maps.LatLng(locations[loc1].data.lat, locations[loc1].data.lng),
					new kakao.maps.LatLng(locations[loc2].data.lat, locations[loc2].data.lng)
				]
			}
			let polyline = new kakao.maps.Polyline({
				path: path, // 선을 구성하는 좌표배열 입니다
				strokeWeight: 5, // 선의 두께 입니다
				strokeColor: '#FFAE00', // 선의 색깔입니다
				strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
				strokeStyle: 'solid' // 선의 스타일입니다
			});
			polyline.title = locations[loc2].data.title
			polyline.contentId = loc2;
			line.push(polyline)
		}
		dis.push(line);
	}
	return dis;
}