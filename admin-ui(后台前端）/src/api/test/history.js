import request from '@/utils/request'

// 查询历史记录列表
export function listHistory(query) {
  return request({
    url: '/test/history/list',
    method: 'get',
    params: query
  })
}
//已读
export function readHistory(list) {
  return request({
    url: '/test/history/markAsRead',
    method: 'post',
    data:JSON.stringify(list)
  })
}
// 查询历史记录详细
export function getHistory(id) {
  return request({
    url: '/test/history/' + id,
    method: 'get'
  })
}

// 新增历史记录
export function addHistory(data) {
  return request({
    url: '/test/history',
    method: 'post',
    data: data
  })
}

// 修改历史记录
export function updateHistory(data) {
  return request({
    url: '/test/history',
    method: 'put',
    data: data
  })
}

// 删除历史记录
export function delHistory(id) {
  return request({
    url: '/test/history/' + id,
    method: 'delete'
  })
}
