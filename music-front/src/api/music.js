import axios from 'axios';
 


const baseURL = `${window.configObj.url}`
const apiClient = axios.create({
  baseURL: baseURL,
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
