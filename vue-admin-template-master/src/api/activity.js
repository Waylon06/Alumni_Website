import request from '@/utils/request'

export function listActivity(query) {
  return request({
    url: '/api/activity/list',
    method: 'get',
    params: query
  })
}

export function getActivity(activityId) {
    return request({
      url: '/api/activity/getActivityById',
      method: 'get',
      params: { 'aid': activityId }
    })
  }
  
  // 新增活动管理
  export function addActivity(data) {
    return request({
      url: '/api/activity/add',
      method: 'post',
      params: data
    })
    
  }
  
  // 修改活动管理
  export function updateActivity(data) {
    return request({
      url: '/api/activity/change',
      method: 'get',
      params: data
    })
  }
  
  // 删除活动管理
  export function delActivity(activityId) {
    return request({
      url: '/api/activity/del',
      method: 'delete',
      params: { 'aid': activityId }
    })
  }

  