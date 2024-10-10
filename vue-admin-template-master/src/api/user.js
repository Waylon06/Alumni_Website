import request from '@/utils/request'

// export function login(data) {
//   return request({
//     url: '/vue-admin-template/user/login',
//     method: 'post',
//     data
//   })
// }

// export function getInfo(token) {
//   return request({
//     url: '/vue-admin-template/user/info',
//     method: 'get',
//     params: { token }
//   })
// }

// export function logout() {
//   return request({
//     url: '/vue-admin-template/user/logout',
//     method: 'post'
//   })
// }

export function listUser(query) {
  return request({
    url: 'api/user/list',
    method: 'get',
    params: query
  })
}

export function getUser(userId) {
  return request({
    url: 'api/user/findById',
    method: 'get',
    params: { userId: userId }
  })
}

export function addUser(data) {
  return request({
    url: 'api/user/register',
    method: 'post',
      data
  })
}

export function updateUser(data) {
  return request({
    url: 'api/user/change',
    method: 'post',
    data
  })
}

export function delUser(userId) {
  return request({
    url: 'api/user/del',
    method: 'delete',
    params: { userId: userId }
  })
}
