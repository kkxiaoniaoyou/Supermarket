import { createRouter, createWebHistory } from 'vue-router';
import Layout from '../components/Layout.vue';
import Login from '../views/Login.vue';

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/admin',
    component: Layout,
    redirect: '/admin/user-management',
    children: [
      // {
      //   path: 'user-management',
      //   name: 'UserManagement',
      //   component: () => import('../views/admin/User.vue'),
      //   meta: { requiresAuth: true, permissions: ['admin'] },
      // },
      {
        path: 'product-management',
        name: 'ProductManagement',
        component: () => import('../views/admin/ProductManagement.vue'),
        meta: { requiresAuth: true, permissions: ['admin'] },
      },
      {
        path: 'inventory-management',
        name: 'InventoryManagement',
        component: () => import('../views/admin/InventoryManagement.vue'),
        meta: { requiresAuth: true, permissions: ['admin'] },
      },
      {
        path: 'employee-management',
        name: 'EmployeeManagement',
        component: () => import('../views/admin/EmployeeManagement.vue'),
        meta: { requiresAuth: true, permissions: ['admin'] },
      },
      {
        path: 'employee-management',
        name: 'EmployeeManagement',
        component: () => import('../views/admin/EmployeeManagement.vue'),
        meta: { requiresAuth: true, permissions: ['admin'] },
      },
      {
        path: 'productReservation-management',
        name: 'ProductReservationManagement',
        component: () => import('../views/admin/ProductReservationManagement.vue'),
        meta: { requiresAuth: true, permissions: ['admin'] },
      },
      {
        path: 'productReservation-management',
        name: 'ProductReservationManagement',
        component: () => import('../views/admin/ProductReservationManagement.vue'),
        meta: { requiresAuth: true, permissions: ['admin'] },
      },
      {
        path: 'productReservationDetail-management',
        name: 'ProductReservationDetailManagement',
        component: () => import('../views/admin/ProductReservationDetailManagement.vue'),
        meta: { requiresAuth: true, permissions: ['admin'] },
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;