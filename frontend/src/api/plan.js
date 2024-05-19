import { localAxios, localAxiosInstance } from "@/util/http-commons";

const local = localAxiosInstance;

function getSido(success, fail) {
  local.get(`/address/sido`).then(success).catch(fail);
}

function getGugun(sido, success, fail) {
  local.get(`/address/gugun`, { params: { sido } }).then(success).catch(fail);
}

function getAttraction(filter, success, fail) {
  local.get(`/attraction`, { params: filter }).then(success).catch(fail);
}

export { getSido, getGugun, getAttraction };
