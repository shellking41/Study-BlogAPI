import { createRouter, createWebHistory } from 'vue-router';
import MainLayout from '../layout/MainLayout.vue';
import Home from '../pages/Home.vue';
import About from '../pages/About.vue';

const routes = [
  {
    path: '/',
    children: [
      { path: '', component: Home },
      { path: '/about', component: About },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;