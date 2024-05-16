import { localAxios, noneConfigAxios } from "@/util/http-commons";

const local = localAxios();
const noneConfiginstance = noneConfigAxios();

async function signIn(signInData, success, fail) {
  await local
    .post(`/member/sign-in`, signInData)
    .then(success)
    .then((jwt) => {
      local.defaults.headers["Authorization"] = `Bearer ${jwt}`;
    })
    .catch(fail);
}

async function refreshTokenReq(refreshToken) {
  try {
    const res = await noneConfiginstance.post(
      "/member/refresh",
      {},
      { headers: { Authorization: `Bearer ${refreshToken}` } }
    );
    if (res.status === 201) {
      local.defaults.headers["Authorization"] = `Bearer ${res.data.accessToken}`;
      return res.data.accessToken;
    }
  } catch (e) {
    throw new Error("액세스 토큰 재발급 실패");
  }
}

async function signUp(signUpData, success, fail) {
  await noneConfiginstance.post(`/member/sign-up`, signUpData).then(success).catch(fail);
}

async function getUserInfoReq(userId, success, fail) {
  await local.get(`/member/${userId}`).then(success).catch(fail);
}

export { signIn, refreshTokenReq, signUp, getUserInfoReq };
