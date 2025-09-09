import axios from 'axios'

const instance = axios.create({
    baseURL: '/api',
    timeoute: 2000
})
instance.interceptors.request.use((config) => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers['Authorization'] = token
    }
    return config
}, (error) => {
    return Promise.reject(error)
})

instance.interceptors.response.use(response => {
    return response
}, (error) => {
    return error.response
})
const request = async ({ url, method = 'GET', data = {}, params = {}, headers = {} }) => {
    const config = {
        method,
        url,
        headers,
        ...(method === 'GET' ? { params } : { data })
    }
    const res = await instance(config)
    return res.data
}
export default instance
export { request }