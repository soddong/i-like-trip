import { ref } from "vue";
import { defineStore } from "pinia";
import { signIn, refreshTokenReq } from "@/api/member";
import { jwtDecode } from "jwt-decode";

export const useUserStore = defineStore("userStore", () => {
  const jwt = ref("");
  const userId = ref("");
  const isSignIn = ref(false);
  const isSignInError = ref(false);
  const userInfo = ref(null);

  const userSignIn = async (signInData) => {
    await signIn(
      signInData,
      (response) => {
        if (response.status === 201) {
          console.log("로그인 성공!!!!");
          let { data } = response;

          jwt.value = data["accessToken"];
          localStorage.setItem("refresh-token", data["refreshToken"]);

          let decodeToken = jwtDecode(jwt.value);
          userId.value = decodeToken.aud.pop();
          isSignIn.value = true;
          isSignInError.value = false;
        }
      },
      (error) => {
        console.log("로그인 실패!!!!");
        isSignIn.value = false;
        isSignInError.value = true;
        console.error(error);
      }
    );
  };

  const refreshToken = async () => {
    const refreshToken = localStorage.getItem("refresh-token");
    if (refreshToken) {
      try {
        jwt.value = await refreshTokenReq(refreshToken);
        isSignIn.value = true;
        isSignInError.value = false;
        let decodeToken = jwtDecode(jwt.value);
        userId.value = decodeToken.aud.pop();
      } catch (error) {
        userLogout();
        throw new Error("액세스 토큰 재발급 실패");
      }
    } else {
      throw new Error("리프레쉬 토큰이 존재하지 않습니다.");
    }
  };

  const userLogout = () => {
    localStorage.removeItem("refresh-token");
    jwt.value = "";
    userId.value = "";
    isSignIn.value = false;
    isSignInError.value = false;
    userInfo.value = null;
  };

  const getUserInfo = () => {};
  return {
    jwt,
    userId,
    isSignIn,
    isSignInError,
    userInfo,
    userSignIn,
    refreshToken,
    userLogout,
    getUserInfo,
  };
});
