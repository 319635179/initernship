import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/login.vue';
import HomeView from '@/views/home.vue';
import ResultView from '@/views/result.vue';
import HistoryView from '@/views/history.vue';
import store from '@/store';

const routes = [
  {
    path: '/',
    name: 'def',
    redirect: HomeView,
  }, {
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      isAuth: false,
    }
  },{
    path: '/home',
    name: 'home',
    component: HomeView,
    meta: {
      isAuth: true,
    }
  },{
    path: '/result',
    name: 'result',
    component: ResultView,
    meta: {
      isAuth: true,
    }
  },{
    path: '/history',
    name: 'history',
    component: HistoryView,
    meta: {
      isAuth: false,
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if(!store.state.user.is_login && to.meta.isAuth){
    next({name: 'login'});
  }else{
    next();
  }
})

export default router
