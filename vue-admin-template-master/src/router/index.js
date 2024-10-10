import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import ElementUI from 'element-ui'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  // {
  //   path: '/login',
  //   component: () => import('@/views/login/index'),
  //   hidden: true
  // },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/activity',
    component: Layout,
    redirect: '/activity/index',
    name: 'Activity',
    meta: { title: '活动', icon: 'el-icon-s-help' },
    children: [{
      path: 'index',
      name: 'Activity',
      component: () => import('@/views/activity/index'),
      meta: { title: '活动管理' }
    }]
  },

  {
    path: '/student',
    component: Layout,
    redirect: '/student/index',
    name: 'Student',
    meta: { title: '学生', icon: 'el-icon-s-help' },
    children: [{
      path: 'index',
      name: 'Student',
      component: () => import('@/views/student/index'),
      meta: { title: '学生管理' }
    }]
  },

  {
    path: '/user',
    component: Layout,
    redirect: '/user/index',
    name: 'User',
    meta: { title: '用户', icon: 'el-icon-s-help' },
    children: [{
      path: 'index',
      name: 'User',
      component: () => import('@/views/user/index'),
      meta: { title: '用户管理' }
    }]
  },

  {
    path: '/notice',
    component: Layout,
    redirect: '/notice/index',
    name: 'Notice',
    meta: { title: '通知', icon: 'el-icon-s-help' },
    children: [{
      path: 'index',
      name: 'Notice',
      component: () => import('@/views/notice/index'),
      meta: { title: '通知管理' }
    }]
  },
  {
    path: '/major',
    component: Layout,
    redirect: '/major/index',
    name: 'Major',
    meta: { title: '专业', icon: 'el-icon-s-help' },
    children: [{
      path: 'index',
      name: 'Major',
      component: () => import('@/views/major/index'),
      meta: { title: '专业管理' }
    }]
  },
  {
    path: '/class',
    component: Layout,
    redirect: '/classes/index',
    name: 'Class',
    meta: { title: '班级', icon: 'el-icon-s-help' },
    children: [{
      path: 'index',
      name: 'Class',
      component: () => import('@/views/classes/index'),
      meta: { title: '班级管理' }
    }]
  },
  {
    path: '/feedback',
    component: Layout,
    redirect: '/feedback/index',
    name: 'Feedback',
    meta: { title: '反馈', icon: 'el-icon-s-help' },
    children: [{
      path: 'index',
      name: 'Feedback',
      component: () => import('@/views/feedback/index'),
      meta: { title: '反馈管理' }
    }]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

router.beforeEach((to, from, next) => {
  if (to.path === '/dashboard') {
    console.log(to.query.token);
    if (to.query.token) {
      sessionStorage.setItem('avatar', to.query.token)
      next()
    }
    else if (sessionStorage.getItem('avatar')) {
      next()
    }
    else {
      alert('请先登录管理员账号（￣▽￣）')
      window.location.href = 'http://localhost:8080/login?value=false'
    }
  } else {
    next()
  }

})

export default router
