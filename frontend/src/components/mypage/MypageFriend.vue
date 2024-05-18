<template>
  <v-container class="friend-info-container">
    <v-row>
      <v-col cols="12">
        <v-card class="friend-info-card" outlined>
          <h3 class="highlight">내 친구 목록</h3>
          <hr class="divider">
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
                      v-for="result in searchResults"
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
                          <v-btn @click="addFriend(result)" variant="tonal" color="primary">추가</v-btn>
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
          <table class="friend-table">
            <thead>
              <tr>
                <th>프로필명</th>
                <th>내가 남긴 메모</th>
                <th>상태</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="friend in friends" :key="friend.id">
                <td class="friend-profile">
                  <img :src="friend.profilePicture" alt="프로필 사진" class="profile-picture">
                  <span>{{ friend.nickname }}</span>
                </td>
                <td>{{ friend.memo }}</td>
                <td>
                  <v-btn :class="{'friend-button': true, 'friend': friend.isFriend}" @click="toggleFriendStatus(friend)">
                    {{ friend.isFriend ? '친구' : '손절' }}
                  </v-btn>
                </td>
              </tr>
            </tbody>
          </table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref } from 'vue';
import { searchFriends } from '@/api/member.js'; 

// 친구 목록 초기 데이터
const friends = ref([
  {
    id: 1,
    profilePicture: 'https://via.placeholder.com/50',
    nickname: '친구1',
    memo: '친구1에 대한 메모',
    isFriend: true
  },
  {
    id: 2,
    profilePicture: 'https://via.placeholder.com/50',
    nickname: '친구2',
    memo: '친구2에 대한 메모',
    isFriend: true
  },
  {
    id: 3,
    profilePicture: 'https://via.placeholder.com/50',
    nickname: '친구3',
    memo: '친구3에 대한 메모',
    isFriend: true
  },
  {
    id: 4,
    profilePicture: 'https://via.placeholder.com/50',
    nickname: '친구4',
    memo: '친구4에 대한 메모',
    isFriend: true
  }
]);

// 검색 상태 관련 데이터
const showSearchField = ref(false);
const searchQuery = ref('');
const searchResults = ref([]);

// 친구 상태 변경
function toggleFriendStatus(friend) {
  friend.isFriend = !friend.isFriend;
}

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
    console.error('Error fetching members:', error);
  }
}

// 친구 추가 함수
function addFriend(user) {
  friends.value.push({
    id: friends.value.length + 1,
    profilePicture: user.profilePicture,
    nickname: user.nickname,
    memo: `${user.nickname}에 대한 메모`,
    isFriend: true
  });
  toggleSearchField();
}
</script>

<style scoped>
.friend-info-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 75vh;
  background-color: #f0f0f0;
  padding: 20px;
  box-sizing: border-box;
  overflow-y: auto;
}

.friend-info-card {
  background-color: white;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  margin-bottom: 20px;
  max-height: 70vh;
  overflow-y: auto;
}

h3 {
  color: #666;
}

table {
  width: 100%;
  border-collapse: collapse;
  background-color: white;
}

th, td {
  padding: 12px;
  text-align: center;
}

th {
  background-color: #f4f4f4;
  font-weight: bold;
}

tr:not(:last-child) td {
  border-bottom: 1px solid #ddd;
}

.friend-profile {
  display: flex;
  align-items: center;
}

.profile-picture {
  border-radius: 50%;
  margin-right: 10px;
  width: 50px;
  height: 50px;
}

.friend-button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  background-color: gray;
  color: white;
}

.friend {
  background-color: #f3d849;
  color: rgb(77, 74, 70);
}

.divider {
  border: none;
  border-top: 1px solid #ddd;
  margin: 20px 0;
}

.highlight {
  display: inline-block;
  box-shadow: inset 0 -10px 0 #fcd9e5;
}

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

  /* padding: 10px; */
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
