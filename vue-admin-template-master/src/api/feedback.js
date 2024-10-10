import request from '@/utils/request'

export function listFeedback(query) {
  return request({
    url: '/api/feedback/list',
    method: 'get',
    params: query
  })
}

export function getFeedback(fid) {
  return request({
    url: '/api/feedback/findById',
    method: 'get',
    params: { fid: fid }
  })
}

export function addFeedback(data) {
  return request({
    url: '/api/feedback/add',
    method: 'post',
    params: data
  })
}

export function updateFeedback(data) {
  return request({
    url: '/api/feedback/change',
    method: 'post',
    params: data
  })
}

export function delFeedback(fid) {
  return request({
    url: '/api/feedback/del',
    method: 'get',
    params: { fid: fid }
  })
}