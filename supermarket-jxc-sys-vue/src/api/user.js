import { request } from '@/utils/axiosInstance.js'

export const getCaptchaNbr = (params) => {
    return request({
        url: '/captchaNbr',
        method: 'GET',
        params
    })
}
export const login = (data) => {
    return request({
        url: '/user/login',
        method: 'POST',
        data
    })
}
export const register = (data) => {
    return request({
        url: '/user/register',
        method: 'POST',
        data: {
            ...data,
            status: 0  // 0-正常 1-禁用
        }
    })
}

export const getList = (data) => {
    return request({
        url: '/user/pageList',
        method: 'POST',
        data
    })
}
export const getAllList = () => {
    return request({
        url: '/user/list',
    })
}
export const update = (data) => {
    return request({
        url: `/user/update`,
        method: 'POST',
        data
    })
}
export const delItem = (id) => {
    return request({
        url: `/user/${id}`,
        method: 'DELETE',
        params: null
    })
}
