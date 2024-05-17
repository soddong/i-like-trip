<template>
  <div class="search-wrapper">
    <v-row class="search-container">
      <v-col>
        <v-text-field
          v-model="searchQuery"
          label="ID 검색"
          type="input"
          @input="searchFriendsHandler"
          class="search-field"
          v-show="showSearchField"
        ></v-text-field>
        <v-card v-if="searchResults.length" class="search-results-card" outlined>
          <v-list>
            <v-list-item
              v-for="result in filteredResults"
              :key="result.id"
              class="search-result-item"
            >
              <v-row>
                <v-col cols="4" class="text-center">
                  <v-list-item-content>
                    {{ result.name }}
                  </v-list-item-content>
                </v-col>
                <v-col cols="4" class="text-center">
                  <v-list-item-content>
                    {{ result.id }}
                  </v-list-item-content>
                </v-col>
                <v-col cols="4" class="text-center">
                  <v-btn @click="$emit('add-friend', result)" variant="tonal" color="primary">추가</v-btn>
                </v-col>
              </v-row>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
      <v-col>
        <v-btn @click="toggleSearchField" class="custom-btn" variant="outlined">
          + 새 친구 추가
        </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { searchFriends } from '@/api/member.js'; 
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();

const props = defineProps({
  existingFriends: Array,
});

// 검색 상태 관련 데이터
const showSearchField = ref(false);
const searchQuery = ref('');
const searchResults = ref([]);

// 검색창 표시 토글
function toggleSearchField() {
  showSearchField.value = !showSearchField.value;
  searchQuery.value = '';
  searchResults.value = [];
}

// 비동기 검색 함수
async function searchFriendsHandler() {
  if (searchQuery.value === '') {
    searchResults.value = [];
    return;
  }

  try {
    searchResults.value = await searchFriends(searchQuery.value);
  } catch (error) {
    console.error('회원 정보를 가져오는 중 오류 발생:', error);
  }
}

// 기존 친구 목록과 본인 ID를 제외한 검색 결과
const filteredResults = computed(() => {
  return searchResults.value.filter(result => 
    result.id !== userStore.userId && 
    !props.existingFriends.some(friend => friend.id === result.id)
  );
});
</script>

<style scoped>
.search-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.search-container {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  margin-bottom: 10px;
  margin-top: 10px;
}

.custom-btn {
  width: 200px;
  height: 50px;
  color: #18b7be;
  border-radius: 25px;
  margin-bottom: 10px;
}

.search-field {
  width: 500px; /* 검색창의 고정된 크기 */
}

.search-results-card {
  position: absolute; /* 검색 결과를 다른 항목들 위에 띄움 */
  z-index: 1000; /* 다른 요소보다 위에 위치 */
  background-color: white;
}

.search-result-item {
  border-bottom: 1px solid #ddd;
  text-align: center;
  width: 500px; /* 검색창의 고정된 크기 */
}

.text-center {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
