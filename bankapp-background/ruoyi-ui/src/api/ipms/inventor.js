import request from '@/utils/request'

// 查询专利发明人信息列表
export function listInventor(query) {
  return request({
    url: '/ipms/inventor/list',
    method: 'get',
    params: query
  })
}

// 查询专利发明人信息详细
export function getInventor(patentinventorId) {
  return request({
    url: '/ipms/inventor/' + patentinventorId,
    method: 'get'
  })
}

// 新增专利发明人信息
export function addInventor(data) {
  return request({
    url: '/ipms/inventor',
    method: 'post',
    data: data
  })
}

// 修改专利发明人信息
export function updateInventor(data) {
  return request({
    url: '/ipms/inventor',
    method: 'put',
    data: data
  })
}

// 删除专利发明人信息
export function delInventor(patentinventorId) {
  return request({
    url: '/ipms/inventor/' + patentinventorId,
    method: 'delete'
  })
}
