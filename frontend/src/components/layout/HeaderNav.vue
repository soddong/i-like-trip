<script setup>
import { ref } from "vue";
import { RouterLink } from "vue-router";
import { useDisplay } from 'vuetify'

const { mdAndUp } = useDisplay()
const drawerOpen = ref(false);

const toggleDrawer = () => {
    drawerOpen.value = !drawerOpen.value;
};

const menuList = ref([
    {
        title: '여행 일정들',
                to: { name: "Board" } // Plan 에러나서 임시로
    },
    {
        title: '자유게시판',
        to: { name: "Board" }
    },
    {
        title: '또다른 메뉴',
        to: { name: "Board" } // Other 에러나서 임시로
    }
])

const tempLoginStatus=ref(true)
</script>

<template>
    <v-app-bar>
        <v-app-bar-title>
            <RouterLink :to="{name:'Home'}">
                <v-sheet class="d-inline-flex align-center">
                    <v-img :width="40" src="src/assets/logo.png" inline class="rounded-pill mr-2"></v-img>
                    조아요행
                </v-sheet>
            </RouterLink>
        </v-app-bar-title>


        <!-- Automatically collapses into dropdown menu -->
        <v-spacer></v-spacer>
        <v-app-bar-nav-icon @click="toggleDrawer" v-show="!mdAndUp"></v-app-bar-nav-icon>
        <v-btn-group v-show="mdAndUp">
            <v-btn v-for="menu in menuList" :key="menu.title" :to="menu.to">{{ menu.title }}</v-btn>
        </v-btn-group>
        <v-spacer></v-spacer>
        <v-btn v-if="tempLoginStatus" :to="{name:'sign-in'}">로그인</v-btn>
        <v-menu v-else min-width="120px"  rounded>
            <template v-slot:activator="{ props }">
                <v-btn icon v-bind="props" >
                    <v-avatar color="brown" >
                        <span class="text-h6">사용</span>
                    </v-avatar>
                </v-btn>
            </template>
            <v-card>
                <v-card-text>
                    <div class="mx-auto text-center">
                        <v-avatar color="brown">
                            <span class="text-h7">사용</span>
                        </v-avatar>
                        <h3>사용자</h3>
                        <p class="text-caption mt-1">
                            이메일@닷.컴
                        </p>
                        <v-divider class="my-1"></v-divider>
                        <v-btn variant="text" rounded>
                            내정보
                        </v-btn>
                        <v-divider class="my-1"></v-divider>
                        <v-btn variant="text" rounded>
                            로그아웃
                        </v-btn>
                    </div>
                </v-card-text>
            </v-card>
        </v-menu>
    </v-app-bar>
    <v-navigation-drawer v-model="drawerOpen" location="right" disableResizeWatcher>
        <v-list two-line>
            <v-list-item v-for="menu in menuList" :title="menu.title" :to="menu.to"></v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<style scoped></style>