import axios from "axios";
import { useUserStore } from "@/stores/user";

const { VITE_VUE_API_URL } = import.meta.env;

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });

  instance.interceptors.response.use(
    (response) => {
      // 2xx 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
      // 응답 데이터가 있는 작업 수행
      return response;
    },
    async (error) => {
      // 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
      // 응답 오류가 있는 작업 수행
      const originalRequest = error.config;

      if (error.response.status === 403 && !originalRequest._retry) {
        try {
          const userStore = useUserStore();
          userStore.refreshToken();
          const newAccessToken = userStore.jwt;
          originalRequest._retry = true;
          originalRequest.headers["Authorization"] = `Bearer ${newAccessToken}`;
          return instance(originalRequest);
        } catch (error) {
          return Promise.reject(error);
        }
      }
      return Promise.reject(error);
    }
  );
  return instance;
}

function noneConfigAxios() {
  return axios.create({
    baseURL: VITE_VUE_API_URL,
  });
}

export { localAxios, noneConfigAxios };
