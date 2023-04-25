import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Exercise from "../views/Exercise.vue";
import Trainer from "../views/Trainer.vue";
import trainerPage from '../views/TrainerPage.vue'
import userPage from '../views/UserPage.vue'
import About from '../views/About.vue'
import Faq from '../views/Faq.vue'
import Contact from '../views/Contact.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/exercise",
      name: "Exercise",
      component: Exercise,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/trainer",
      name: "Trainer",
      component: Trainer,
      meta: {
        requiresAuth: true,
        title: "Dashboard",
      },
    },
    {
      path: "/user/:id",
      name: "user",
      component: userPage,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/trainer/:id",
      name: "trainer",
      component:
       trainerPage,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/about',
      name: 'about',
      component: About
    },
    {
      path: '/faq',
      name: 'faq',
      component: Faq
    },
    {
      path: '/contact',
      name: 'contact',
      component: Contact
    },
  ],
});

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
