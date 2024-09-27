import axios from 'axios';
 


const baseURL = `${window.configObj.url}`
const apiClient = axios.create({
  baseURL: baseURL,
  headers: {
    'Content-Type': 'application/json'
  }
});
 
export default {
  // 获取指定用户的收藏目录
  get_collect(uid) {
    // 使用 params 对象来传递查询参数
    return apiClient.get('/collect/getCollectListByUid', {
      params: {
        uid: uid
      }
    });
  }
}
