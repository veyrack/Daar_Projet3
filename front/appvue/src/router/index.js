import Vue from 'vue'
import VueRouter from 'vue-router'
import AjoutCV from "../components/AjoutCV.vue"
import Recherche from "../components/Recherche.vue"

Vue.use(VueRouter)

export default new VueRouter({
    routes:[
        {
            path: '/ajoutcv',
            component: AjoutCV
        },
        {
            path: '/recherche',
            component: Recherche
        }
    ]
})