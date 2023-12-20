import request from '@/utils/request'

// 查询Transfer_money列表
export function listTransfer_money(query) {
  return request({
    url: '/bank/transfer_money/list',
    method: 'get',
    params: query
  })
}

// 查询Transfer_money详细
export function getTransfer_money(id) {
  return request({
    url: '/bank/transfer_money/' + id,
    method: 'get'
  })
}

// 新增Transfer_money
export function addTransfer_money(data) {
  return request({
    url: '/bank/transfer_money',
    method: 'post',
    data: data
  })
}

// 修改Transfer_money
export function updateTransfer_money(data) {
  return request({
    url: '/bank/transfer_money',
    method: 'put',
    data: data
  })
}

// 删除Transfer_money
export function delTransfer_money(id) {
  return request({
    url: '/bank/transfer_money/' + id,
    method: 'delete'
  })
}
