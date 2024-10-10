import Vue from 'vue'
import App from './App.vue'

import router from './router'
import store from './store'

import "swiper/css/swiper.min.css"
import { Container, Main, Header, Footer, Menu, MenuItem, Submenu, Switch, Row, Col, Button, Form, FormItem, Input, Upload, Dialog, Icon, Select, Option, Message, MessageBox, Notification, Empty } from 'element-ui'
import '@/assets/css/init.css'

Vue.use(Container)
Vue.use(Header)
Vue.use(Main)
Vue.use(Footer)
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Submenu)
Vue.use(Switch)
Vue.use(Row)
Vue.use(Col)
Vue.use(Button)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Upload)
Vue.use(Dialog)
Vue.use(Icon)
Vue.use(Select)
Vue.use(Option)
Vue.use(Empty)

Vue.prototype.$message = Message;
Vue.prototype.$msgbox = MessageBox;
Vue.prototype.$alert = MessageBox.alert;
Vue.prototype.$confirm = MessageBox.confirm;
Vue.prototype.$prompt = MessageBox.prompt;
Vue.prototype.$notify = Notification;

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
