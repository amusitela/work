import request from '@/utils/request'

// 查询代理机构信息列表
export function listAgency(query) {
  return request({
    url: '/ipms/agency/list',
    method: 'get',
    params: query
  })
}

// 查询代理机构信息详细
export function getAgency(agencyId) {
  return request({
    url: '/ipms/agency/' + agencyId,
    method: 'get'
  })
}

// 新增代理机构信息
export function addAgency(data) {
  return request({
    url: '/ipms/agency',
    method: 'post',
    data: data
  })
}

// 修改代理机构信息
export function updateAgency(data) {
  return request({
    url: '/ipms/agency',
    method: 'put',
    data: data
  })
}

// 删除代理机构信息
export function delAgency(agencyId) {
  return request({
    url: '/ipms/agency/' + agencyId,
    method: 'delete'
  })
}
