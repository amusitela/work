import request from '@/utils/request'

// 查询软件著作权登记列表
export function listSoftright(query) {
  return request({
    url: '/ipms/softright/list',
    method: 'get',
    params: query
  })
}

// 查询软件著作权登记详细
export function getSoftright(softrightId) {
  return request({
    url: '/ipms/softright/' + softrightId,
    method: 'get'
  })
}

// 新增软件著作权登记
export function addSoftright(data) {
  return request({
    url: '/ipms/softright',
    method: 'post',
    data: data
  })
}

// 修改软件著作权登记
export function updateSoftright(data) {
  return request({
    url: '/ipms/softright',
    method: 'put',
    data: data
  })
}

// 删除软件著作权登记
export function delSoftright(softrightId) {
  return request({
    url: '/ipms/softright/' + softrightId,
    method: 'delete'
  })
}
