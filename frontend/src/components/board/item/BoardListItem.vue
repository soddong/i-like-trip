<script setup>
import { timeAgo } from '@/util/time-utils';
import { defineProps, computed } from 'vue';

const props = defineProps({
   article: Object,
   image: String 
});

const timeSincePosted = computed(() => timeAgo(props.article.registerTime));

const imageUrl = computed(() => {
  return props.image || 'https://cdn.vuetifyjs.com/images/cards/sunshine.jpg';
});

</script>

<template>
  <v-card
    class="mx-auto my-5"
    width="500"
    height="460"
  > 
    <router-link
      :to="{ name: 'article-view', params: { articleno: article.articleNo } }"
      class="article-title link-dark"
    >
    <v-img
      height="350px"
      :src="imageUrl"
      cover
    ></v-img>
    </router-link>
    <v-card-title class="mb-4 mt-4">
      {{ article.subject }}
    </v-card-title>

    <v-card-subtitle class="d-flex justify-space-between">
      <div>{{ article.userName }}</div>
      <div>{{ timeSincePosted }}</div>
    </v-card-subtitle>
  </v-card>
</template>

<style scoped>
a {
  text-decoration: none;
}
.v-card-subtitle > div:first-child {
  margin-right: auto; 
}

.v-card-subtitle > div:last-child {
  margin-left: auto;
}
</style>
