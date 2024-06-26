import { localAxiosInstance } from "@/util/http-commons";

const local = localAxiosInstance;

function listArticle(param, success, fail) {
  local.get(`/board`, { params: param }).then(success).catch(fail);
}

function detailArticle(articleno, success, fail) {
  local.get(`/board/${articleno}`).then(success).catch(fail);
}

function registArticle(article, success, fail) {
  console.log("boardjs article", article);
  local.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  local.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, registUserId, success, fail) {
  local.delete(`/board/${articleno}`, { data: { registUserId } }).then(success).catch(fail);
}

export { listArticle, detailArticle, registArticle, modifyArticle, deleteArticle };
