import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import '@mdi/font/css/materialdesignicons.css'
import vuetify from './plugins/vuetify'
import { library } from '@fortawesome/fontawesome-svg-core'
import {   faFacebookSquare, faTwitter, faLinkedin, faYoutubeSquare,  } from '@fortawesome/free-brands-svg-icons'
import {  faShoppingCart, faKey, faMailBulk, faUser, faArrowRightToBracket,
   faArrowRightFromBracket, faStar, faBars, faPen, faDumbbell,faListNumeric, faCalendar} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faDashboard, faClock } from '@fortawesome/free-solid-svg-icons'

library.add(faFacebookSquare, faTwitter, 
  faShoppingCart, faKey, faMailBulk, faUser, faLinkedin, faYoutubeSquare,faListNumeric,
   faArrowRightToBracket, faArrowRightFromBracket, faStar, faBars, faPen, faDumbbell, faDashboard, faClock, faCalendar)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
