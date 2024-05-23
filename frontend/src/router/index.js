import AccountView from "@/views/AccountView.vue";
import HomeView from "@/views/HomeView.vue";
import MypageView from "@/views/MypageView.vue";
import FriendView from "@/views/FriendView.vue";
import { createRouter, createWebHistory } from "vue-router";
import { useUserStore } from "@/stores/user";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "",
      name: "Home",
      component: HomeView,
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
          beforeEnter: (to, from) => {
            const userStore = useUserStore();
            if (userStore.isSignIn) return true;
            else {
              return { name: "sign-in" };
            }
          },
        },
        {
          path: "/board/:articleno",
          name: "article-modify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
      ],
      meta: {
        viewTitle: "리뷰 게시판",
      },
    },
    {
      path: "/account",
      name: "Account",
      component: AccountView,
      children: [
        {
          path: "sign-in",
          name: "sign-in",
          component: () => import("@/components/account/AccountSignIn.vue"),
        },
        {
          path: "sign-up",
          name: "sign-up",
          component: () => import("@/components/account/AccountSignUp.vue"),
        },
      ],
    },
    {
      path: "/mypage",
      name: "Mypage",
      component: MypageView,
      meta: {
        viewTitle: "마이페이지",
      },
    },
    {
      path: "/plan",
      name: "plan-list",
      component: () => import("@/components/plan/PlanList.vue"),
      meta: {
        viewTitle: "일정 공유",
      },
    },
    {
      path: "/plan/:planno",
      name: "plan-view",
      component: () => import("@/components/plan/PlanDetail.vue"),
      meta: {
        noHeader: true,
      },
    },
    {
      path: "/plan",
      name: "plan-write",
      component: () => import("@/components/plan/PlanMap.vue"),
      meta: {
        noHeader: true,
      },
      beforeEnter: (to, from) => {
        const userStore = useUserStore();
        if (userStore.isSignIn) return true;
        else {
          return { name: "sign-in" };
        }
      },
    },
    {
      path: "/plan/:planno",
      name: "plan-modify",
      component: () => import("@/components/plan/PlanModify.vue"),
      meta: {
        noHeader: true,
      },
    },
    {
      path: "/friend",
      name: "Friend",
      component: FriendView,
      meta: {
        viewTitle: "내친구",
      },
    },
    {
      path: "/myplan",
      name: "my-plan",
      component: () => import("@/components/myplan/MyPlanList.vue"),
      meta: {
        viewTitle: "내일정",
      },
    },
  ],
});

router.beforeEach(async (to) => {
  if (!(to.name === "sign-in" || to.name === "sign-up")) {
    const userStore = useUserStore();
    if (!userStore.isSignIn && !userStore.isInit) {
      console.log("액세스 토큰 재발급");
      try {
        await userStore.refreshToken();
        userStore.getUserInfo();
      } catch (error) {
        console.log("");
      }
    }
  }
});

export default router;
