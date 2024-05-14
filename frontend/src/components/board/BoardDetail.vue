<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle } from "@/api/board";

import BoardViewerItem from '@/components/board/item/BoardViewerItem.vue'; 

const route = useRoute();
const router = useRouter();

// const articleno = ref(route.params.articleno);
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
        <div class="header">
          <v-row>
            <v-col>
              <span class="title">{{ article.subject }}</span>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="8">
              <v-avatar color="brown" size="large">
                <span class="text-h5">사용</span>
              </v-avatar>
              <span class="user-name">{{ article.userName }}</span>
              <span class="register-time">{{ article.registerTime }}</span>
            </v-col>
            <v-col cols="1">
              <span class="hit">조회수  {{ article.hit }} 회</span>
            </v-col>
          </v-row>
        </div>
        <BoardViewerItem :content="article.content" />
        
          <v-row>
          <v-col cols="5"></v-col>
            <v-col>
              <v-btn @click="moveModify" variant="outlined" class="mr-2">
                글수정
              </v-btn>
              <v-btn @click="onDeleteArticle" variant="outlined" class="mr-2">
                글삭제
              </v-btn>
            </v-col>
          </v-row>
      </v-col>
    </v-row>
  </v-container>


</template>

<style scoped>
  .img {
    margin-bottom:20px;
    border-radius: 1%;
    max-height: 400px;
    width: 100%;
  }

  .header {
    margin: 20px;
    width: 100%;
  }

  .content {
    margin: 50px;
    display: left ;
    align-items: center;
     width: 100%;
  }

  .text-content {
    font-size: 25px;
    padding: 20px;
    text-align: left; 
    width: 100%; 
  }

  .title {
    font-size: 40px;
    font-weight: bold;
  }

.register-time, .hit {
  margin-left: 20px;
  font-size: 18px;
  color: gray;
}  

.user-name{
  margin-left: 20px;
  font-size: 18px;
  color: rgb(0, 0, 0);
}  


</style>
