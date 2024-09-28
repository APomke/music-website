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
  // 获取指定用户的收藏目录
  get_collect(uid) {
    // 使用 params 对象来传递查询参数
    return apiClient.get('/collect/getCollectListByUid', {
      params: {
        uid: uid
      }
    });
  },
  // 收藏指定音乐
  collect_music(music) {
    return apiClient.post('/collect/CollectMusic',music)
  }
}
