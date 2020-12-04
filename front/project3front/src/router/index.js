import Vue from 'vue'
import VueRouter from 'vue-router'
import Accueil from "../components/Accueil.vue"
import Recherche from "../components/Recherche.vue"

Vue.use(VueRouter)

export default new VueRouter({
    routes:[
        {
            path: '/',
            component: Accueil
        },
        {
            path: '/recherche',
            component: Recherche
        }
    ]
})