import {
    createApp
} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'
import ElementPlus from 'element-plus'
import "element-plus/dist/index.css";

import './styles/index.css'
import locale from 'element-plus/dist/locale/zh-cn.mjs'

createApp(App).use(router).use(store).use(ElementPlus, { locale }).mount('#app')