import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/Home.vue';
import LoginSignUp from './views/LogInSignUp.vue'; 
import Occurrence from '@/views/Occurrence.vue';
import LostFound from '@/views/LostFound.vue';
import Meeting from '@/views/Meeting.vue';
import Reserve from '@/views/Reserve.vue'; 

const routes = [
  { path: '/login', name: 'LoginSignUp', component: LoginSignUp },
  { path: '/', name: 'Home', component: Home },
  { path: '/occurrence', name: 'Occurrence', component: Occurrence },
  { path: '/lostfound', name: 'LostFound', component: LostFound },
  { path: '/meeting', name: 'Meeting', component: Meeting },
  { path: '/reserve', name: 'Reserve', component: Reserve },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
