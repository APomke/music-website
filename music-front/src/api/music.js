import axios from 'axios';
 


const baseURL = `${window.configObj.url}`
const apiClient = axios.create({
  baseURL: baseURL,
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
  // 获取推荐音乐
  getRecommendationsMusic() {
    return apiClient.get(`/music/recommendations`);
  },
  // 上传音乐
  uploadMusic(data,fileName,iconFileName) {
    return apiClient.post(`/music/addMusic?fileName=${encodeURIComponent(fileName)}&iconFileName=${encodeURIComponent(iconFileName)}`, data);
  }
}
