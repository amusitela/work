import request from '@/utils/request'

// 查询代理机构代理人信息列表
export function listAgent(query) {
  return request({
    url: '/ipms/agent/list',
    method: 'get',
    params: query
  })
}

// 查询代理机构代理人信息详细
export function getAgent(agentId) {
  return request({
    url: '/ipms/agent/' + agentId,
    method: 'get'
  })
}

// 新增代理机构代理人信息
export function addAgent(data) {
  return request({
    url: '/ipms/agent',
    method: 'post',
    data: data
  })
}

// 修改代理机构代理人信息
export function updateAgent(data) {
  return request({
    url: '/ipms/agent',
    method: 'put',
    data: data
  })
}

// 删除代理机构代理人信息
export function delAgent(agentId) {
  return request({
    url: '/ipms/agent/' + agentId,
    method: 'delete'
  })
}
