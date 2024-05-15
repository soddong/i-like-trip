<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

function goToBoardList() {
  router.go(-1); 
}
</script>


<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">
        <div class="background-image">
          <div class="overlay" @click="goToBoardList">
            <h1 class="header-title">자유게시판</h1>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.background-image {
  background-image: url('https://cdn.vuetifyjs.com/images/cards/docks.jpg');
  background-size: cover;
  background-position: center;
  height: 300px;
  position: relative;
}

.overlay {
  background-color: rgba(0, 0, 0, 0.5);
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.header-title {
  color: white;
  font-size: 40px; 
}

</style>