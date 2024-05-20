<script setup>
import { ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { listArticle } from '@/api/board.js';
import { getMainImageUrl } from '@/api/image.js';
import BoardListItem from '@/components/board/item/BoardListItem.vue';
import BoardListEmptyItem from '@/components/board/item/BoardListEmptyItem.vue';

const router = useRouter();
const route = useRoute();
const articles = ref([]);
const hasError = ref(false);
const param = ref({
  key: "subject",
  word: "",
});
const articleImages = ref({}); 

const loadArticles = (params) => {
  listArticle(params, ({ data }) => {
    articles.value = data.articles;
    hasError.value = false;
    articles.value.forEach(article => {
      getMainImageUrl(article.articleNo, (blob) => {
        const imageUrl = URL.createObjectURL(blob);
        articleImages.value[article.articleNo] = imageUrl;
      });
    });
  }, error => {
    console.error(error);
    hasError.value = true;
  });
};

watch(() => route.query.keyword, (newKeyword) => {
  if (newKeyword) {
    loadArticles({ key: 'subject', word: newKeyword });
  } else {
    loadArticles(param.value);
  }
}, { immediate: true });

const moveWrite = () => {
  router.push({ name: 'article-write' });
};

</script>

<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">
        <v-row>
          <v-col class="text-right"> 
            <v-btn class="main-button" @click="moveWrite" color="#072a40">게시글 작성하기</v-btn>
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-card class="common-card">
            <template v-if="articles.length > 0">
              <v-col v-for="article in articles" :key="article.articleNo" class="py-2">
                <BoardListItem :article="article" :image="articleImages[article.articleNo]" />
              </v-col>
            </template>
            <template v-else-if="hasError">
              <BoardListEmptyItem/>
            </template>
            <template v-else>
              <BoardListEmptyItem/>
            </template>
        </v-card>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.common-card {
  display: flex;
  flex-wrap: wrap; 
  justify-content: flex-start; 
  align-items: flex-start; 
  gap: 16px; 
  padding: 20px;
  border: none;
  box-shadow: none;
  
  /* box-shadow: 0 2px 12px rgba(0,0,0,0.1); */
}
</style>