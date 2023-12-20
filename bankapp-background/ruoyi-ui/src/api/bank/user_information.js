import request from '@/utils/request'

// 查询User_information列表
export function listUser_information(query) {
  return request({
    url: '/bank/user_information/list',
    method: 'get',
    params: query
  })
}

// 查询User_information详细
export function getUser_information(useId) {
  return request({
    url: '/bank/user_information/' + useId,
    method: 'get'
  })
}

// 新增User_information
export function addUser_information(data) {
  return request({
    url: '/bank/user_information',
    method: 'post',
    data: data
  })
}

// 修改User_information
export function updateUser_information(data) {
  return request({
    url: '/bank/user_information',
    method: 'put',
    data: data
  })
}

// 删除User_information
export function delUser_information(useId) {
  return request({
    url: '/bank/user_information/' + useId,
    method: 'delete'
  })
}
