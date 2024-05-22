<script setup>
import { ref, watch, onUnmounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { listArticle } from '@/api/board.js';
import { getMainImageUrl } from '@/api/image.js';
import BoardListItem from '@/components/board/item/BoardListItem.vue';
import BoardListEmptyItem from '@/components/board/item/BoardListEmptyItem.vue';

const scrollContainer = ref(null);
let isDragging = false;
let startX;
let scrollLeft;

const startDrag = (e) => {
  isDragging = true;
  startX = e.pageX - scrollContainer.value.offsetLeft;
  scrollLeft = scrollContainer.value.scrollLeft;
  scrollContainer.value.style.cursor = 'grabbing';
};

const onDrag = (e) => {
  if (!isDragging) return;
  e.preventDefault();
  const x = e.pageX - scrollContainer.value.offsetLeft;
  const walk = (x - startX) * 2; // 2는 드래그 속도 조절 계수
  scrollContainer.value.scrollLeft = scrollLeft - walk;
};

const stopDrag = () => {
  isDragging = false;
  scrollContainer.value.style.cursor = 'grab';
};

// 이벤트 리스너 등록
scrollContainer.value?.addEventListener('mousemove', onDrag);
scrollContainer.value?.addEventListener('mouseup', stopDrag);
scrollContainer.value?.addEventListener('mouseleave', stopDrag);

// 컴포넌트 언마운트 시 이벤트 리스너 제거
onUnmounted(() => {
  scrollContainer.value?.removeEventListener('mousemove', onDrag);
  scrollContainer.value?.removeEventListener('mouseup', stopDrag);
  scrollContainer.value?.removeEventListener('mouseleave', stopDrag);
});

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
        <v-col cols="12" class="text-right">
            <v-btn rounded class="main-button" @click="moveWrite" color="#F0BBB1"
            style=" width: 250px; font-size: 15px; color: white">게시글 작성하기</v-btn>
        </v-col>
        <v-col cols="12" style="overflow-x: scroll">
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