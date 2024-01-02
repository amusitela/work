import request from '@/utils/request'

// 查询专利申请人信息列表
export function listApplicant(query) {
  return request({
    url: '/ipms/applicant/list',
    method: 'get',
    params: query
  })
}

// 查询专利申请人信息详细
export function getApplicant(patentapplicantId) {
  return request({
    url: '/ipms/applicant/' + patentapplicantId,
    method: 'get'
  })
}

// 新增专利申请人信息
export function addApplicant(data) {
  return request({
    url: '/ipms/applicant',
    method: 'post',
    data: data
  })
}

// 修改专利申请人信息
export function updateApplicant(data) {
  return request({
    url: '/ipms/applicant',
    method: 'put',
    data: data
  })
}

// 删除专利申请人信息
export function delApplicant(patentapplicantId) {
  return request({
    url: '/ipms/applicant/' + patentapplicantId,
    method: 'delete'
  })
}
