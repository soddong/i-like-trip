import { localAxiosInstance, noneConfigAxios } from "@/util/http-commons";
const local = localAxiosInstance;
const noConfig = noneConfigAxios();

// place 정보

function getSido(success, fail) {
  local.get(`/address/sido`).then(success).catch(fail);
}

function getGugun(sido, success, fail) {
  local.get(`/address/gugun`, { params: { sido } }).then(success).catch(fail);
}

function getAttraction(filter, success, fail) {
  local.get(`/attraction`, { params: filter }).then(success).catch(fail);
}

function getPlanPath(data, success, fail) {
  noConfig
    .post(`https://apis-navi.kakaomobility.com/v1/waypoints/directions`, data, {
      headers: { Authorization: `KakaoAK 15f8cbb14448250a4ee361ad6c1d5bf2` },
    })
    .then(success)
    .catch(fail);
}

// Plan 정보

function detailPlan(planId, success, fail) {
  local.get(`/plans/${planId}`).then(success).catch(fail);
}

function listPlan(filter, success, fail) {
  local.get(`/plans`, { params: filter }).then(success).catch(fail);
}

function createPlan(newPlan, success, fail) {
  local.post(`/plans`, JSON.stringify(newPlan)).then(success).catch(fail);
}

export { getSido, getGugun, getAttraction, detailPlan, listPlan, createPlan, getPlanPath };
