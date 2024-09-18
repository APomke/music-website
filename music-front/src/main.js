import Vue from 'vue'
import App from './App.vue'
import store from '../store/index';

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//使用事件总线eventBus

Vue.use(ElementUI);

Vue.config.productionTip = false

new Vue({
  el: '#app',
  store,
  render: h => h(App)
});

// new Vue({
//   render: h => h(App),
// }).$mount('#app')
