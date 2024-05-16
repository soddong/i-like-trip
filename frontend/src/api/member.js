import { localAxios, noneConfigAxios } from "@/util/http-commons";

const local = localAxios();

async function signIn(signInData, success, fail) {
  await local.post(`/member/sign-in`, signInData).then(success).catch(fail);
}

async function refreshTokenReq(refreshToken) {
  const instance = noneConfigAxios();
  try {
    const res = await instance.post(
      "/member/refresh",
      {},
      { headers: { Authorization: `Bearer ${refreshToken}` } }
    );
    if (res.status === 201) {
      return res.data.accessToken;
    }
  } catch (e) {
    throw new Error("액세스 토큰 재발급 실패");
  }
}

export { signIn, refreshTokenReq };
