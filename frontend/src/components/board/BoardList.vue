<script setup>
import { ref, watch, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { listArticle } from '@/api/board.js';
import VSelect from '@/components/common/VSelect.vue';
import BoardListItem from '@/components/board/item/BoardListItem.vue';

const router = useRouter();
const route = useRoute();
const articles = ref([]);
const param = ref({
  key: "subject",
  word: "",
});

watch(() => route.query.keyword, (newKeyword) => {
  if (newKeyword) {
    listArticle({ key: 'subject', word: newKeyword }, ({ data }) => {
      articles.value = data.articles;
    }, error => console.error(error));
  } else {
    listArticle(param.value, ({ data }) => {
      articles.value = data.articles;
    }, error => console.error(error));
  }
}, { immediate: true }); 

const changeKey = val => {
  param.value.key = val;
};

const getArticleList = () => {
  listArticle(param.value, ({ data }) => {
    articles.value = data.articles;
  }, error => console.error(error));
};

const moveWrite = () => {
  router.push({ name: 'article-write' });
};
</script>

<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" md="10">
        <v-row>
          <v-col class="text-right"> 
            <v-btn class="main-button" @click="moveWrite">글쓰기 (임시버튼)</v-btn>
          </v-col>
        </v-row>
        <v-row justify="start">
          <v-col v-for="article in articles" :key="article.articleNo" class="py-2">
            <BoardListItem
              :article="article"
            />
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
</style>