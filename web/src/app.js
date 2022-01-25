

import App from "./index"
import VueRouter from 'vue-router'
import Toasted from 'vue-toasted'
import Vue from "vue"

import Axios from "axios"
import Login from "./pages/Login"
import Register from "./pages/Register"


Vue.use(window.VueRouter = VueRouter)

Vue.use(Toasted, {position: 'bottom-right', duration: 4000})


Vue.prototype.$http = Axios


const router = new VueRouter({
    mode: 'hash',
    scrollBehavior (to, from, savedPosition) {
        return { x: 0, y: 0 }
    },
    // linkExactActiveClass: "active"
})


const routes = [
    { component: Login, path: "/login" },
    { component: Register, path: "/register" }
]

router.addRoutes(routes)


const app = new Vue({
    router,
    components: {App},
    template: '<app></app>'
}).$mount('#app')