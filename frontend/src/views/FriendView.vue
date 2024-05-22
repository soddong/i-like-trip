<template>
  <v-main class="fill-height top-margin">
    <v-row align="start" justify="center" class="fill-height">
      <v-col cols="12" md="6" class="fixed-height">
        <MyfriendList></MyfriendList>
      </v-col>
      <v-col cols="12" md="6" class="fixed-height">
        <MyfriendNews></MyfriendNews>
      </v-col>
    </v-row>
  </v-main>
</template>

<script setup>
import MyfriendNews from '@/components/friend/MyfriendNews.vue'
import MyfriendList from '@/components/friend/MyfriendList.vue'
</script>

<style>
.fill-height {
  height: 100vh;
}

.fixed-height {
  height: 800px;
}

.top-margin {
  margin-top: 60px;
}

.cover-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
