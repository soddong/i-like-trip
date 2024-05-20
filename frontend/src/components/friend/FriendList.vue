<template>
    <v-tabs v-model="activeTab" fixed-tabs>
        <v-tab v-for="(tab, index) in tabs" :key="index" :value="tab.value">
        {{ tab.label }}
        </v-tab>
    </v-tabs>
    <div class="content">
        <template v-if="isDataLoaded">
        <table class="friend-table" v-if="filteredFriends.length">
            <thead>
            <tr>
                <th>프로필</th>
                <th>아이디</th>
                <th>닉네임</th>
                <th>토글</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="friend in filteredFriends" :key="friend.id">
                <td class="friend-profile">
                <img :src="friend.profilePicture" alt="프로필 사진" class="profile-picture">
                </td>
                <td>
                <span>{{ friend.id }}</span>
                </td>
                <td>
                <span>{{ friend.name }}</span>
                </td>
                <td>
                <v-btn :class="getButtonClass(friend.relation)" @click="toggleFriendStatus(friend)">
                    {{ getRelationText(friend.relation) }}
                </v-btn>
                </td>
            </tr>
            </tbody>
        </table>
        <div v-else class="mt-3">
            목록이 없습니다.
        </div>
        </template>
        <template v-else>
        <div>Loading...</div>
        </template>
    </div>
</template>
<script setup>
import { ref, computed, onMounted } from 'vue';
import { fetchFriends } from '@/api/friend';
import { useUserStore } from '@/stores/user';
import defaultProfile from '@/assets/default_profile.png';
const emit = defineEmits(['add-friend', 'remove-friend']);

const VITE_VUE_API_URL = import.meta.env.VITE_VUE_API_URL;

const userStore = useUserStore();
const userId = userStore.userId;
const friends = ref([]);
const activeTab = ref(1); // 기본 활성화된 탭 설정
const isDataLoaded = ref(false); // 데이터 로드 상태를 추적

const tabs = [
  { label: '내가 등록한 친구', value: 1 },
  { label: '나를 등록한 친구', value: 2 },
  { label: '서로 등록한 친구', value: 3 },
];

function loadFriends() {
  isDataLoaded.value = false; // 데이터 로드 시작
  fetchFriends(userId, 
    (data) => {
      friends.value = data.map(friend => ({
        id: friend.friendId,
        name: friend.friendName,
        profilePicture: friend.friendProfile ? `${VITE_VUE_API_URL}upload?name=${friend.friendProfile}` : defaultProfile,
        relation: friend.relation
      }));
      isDataLoaded.value = true; // 데이터 로드 완료
    },
    (error) => {
      console.error('Error loading friends:', error);
      isDataLoaded.value = true; // 오류 발생 시에도 로드 완료로 설정
    }
  );
}

onMounted(() => {
  loadFriends();
});

const filteredFriends = computed(() => {
  let result = [];
  if (activeTab.value === 1) {
    result = friends.value.filter(friend => friend.relation === 1 || friend.relation === 3);
  } else if (activeTab.value === 2) {
    result = friends.value.filter(friend => friend.relation === 2 || friend.relation === 3);
    result.sort((a, b) => a.relation - b.relation); // 오름차순 정렬
  } else if (activeTab.value === 3) {
    result = friends.value.filter(friend => friend.relation === 3);
  }
  return result;
});

function getRelationText(relation) {
  switch(relation) {
    case 1:
      return '친구 취소';
    case 2:
      return '친구 등록';
    case 3:
      return '친구 취소';
    default:
      return '친구 등록';
  }
}

function getButtonClass(relation) {
  return {
    'friend-button': true,
    'cancle': relation === 1 || relation==3,
    'register': relation === 2,
  };
}

function toggleFriendStatus(friend) {
  if (friend.relation === 1) { // 내가 등록한 친구
    friend.relation = 0;       // 친구 취소
    emit('remove-friend', {userId: userStore.userId, friendId: friend.id});
  } else if (friend.relation === 2) { // 나를 등록한 친구
    friend.relation = 3;            // 서로친구
    emit('add-friend', {userId: userStore.userId, friendId: friend.id});
  } else if (friend.relation === 3) { // 서로 친구
    friend.relation = 2;              // 친구 취소
    emit('remove-friend', {userId: userStore.userId, friendId: friend.id});
  }
}
</script>

<style scoped>
.friend-table {
  width: 100%;
  border-collapse: collapse;
  background-color: white;
}

.content {
  margin-top: 20px;
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
  justify-content: center; 
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
  transition: background-color 0.3s;
}

.friend-button.register {
  background-color:#1cb2b99f; /* 진한 노란색 */
  color: white;
}

.friend-button.cancle {
  background-color: #f44336bd; /* 녹색 */
  color: white;
}
</style>
