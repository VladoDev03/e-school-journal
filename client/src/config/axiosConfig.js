import axios from "axios";
import keycloak from "./keycloak";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8081/api",
  withCredentials: true,
});

axiosInstance.interceptors.request.use(
  async (config) => {
    try {
      if (!keycloak.authenticated) {
        await keycloak.login();
        return Promise.reject(new Error("Not authenticated"));
      }

      await keycloak.updateToken(5);

      if (keycloak.token) {
        config.headers["Authorization"] = `Bearer ${keycloak.token}`;
      }

      return config;
    } catch (error) {
      console.error("Failed to refresh token", error);

      if (keycloak.authenticated === false) {
        keycloak.login();
      }

      return Promise.reject(error);
    }
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      console.error("Unauthorized request, redirecting to login");
      keycloak.login();
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
