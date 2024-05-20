import { localAxiosInstance } from "@/util/http-commons";

const local = localAxiosInstance;

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


// Plan 정보

function getPlan(planId, success, fail) {
  local.get(`/plans/${planId}`).then(success).catch(fail);
}

function listPlan(filter, success, fail) {
  local.get(`/plans`, { params: filter }).then(success).catch(fail);
}

function createPlan(newPlan, success, fail) {
  local.post(`/plans`, newPlan).then(success).catch(fail);
}

export { getSido, getGugun, getAttraction 
        ,getPlan, listPlan, createPlan };
