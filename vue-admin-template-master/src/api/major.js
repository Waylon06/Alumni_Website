import request from '@/utils/request'

export function updateMajor(data) {
  return request({
    url: '/api/major/change',
    method: 'post',
    params: data
  })
}

export function listMajor(query) {
  return request({
    url: '/api/major/list',
    method: 'get',
    params: query
  })
}

    export function delMajor(mid) {
      return request({
        url: '/api/major/del',
        method: 'post',
        params: { mid: mid }
      })
    }

    export function getMajor(mid) {
      return request({
        url: '/api/major/findById',
        method: 'get',
        params: { mid: mid }
      })
    }

    export function addMajor(data) {
      return request({
        url: '/api/major/add',
        method: 'post',
        params: data
      })
    }