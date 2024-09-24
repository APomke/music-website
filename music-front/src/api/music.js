import axios from 'axios';
 
const apiClient = axios.create({
  baseURL: 'http://localhost:8081',
  headers: {
    'Content-Type': 'application/json'
  }
});
 
export default {
  // 获取推荐音乐
  getRecommendationsMusic() {
    return apiClient.get(`/music/recommendations`);
  },
  // 上传音乐
  uploadMusic(data) {
    return apiClient.post(`/music/upload`, data);
  }
}
