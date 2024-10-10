import request from '@/utils/request'

export function listStudent(query) {
  return request({
    url: 'api/student/list',
    method: 'get',
    params: query
  })
}

export function getStudent(sid) {
  return request({
    url: 'api/student/findById',
    method: 'get',
    params: { sid: sid }
  })
}

export function addStudent(data) {
  return request({
    url: 'api/student/add',
    method: 'post',
    params: data
  })
}

export function updateStudent(data) {
  return request({
    url: 'api/student/change',
    method: 'put',
    params: data
  })
}

export function delStudent(sid) {
  return request({
    url: 'api/student/del',
    method: 'delete',
    params: { sid: sid }
  })
}