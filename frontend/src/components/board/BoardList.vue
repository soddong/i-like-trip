<script setup>
import { ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { listArticle } from '@/api/board.js';
import { getMainImageUrl } from '@/api/image.js';
import BoardListItem from '@/components/board/item/BoardListItem.vue';
import BoardListEmptyItem from '@/components/board/item/BoardListEmptyItem.vue';
import VSearchInputInBoard from '@/components/common/VSearchInputInBoard.vue'

const router = useRouter();
const route = useRoute();
const articles = ref([]);
const hasError = ref(false);

const param = ref({
  keyword: "subject",
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

watch(() => route.query.word, (newWord) => {
  if (newWord) {
    loadArticles({ keyword: 'subject', word: newWord });
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
      <v-col cols="12" class="text-right">
        <v-btn rounded class="main-button" @click="moveWrite" color="#F0BBB1"
               style="width: 250px; font-size: 15px; color: white">게시글 작성하기</v-btn>
      </v-col>
      <v-col cols="12">
          <VSearchInputInBoard />
      </v-col>
      <v-col cols="12">
        <v-card class="common-card">
            <template v-if="articles.length > 0">
              <div class="grid-container">
                <div v-for="article in articles" :key="article.articleNo" class="grid-item">
                  <BoardListItem :article="article" :image="articleImages[article.articleNo]" />
                </div>
              </div>
            </template>
            <template v-else-if="hasError">
              <BoardListEmptyItem />
            </template>
            <template v-else>
              <BoardListEmptyItem />
            </template>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.common-card {
  border: none;
  box-shadow: none;
}


.grid-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  /* 한 줄에 네 개의 항목 */
  gap: 16px;
  /* 각 항목 사이의 간격 */
}

.grid-item {
  width: 100%;
}

</style>
