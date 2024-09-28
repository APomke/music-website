import Vue from 'vue'
import App from './App.vue'
import store from '../store/index';
import axios from 'axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//使用事件总线eventBus

Vue.use(ElementUI);

Vue.config.productionTip = false

axios.defaults.withCredentials = true //携带cookie
Vue.prototype.$axios = axios
new Vue({
  el: '#app',
  store,
  render: h => h(App)
});

// new Vue({
//   render: h => h(App),
// }).$mount('#app')
