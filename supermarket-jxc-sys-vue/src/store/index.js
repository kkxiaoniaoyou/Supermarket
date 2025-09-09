import { createStore } from 'vuex';

const store = createStore({
    state() {
        return {
            // 假设这里存储着当前用户的权限
            userPermissions: ['admin'], // 这里应该动态设置为当前登录用户的权限
            userInfo: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')) : {
                userId: 1
            },
            // 定义所有可能的导航项
            navigation: [
                // { name: '人员管理', path: '/admin/user-management', permissions: ['admin'] },
                { name: '商品管理', path: '/admin/product-management', permissions: ['admin'] },
                { name: '库存管理', path: '/admin/inventory-management', permissions: ['admin'] },
                { name: '员工管理', path: '/admin/employee-management', permissions: ['admin'] },
                { name: '商品预约管理', path: '/admin/productReservation-management', permissions: ['admin'] },
                { name: '商品预约详情管理', path: '/admin/productReservationDetail-management', permissions: ['admin'] },
            ],
        };
    },
    getters: {
        // 根据用户的权限来过滤导航项
        accessibleNavigation(state) {
            return state.navigation.filter(navItem => {
                // 如果导航项没有指定权限，则它是公共的，否则需要检查用户权限
                return navItem.permissions.length === 0 ||
                    navItem.permissions.some(permission => state.userPermissions.includes(permission));
            });
        },
    },
    mutations: {
        changeUserPermissions(state, payload) {
            state.userPermissions = [payload]
        },
        setUser(state, payload) {
            state.userInfo = {
                ...payload
            }
        }
    }
    // ...可能还需要一些 mutations 和 actions 来更新用户权限等...
});

export default store;
