import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function signIn(signInData, success, fail) {
  await local.post(`/member/sign-in`, signInData).then(success).catch(fail);
}

export {
  signIn
};
