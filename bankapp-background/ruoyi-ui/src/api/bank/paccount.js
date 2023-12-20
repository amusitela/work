import request from '@/utils/request'

// 查询paccount列表
export function listPaccount(query) {
  return request({
    url: '/bank/paccount/list',
    method: 'get',
    params: query
  })
}

// 查询paccount详细
export function getPaccount(id) {
  return request({
    url: '/bank/paccount/' + id,
    method: 'get'
  })
}

// 新增paccount
export function addPaccount(data) {
  return request({
    url: '/bank/paccount',
    method: 'post',
    data: data
  })
}

// 修改paccount
export function updatePaccount(data) {
  return request({
    url: '/bank/paccount',
    method: 'put',
    data: data
  })
}

// 删除paccount
export function delPaccount(id) {
  return request({
    url: '/bank/paccount/' + id,
    method: 'delete'
  })
}
