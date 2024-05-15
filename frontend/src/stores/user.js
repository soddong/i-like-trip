import { ref } from "vue";
import { defineStore } from "pinia";
import { signIn } from "@/api/member";
import { jwtDecode } from "jwt-decode";

export const useUserStore = defineStore("userStore", () => {
  const jwt = ref("");
  const userId = ref("");
  const isSignIn= ref(false)
  const isSignInError = ref(false)
  const userInfo = ref(null)

  const userSignIn = async (signInData) => {
    await signIn(
        signInData,
      (response) => {
        if (response.status === 201) {
          console.log("로그인 성공!!!!")
          let { data } = response
          let accessToken = data["accessToken"]
          let refreshToken = data["refreshToken"]
          let decodeToken = jwtDecode(accessToken)
          userId.value=decodeToken.aud.pop();
          isSignIn.value = true
          isSignInError.value = false
          jwt.value=accessToken
          localStorage.setItem("refresh-token", refreshToken)
        }
      },
      (error) => {
        console.log("로그인 실패!!!!")
        isSignIn.value = false
        isSignInError.value = true
        console.error(error)
      }
    )
  }

//   const userLogout = async () => {
//     console.log("로그아웃 아이디 : " + userInfo.value.userId)
//     await logout(
//       userInfo.value.userId,
//       (response) => {
//         if (response.status === httpStatusCode.OK) {
//           isLogin.value = false
//           userInfo.value = null
//           isValidToken.value = false

//           sessionStorage.removeItem("accessToken")
//           sessionStorage.removeItem("refreshToken")
//         } else {
//           console.error("유저 정보 없음!!!!")
//         }
//       },
//       (error) => {
//         console.log(error)
//       }
//     )
//   }
return {
    jwt,
    userId,
    isSignIn,
    isSignInError,
    userInfo,
    userSignIn
  }
});
