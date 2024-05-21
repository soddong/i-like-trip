<script setup>
import { ref, watch } from "vue";
import { RouterLink } from "vue-router";
import { useDisplay } from 'vuetify'
import { useUserStore } from "@/stores/user"
import defaultProfile from '@/assets/default_profile.png';

const VITE_VUE_API_URL = import.meta.env.VITE_VUE_API_URL;

const userProfile = ref(defaultProfile);
const { mdAndUp } = useDisplay()
const drawerOpen = ref(false);

const toggleDrawer = () => {
    drawerOpen.value = !drawerOpen.value;
};

const mainMenuList = ref([
    {
        title: '여행 일정들',
        to: { name: "plan-list" }
    },
    {
        title: '자유게시판',
        to: { name: "Board" }
    }
])

const myMenuList = ref([
    {
        title: '내친구',
        to: { name: "Friend" }
    },
    {
        title: '내계획',
        to: { name: "Mypage" }
    }
])

const userStore = useUserStore()

watch(
    () => userStore.userInfo?.profileImg,
    (newProfile) => {
        if (newProfile) {
            userProfile.value = `${VITE_VUE_API_URL}upload?name=${newProfile}`;
            console.log("Updated userProfile URL:", userProfile.value);
        }
    },
    { immediate: true }
);
</script>

<template>
    <v-app-bar color="#CCE2E8" elevation="0" absolute rounded="">
        <v-app-bar-title :style="{ fontSize: 'x-large', color: '#20435C', cursor: 'pointer' }"
            @click="$router.push({ name: 'Home' })">
            조아요행
        </v-app-bar-title>

        <!-- Automatically collapses into dropdown menu -->
        <v-spacer></v-spacer>
        <v-app-bar-nav-icon @click="toggleDrawer" v-show="!mdAndUp"></v-app-bar-nav-icon>

        <!-- Centered main menu -->
        <v-btn-group v-show="mdAndUp" class="button-group" variant="text">
            <v-btn v-for="menu in mainMenuList" :key="menu.title" :to="menu.to" class="main-button">
                {{ menu.title }}</v-btn>
        </v-btn-group>

        <!-- Sub menu -->
        <v-btn-group v-show="mdAndUp" class="button-group" variant="text">
            <v-btn v-for="menu in myMenuList" :key="menu.title" :to="menu.to" class="sub-button">
                {{ menu.title }}</v-btn>
        </v-btn-group>


        <v-btn v-if="!userStore.isSignIn" :to="{ name: 'sign-in' }">로그인</v-btn>
        <v-menu v-else min-width="120px" rounded>
            <template v-slot:activator="{ props }">
                <v-btn icon v-bind="props">
                    <v-avatar color="brown">
                        <img :src="userProfile" alt="Profile Image" class="cover-image">
                    </v-avatar>
                </v-btn>
            </template>
            <v-card>
                <v-card-text>
                    <div class="mx-auto text-center">
                        <v-avatar color="brown">
                            <img :src="userProfile" alt="Profile Image" class="cover-image">
                        </v-avatar>
                        <h3>{{ userStore.userInfo.name }}</h3>
                        <p class="text-caption mt-1">
                            {{ userStore.userInfo.email }}
                        </p>
                        <v-divider class="my-1"></v-divider>
                        <v-btn variant="text" :to="{ name: 'Mypage' }" rounded>
                            내정보
                        </v-btn>
                        <v-divider class="my-1"></v-divider>
                        <v-btn variant="text" rounded @click="userStore.userLogout">
                            로그아웃
                        </v-btn>
                    </div>
                </v-card-text>
            </v-card>
        </v-menu>
    </v-app-bar>
    <v-navigation-drawer v-model="drawerOpen" location="right" disableResizeWatcher>
        <v-list two-line>
            <v-list-item v-for="menu in mainMenuList" :title="menu.title" :to="menu.to" :key="menu.title"></v-list-item>
            <v-list-item v-for="menu in myMenuList" :title="menu.title" :to="menu.to" </v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<style scoped>
.v-sheet {
    background-color: #178ca4;
    align-items: center;
    display: flex;
    height: 100%;
}

.router-link-active {
    color: #f9f7f0;
    font-weight: bold;
}

.logo {
    color: #20435C;
}

.v-app-bar {
    height: 70px;
    /* 메뉴바의 높이를 조정 */
}

.v-btn {
    font-size: 1.1rem;
    /* 글씨 크기 조절 */
}


.button-group {
    color: #20435C;
    font-family: 'SeoulHangangM' !important;
}

.cover-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 50%;
}
</style>
