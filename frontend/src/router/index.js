import AccountView from "@/views/AccountView.vue"
import HomeView from "@/views/HomeView.vue"
import MypageView from "@/views/MypageView.vue"
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '',
      name: 'Home',
      component:HomeView
    },
    {
      path: "/board",
      name: "Board",
      component: () => import("../views/BoardView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          path: "/board",
          name: "article-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "/board/:articleno",
          name: "article-view",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        {
          path: "/board",
          name: "article-write",
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        {
          path: "/board/:articleno",
          name: "article-modify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
      ],
    },
    {
      path:"/account",
      name:"account",
      component:AccountView,

      children:[
        {
          path:"signin",
          name:"sign-in",
          component:() => import("@/components/account/AccountSignIn.vue"),
        },
        {
          path:"signup",
          name:"sign-up",
          component:() => import("@/components/account/AccountSignUp.vue"),
        }
      ]
    },
    {
      path:"/mypage",
      name:"Mypage",
      component:MypageView,

    }
  ]
})

export default router
