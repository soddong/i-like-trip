import axios from "axios";

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
        const refreshToken = localStorage.getItem("refresh-token");
        console.log(error.config, "액세스토큰 재발급")
        try {
          const res = await instance.post(
            "/member/refresh",
            {},
            { headers: { Authorization: `Bearer ${refreshToken}` } }
          );
          if (res.status === 201) {
            originalRequest._retry = true;
            originalRequest.headers['Authorization'] = `Bearer ${res.data.accessToken}`;
            return instance(originalRequest);
          }
        } catch (error) {
          return Promise.reject(error);
        }
      }
      return Promise.reject(error);
    }
  );
  return instance;
}

export { localAxios };
