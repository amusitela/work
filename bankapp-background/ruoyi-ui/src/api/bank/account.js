import request from '@/utils/request'

// 查询Account列表
export function listAccount(query) {
  return request({
    url: '/bank/account/list',
    method: 'get',
    params: query
  })
}

// 查询Account详细
export function getAccount(id) {
  return request({
    url: '/bank/account/' + id,
    method: 'get'
  })
}

// 新增Account
export function addAccount(data) {
  return request({
    url: '/bank/account',
    method: 'post',
    data: data
  })
}

// 修改Account
export function updateAccount(data) {
  return request({
    url: '/bank/account',
    method: 'put',
    data: data
  })
}

// 删除Account
export function delAccount(id) {
  return request({
    url: '/bank/account/' + id,
    method: 'delete'
  })
}
