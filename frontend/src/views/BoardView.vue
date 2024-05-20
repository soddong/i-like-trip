<script setup>
import BoardHeaderItem from '@/components/board/item/BoardHeaderItem.vue';
</script>

<template>
  <v-main>
    <v-container class="text-center mt-3">
      <BoardHeaderItem></BoardHeaderItem>
      <router-view></router-view>
    </v-container>
  </v-main>
</template>

<style>
</style>
