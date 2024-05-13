<script setup>
import BoardHeaderItem from '@/components/board/item/BoardHeaderItem.vue';
</script>

<template>
  <v-main>
    <div class="container text-center mt-3">
    <BoardHeaderItem></BoardHeaderItem>
    <router-view></router-view>
  </div>
  </v-main>
</template>

<style>
mark.sky {
  background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
