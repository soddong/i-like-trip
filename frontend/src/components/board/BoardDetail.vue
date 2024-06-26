<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle } from "@/api/board";
import { useUserStore } from "@/stores/user";

import BoardViewerItem from '@/components/board/item/BoardViewerItem.vue';

import '@/assets/styles.css';
import defaultProfile from '@/assets/default_profile.png';

const VITE_VUE_API_URL = import.meta.env.VITE_VUE_API_URL;

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();
const articleUserProfile = ref(defaultProfile);
const { articleno } = route.params;

const article = ref({});
const isMine = ref(false)

onMounted(() => {
  getArticle();
});

const getArticle = () => {
  detailArticle(
    articleno,
    ({ data }) => {
      article.value = data;
      console.log(article.value.profile)
      if (article.value.profile) {
        articleUserProfile.value = `${VITE_VUE_API_URL}upload?name=${article.value.profile}`;
      } else {
        articleUserProfile.value = defaultProfile;
      }
      if (article.value.userId === userStore.userId) {
        isMine.value = true
      }
      console.log("Article Data:", article.value);
      console.log("Updated userProfile URL:", articleUserProfile.value);
    },
    (error) => {
      console.log(error);
    }
  );
};

function moveList() {
  router.push({ name: "article-list" });
}

function moveModify() {
  router.push({ name: "article-modify", params: { articleno } });
}

function onDeleteArticle() {
  if (confirm('게시글을 정말 삭제하시겠습니까?')) {
    deleteArticle(
      articleno, article.value.userId,
      (response) => {
        if (response.status == 200) {
          moveList();
          alert('게시글이 삭제되었습니다')
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
</script>


<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="7">
        <div class="article-header">
          <h2 class="article-title">{{ article.subject }}</h2>
        </div>
        <div class="article-details">
          <v-avatar color="brown">
            <img :src="articleUserProfile" alt="Profile Image" class="cover-image">
          </v-avatar>
          <span class="user-name">{{ article.userName }}</span>
          <span class="register-time">{{ article.registerTime }}</span>
          <span class="hit">조회수 {{ article.hit }}회</span>
        </div>
        <v-divider></v-divider>
        <div class="article-content">
          <BoardViewerItem :content="article.content" />
        </div>
        <div class="article-actions" v-if="isMine">
          <v-col cols="6">
            <v-btn @click="onDeleteArticle" variant="tonal" class="mr-2">글삭제</v-btn>
          </v-col>
          <v-col cols="6">
            <v-btn @click="moveModify" color="primary" variant="tonal" class="mr-2">글수정</v-btn>
          </v-col>
        </div>

      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.common-card {
  padding: 20px;
  display: flex;
  flex-direction: column;
}

.article-header {
  margin-top: 50px;
  margin-bottom: 20px;
}

.article-title {
  font-size: 32px;
  font-weight: bold;
  color: #424242;
}

.article-details {
  display: flex;
  align-items: center;
  margin-top: 20px;
  margin-bottom: 20px;
}

.article-content {
  margin-top: 50px;
  margin-bottom: 30px;
  min-height: 300px;
  width: 100%;
}

.article-actions {
  margin-top: auto;
  display: flex;
  justify-content: flex-end;
}

.user-name,
.register-time,
.hit {
  margin-left: 20px;
  font-size: 16px;
  color: #666;
}

.v-btn {
  width: 100%;
  height: 50px;
}
</style>
