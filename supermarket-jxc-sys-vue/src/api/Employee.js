import { request } from '@/utils/axiosInstance.js'
const base = 'Employee'
export const getList = (data) => {
    return request({
        url: `/${base}/page`,
        method: 'POST',
        data
    })
}
export const getAllList = (data) => {
    return request({
        url: `/${base}/list`,
        method: 'POST',
        data
    })
}
export const delItem = (data) => {
    return request({
        url: `/${base}/deleteById`,
        method: 'POST',
        data
    })
}
export const addItem = (data) => {
    return request({
        url: `/${base}/add`,
        method: 'POST',
        data
    })
}
export const updateItem = (data) => {
    return request({
        url: `/${base}/update`,
        method: 'POST',
        data
    })
}
export const download = (data) => {
    return request({
        url: `/file/download`,
        method: 'POST',
        data
    })
}