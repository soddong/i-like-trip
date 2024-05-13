<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';  // useRouter를 임포트합니다.

const router = useRouter();  // useRouter를 사용하여 라우터 인스턴스를 가져옵니다.
const keyword = ref('');  // 사용자 입력을 반응형으로 관리할 ref.

const handleSearch = () => {
  // 검색 키워드를 query로 전달하며 article-list 라우트로 이동합니다.
  router.push({ name: 'article-list', query: { keyword: keyword.value } });
};
</script>

<template>
  <div class="search-input-container">
    <button class="icon-button" @click="handleSearch">
      <i class="fa fa-solid fa-3x fa-search"></i>
    </button>

    <input
      type="text"
      placeholder="여행지 키워드를 검색하세요"
      v-model="keyword"
      class="search-input"
    />
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
