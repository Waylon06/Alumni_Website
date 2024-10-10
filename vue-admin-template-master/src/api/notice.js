import request from '@/utils/request'

export function listNotice(query) {
  return request({
    url: 'api/notice/list',
    method: 'get',
    params: query
  })
}

export function getNotice(nid) {
  return request({
    url: 'api/notice/findById',
    method: 'get',
    params: { nid: nid }
  })
}

export function addNotice(data) {
  return request({
    url: 'api/notice/add',
    method: 'get',
    params: data
  })
}

export function updateNotice(data) {
  return request({
    url: 'api/notice/change',
    method: 'post',
    params: data
  })
}

export function delNotice(nid) {
  return request({
    url: 'api/notice/del',
    method: 'post',
    params: { nid: nid }
  })
}