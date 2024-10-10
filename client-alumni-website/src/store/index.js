import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        isLogined: sessionStorage.getItem('userData') ? true : false,
        userData: {
            username: '',
            password: '',
            email: '',
            userId: '',
            avatar: '',
            admin: ''
        }
    },
    mutations: {
        changeIsLogined(state, val) {
            state.isLogined = val
          },
        changeUserData(state, val) {
            state.userData = val
        },
        changeName(state, val) {
            state.name = val
        }
    }
})