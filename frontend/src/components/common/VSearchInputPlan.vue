<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getChatgpt } from '@/api/gpt';

const router = useRouter();
const keyword = ref('');
const showDropdown = ref(false);
const popularPlaces = ref(["장소1", "장소2", "장소3", "장소4", "장소5"]);
const isLoading = ref(false); // 로딩 상태를 추적하는 ref 추가

const handleSearch = () => {
  router.push({ name: 'plan-list', query: { keyword: 'title', word: keyword.value} });
};

const toggleDropdown = (status) => {
  showDropdown.value = status;
};

const setKeyword = (place) => {
  keyword.value = place;
  showDropdown.value = false;
};

onMounted(async () => {
  isLoading.value = true;
  try {
    console.log('Fetching top places...');
    const response = await getChatgpt();
    console.log('Response:', response);
    popularPlaces.value = response.split('\n').map(place =>
      place.trim().replace(/^\d+\.\s*/, '')
    );
    isLoading.value = false; 
  } catch (error) {
    console.error('Failed to fetch top places', error);
    popularPlaces.value = ["장소1", "장소2", "장소3", "장소4", "장소5"];
    isLoading.value = false; 
  }
});
</script>


<template>
  <div 
    class="search-input-container"
    @mouseover="toggleDropdown(true)" 
    @mouseleave="toggleDropdown(false)"
  >
    <input
      type="text"
      placeholder="여행지 키워드를 검색하세요"
      v-model="keyword"
      class="search-input"
    />
    <button class="icon-button" @click="handleSearch">
      <i class="fa fa-solid fa-3x fa-search"></i>
    </button>
    <transition name="slide-fade">
      <div class="dropdown" v-if="showDropdown">
        <h2>인기 여행지 TOP 5</h2>
        <ul v-if="!isLoading">
          <li v-for="(place, idx) in popularPlaces" :key="place" @click="setKeyword(place)">
            {{idx+1}}. {{ place }}
          </li>
        </ul>
        <p v-if="isLoading">로딩중...</p> <!-- 로딩중 메시지를 표시 -->
        <span class="note" v-if="!isLoading">출처: ChatGPT 3.5</span>
      </div>
    </transition>
  </div>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
  />
</template>


<style scoped>
.search-input-container {
  display: flex;
  flex-direction: column;
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
  height: 50px;
  padding: 10px;
  padding-left: 60px; 
  font-size: 18px;
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
  top: 50%;
  transform: translateY(-50%);
  color: #808080;
  cursor: pointer;
}

.dropdown {
  margin-top: 10px;
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  padding: 20px;
  position: absolute;
  top: 100%;
  left: 0;
}

.dropdown h2 {
  margin: 0 0 10px 0;
  font-size: 18px;
}

.dropdown ul {
  list-style: none;
  padding: 0;
}

.dropdown li {
  padding: 8px 0;
  font-size: 16px;
  cursor: pointer;
}

.dropdown li:hover {
  background-color: #f0f0f0;
}

.note {
  font-size: 12px;
  color: #808080;
  position: absolute;
  bottom: 10px;
  right: 10px;
}

.slide-fade-enter-active {
  transition: all 0.5s ease;
}

.slide-fade-leave-active {
  transition: all 0.5s ease;
}

.slide-fade-enter, .slide-fade-leave-to /* .slide-fade-leave-active in <2.1.8 */ {
  transform: translateY(-10px);
  opacity: 0;
}
</style>
