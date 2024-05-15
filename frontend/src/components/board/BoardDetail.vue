<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle } from "@/api/board";

import BoardViewerItem from '@/components/board/item/BoardViewerItem.vue'; 

import '@/assets/styles.css'; 

const route = useRoute();
const router = useRouter();

const { articleno } = route.params;

const article = ref({});

onMounted(() => {
  getArticle();
});

const getArticle = () => {
  detailArticle(
    articleno,
    ({ data }) => {
      article.value = data;
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
  deleteArticle(
    articleno,
    (response) => {
      if (response.status == 200) moveList();
    },
    (error) => {
      console.log(error);
    }
  );
}
</script>

<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">
        <v-card class="common-card">
          <div class="article-header">
            <h2 class="article-title">{{ article.subject }}</h2>
          </div>
          <div class="article-details">
            <v-avatar color="brown" size="large">
              <span class="text-h5">사용</span>
            </v-avatar>
            <span class="user-name">{{ article.userName }}</span>
            <span class="register-time">{{ article.registerTime }}</span>
            <span class="hit">조회수 {{ article.hit }}회</span>
          </div>
          <div class="article-content">
            <BoardViewerItem :content="article.content" />
          </div>
          <div class="article-actions">
            <v-btn @click="moveModify" variant="outlined" class="mr-2">글수정</v-btn>
            <v-btn @click="onDeleteArticle" variant="outlined" class="mr-2">글삭제</v-btn>
          </div>
        </v-card>
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
  margin-bottom: 20px;
}

.article-content {
  margin-top: 50px;
  margin-bottom: 30px;
  width: 100%;
}

.article-actions {
  margin-top: auto;
  display: flex;
  justify-content: flex-end;
}

.user-name, .register-time, .hit {
  margin-left: 20px;
  font-size: 16px;
  color: #666;
}
</style>
