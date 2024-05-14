<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const keyword = ref(''); 

const handleSearch = () => {
  router.push({ name: 'article-list', query: { keyword: keyword.value } });
};
</script>

<template>
  <div class="search-input-container">
    <input
      type="text"
      placeholder="여행지 키워드를 검색하세요"
      v-model="keyword"
      class="search-input"
    />
    <button class="icon-button" @click="handleSearch">
      <i class="fa fa-solid fa-3x fa-search"></i>
    </button>
  </div>
    <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
  />
</template>

<style scoped>
.search-input-container {
  display: flex;
  align-items: center;
  max-width: 50%;
  width: 100%;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  position: relative; 
}

.search-input {
  border: none;
  height: 90px;
  padding: 10px;
  padding-left: 60px; 
  font-size: 25px;
  border-radius: 10px;
  width: 100%; 
}

.search-input:focus {
  outline: none;
}

.icon-button {
  border: none;
  background: none;
  position: absolute;
  left: 20px;
  color: #808080;
  cursor: pointer;
}
</style>
