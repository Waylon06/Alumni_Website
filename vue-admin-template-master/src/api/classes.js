import request from '@/utils/request'

export function listClasses(query) {
  return request({
    url: '/api/classes/list',
    method: 'get',
    params: query
  })
}

export function delClasses(cid) {
  return request({
    url: '/api/classes/del',
    method: 'delete',
    params: { cid: cid }
  })
}

export function updateClasses(data) {
  return request({
    url: '/api/classes/change',
    method: 'post',
    params: data
  })
}

export function addClasses(data) {
  return request({
    url: '/api/classes/add',
    method: 'post',
    params: data
  })
}

export function getClasses(cid) {
  return request({
    url: '/api/classes/findById',
    method: 'get',
    params: { cid: cid }
  })
}