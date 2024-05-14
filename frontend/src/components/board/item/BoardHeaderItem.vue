<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import VSelect from '@/components/common/VSelect.vue';
import { mdiMagnify } from '@mdi/js';
import VSearchInput from '@/components/common/VSearchInput.vue'; 

const router = useRouter();
</script>

<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" md="10">
        <!-- <v-carousel cycle>
          <v-carousel-item src="https://cdn.vuetifyjs.com/images/cards/docks.jpg" cover></v-carousel-item>
          <v-carousel-item src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" cover></v-carousel-item>
        </v-carousel>
        <div class="main-comment-overlay">
          <span>인조이, 유얼 트립</span>
        </div> -->
        <div>
          
        </div>
        <div class="search-overlay">
          <!-- <VSearchInput @input="handleInput" /> -->
          
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.mb-4 {
  margin-bottom: 16px;
}

.title {
  font-size: 40px;
  margin-bottom: 30px;
}

.search-input::placeholder {
  color: #aaa; 
}

.search-button {
    border: 2px solid #178ca4; 
    color:#333;
    height: 50px;
}

.search-overlay {
  position: absolute;
  top: 400px; 
  left: 35%;
  width: 60%; 
  z-index: 2;
}

.main-comment-overlay {
  position: absolute;
  top: 220px; 
  left: 20%;
  width: 60%; 
  z-index: 2;
  font-size: 90px;
  color: white;
  /* font-family: bold; */
}
.side-comment-overlay {
  position: absolute;
  top: 250px; 
  left: 20%;
  width: 60%; 
  z-index: 2;
  font-size: 100px;
  color: white;
  /* font-family: bold; */
}
</style>
