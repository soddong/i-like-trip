<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const keyword = ref('');

const handleSearch = () => {
  router.push({ name: 'plan-list', query: { keyword: 'title', word: keyword.value} });
};
</script>

<template>
  <!-- <div class="container"> -->
    <!-- <v-row class="search-input-container"> -->
      <!-- <v-col cols="12" class="search-column"> -->
        <div class="search-wrapper">
          <i class="fas fa-search search-icon"></i>
          <input
            type="text"
            placeholder="여행지 키워드를 검색하세요"
            v-model="keyword"
            class="search-input"
          />
          <v-btn class="search-button" @click="handleSearch">
            검색
          </v-btn>
        </div>
      <!-- </v-col> -->
    <!-- </v-row> -->
  <!-- </div> -->
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
  />
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: flex-end; /* Right-align the container */
  width: 100%;
  max-width: 1000px;
  background-color: white;
  border-radius: 10px;
  justify-content: flex-end; 
}

.search-input-container {
  display: flex;
  justify-content: flex-end; /* Align search input to the right */
  width: 100%;
}

.search-column {
  display: flex;
  justify-content: flex-end; /* Align search column to the right */
}

.search-wrapper {
  display: flex;
  align-items: center;
  position: relative;
  width: auto;
  margin-left: auto; /* Align wrapper to the right */
}

.search-input {
  width: 200px; /* Smaller width for the search input */
  height: 30px; /* Smaller height for the search input */
  padding: 5px 10px 5px 30px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 10px 0 0 10px;
  box-sizing: border-box;
}

.search-input:focus {
  outline: none;
  border-color: #a0a0a0;
}

.search-button {
  width: 50px; /* Adjusted width */
  height: 30px; /* Adjusted height */
  background-color: #b4b2b2;
  color: white;
  font-size: 14px;
  border: none;
  border-radius: 0 10px 10px 0;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: -1px; /* Overlap border with input */
}

.search-button:hover {
  background-color: #b9b9b9bb;
}

.search-icon {
  position: absolute;
  left: 10px;
  top: 50%;
  transform: translateY(-50%);
  color: #808080;
  pointer-events: none;
}

.note {
  font-size: 12px;
  color: #808080;
  position: absolute;
  bottom: 10px;
  right: 10px;
}

.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all 0.5s ease;
}

.slide-fade-enter,
.slide-fade-leave-to {
  transform: translateY(-10px);
  opacity: 0;
}
</style>
