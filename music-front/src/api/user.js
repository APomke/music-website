import axios from 'axios';
 


const baseURL = `${window.configObj.url}`
const apiClient = axios.create({
  baseURL: baseURL,
  withCredentials: true, // 设置这个选项为true
  headers: {
    'Content-Type': 'application/json'
  }
});
 
export default {
  // 登录
  login(data) {
    return apiClient.post(`/user/login`, data);
  },
}
