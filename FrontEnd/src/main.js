import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue'
import Home from './views/Home.vue';
import Occurrence from './views/Occurrence.vue';
import LostFound from './views/LostFound.vue';
import Meeting from './views/Meeting.vue';
import Reserve from './views/CalendarReserve.vue'; 

const routes = [
  { path: '/', component: Home },
  { path: '/home', name: 'Home', component: Home },
  { path: '/occurrence', name: 'Occurrence', component: Occurrence },
  { path: '/lostfound', name: 'LostFound', component: LostFound },
  { path: '/meeting', name: 'Meeting', component: Meeting },
  { path: '/reserve', name: 'Reserve', component: Reserve },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});
export default router
createApp(App).use(router).mount('#app')
