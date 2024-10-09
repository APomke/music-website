import axios from 'axios';
 


const baseURL = `${window.configObj.url}`
const apiClient = axios.create({
  baseURL: baseURL,
  withCredentials: true, // 设置这个选项为true
  headers: {
    'Content-Type': 'application/json'
  }
});

// 请求拦截器
apiClient.interceptors.request.use(
  config => {
    // 从本地存储或其他地方获取 JWT
    const jwt = sessionStorage.getItem('jwt') || ''; // 假设 JWT 存储在本地存储中
    if (jwt) {
      // 如果 JWT 存在，则添加到请求头
      config.headers.Authorization = `Bearer ${jwt}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);
 
export default {
  // 登录
  login(data) {
    return apiClient.post(`/user/login`, data);
  },
  // 通过登录获取的jwt获取用户信息
  getUserInfo() {
    return apiClient.post(`/user/getUserInfo`);
  }
}
