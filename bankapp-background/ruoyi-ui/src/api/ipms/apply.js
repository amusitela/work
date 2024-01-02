import request from '@/utils/request'

// 查询专利申请审批列表
export function listApply(query) {
  return request({
    url: '/ipms/apply/list',
    method: 'get',
    params: query
  })
}

// 查询专利申请审批详细
export function getApply(patentapplyId) {
  return request({
    url: '/ipms/apply/' + patentapplyId,
    method: 'get'
  })
}

// 新增专利申请审批
export function addApply(data) {
  return request({
    url: '/ipms/apply',
    method: 'post',
    data: data
  })
}

// 修改专利申请审批
export function updateApply(data) {
  return request({
    url: '/ipms/apply',
    method: 'put',
    data: data
  })
}

// 删除专利申请审批
export function delApply(patentapplyId) {
  return request({
    url: '/ipms/apply/' + patentapplyId,
    method: 'delete'
  })
}

