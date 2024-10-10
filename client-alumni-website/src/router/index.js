import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import BackGround from '../components/BackGround'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'main',
        component: Main,
        redirect: 'home',
        children: [
            {
                path: '/home',
                name: 'home',
                component: () => import("@/views/HomeView")
            },
            {
                path: '/party',
                name: 'party',
                component: () => import("@/components/PartyComponent"),
                // children: [
                //     {
                //         path: '/party/activity',
                //         name: 'activity',
                //         component: () => import("@/components/ActivityInfoComponent"),
                //     }
                // ]
            },
            {
                path: '/notice',
                name: 'notice',
                component: () => import("@/components/NoticeComponent")
            },
            {
                path: '/user',
                name: 'user',
                component: () => import("@/components/UserComponent")
            },
            {
                path: '/activity',
                name: 'activity',
                component: () => import("@/components/ActivityInfoComponent"),
            },
            {
                path: '/student',
                name: 'student',
                component: () => import("@/components/StudentInfoComponent")
            },
        ]
    },
    {
        path: '/login',
        name: 'BackGround',
        component: BackGround,
        children: [
            {
                path: '/login',
                name: 'login',
                component: () => import('../views/LoginView')
            },
            {
                path: '/register',
                name: 'register',
                component: () => import('../views/RegisterView')
            }
        ]

    }
]


// 解决报错 此方法只针对VueRouter3.0以上
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
    mode: 'history',
    routes
})

router.beforeEach((to, from, next) => {
    console.log(to, from);
    // 可以使用if来判断是否放行
    // 放行
    if (to.path === '/student' || to.path === '/user') {
        if(sessionStorage.getItem('userData')) {
            next()
        }else {
            next('/login')
        }
    }else {
        next()
    }
})

export default router