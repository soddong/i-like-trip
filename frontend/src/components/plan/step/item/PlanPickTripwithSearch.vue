<script setup>
import { ref, computed, onMounted, defineProps, defineEmits } from 'vue';
import { mdiMagnify, mdiPlus } from '@mdi/js';
import { fetchFriends } from '@/api/friend';
import { useUserStore } from '@/stores/user';
import defaultProfile from '@/assets/default_profile.png';

const VITE_VUE_API_URL = import.meta.env.VITE_VUE_API_URL;
const userStore = useUserStore();
const searchQuery = ref('');
const showFriends = ref(false);
const friends = ref([]);
const isDataLoaded = ref(false);

const props = defineProps({
    modelValue: Array // Accepting modelValue prop which is used for v-model
});

const emits = defineEmits(['update:modelValue']);

const filteredFriends = computed(() => {
  return friends.value.filter(friend => 
    (searchQuery.value === '' || friend.name.includes(searchQuery.value)) &&
    showFriends.value
  );
});

function addFriend(friend) {
    let newTripWith = [...props.modelValue, friend];
    emits('update:modelValue', newTripWith);
}

onMounted(() => {
  loadFriends();
});

function loadFriends() {
  isDataLoaded.value = false;
  fetchFriends(userStore.userId, 
    (data) => {
      friends.value = data.map(friend => ({
        id: friend.friendId,
        name: friend.friendName,
        profilePicture: friend.friendProfile ? `${VITE_VUE_API_URL}upload?name=${friend.friendProfile}` : defaultProfile,
        relation: friend.relation
      }));
      isDataLoaded.value = true;
    },
    (error) => {
      console.error('Error loading friends:', error);
      isDataLoaded.value = true;
    }
  );
}
</script>


<template>
  <v-container fluid class="fill-height d-flex flex-column align-center py-5">
    <h3 class="heading-with-underline">동행 추가</h3>
    <v-row>
      <v-col>
        <v-text-field
          v-model="searchQuery"
          placeholder="ID 검색"
          density="compact"
          variant="solo"
          hide-details
          single-line
          class="search-field"
          @input="loadFriends"
          >
          <v-icon>{{ mdiMagnify }}</v-icon>
        </v-text-field>
      </v-col>
      <v-col>
        <v-btn @click="loadFriends" class="search-btn">검색</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-checkbox
          v-model="showFriends"
          label="내 친구 보기"
          @change="loadFriends"
        />
      </v-col>
    </v-row>
    <v-sheet class="friend-list" outlined v-if="isDataLoaded">
      <v-row v-show="filteredFriends.length > 0" class="py-2 px-3">
        <v-col cols="12" v-for="friend in filteredFriends" :key="friend.id" class="box">
          <v-row align="center" no-gutters>
            <v-col cols="4" class="d-flex justify-center">
              <img :src="friend.profilePicture" alt="프로필 사진" class="profile-picture">
            </v-col>
            <v-col cols="4" class="d-flex flex-column align-start justify-center">
              <span class="font-weight-bold">{{ friend.name }}</span>
              <span class="small-id">{{ friend.id }}</span>
            </v-col>
            <v-col cols="4" class="d-flex justify-center">
              <v-btn icon class="add-btn" @click="addFriend(friend)">
                <v-icon>{{ mdiPlus }}</v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <v-row v-show="filteredFriends.length === 0" class="py-2 px-3">
        <v-col>
          <p class="text-center grey--text">친구가 없습니다.</p>
        </v-col>
      </v-row>
    </v-sheet>
    <div v-else>
      <v-progress-circular indeterminate></v-progress-circular>
    </div>
  </v-container>
</template>


<style scoped>
.heading-with-underline {
  position: relative;
  display: inline-block;
  margin-bottom: 20px;
  padding-bottom: 5px;
  font-weight: bold;
}

.heading-with-underline::after {
  content: '';
  position: absolute;
  width: 100%;
  height: 2px;
  background-color: black;
  left: 0;
  bottom: 0;
}

.fill-height {
  height: 100%;
}

.friend-list {
  width: 100%;
  max-width: 600px;
  height: 650px;
  border: 1px solid #ccc;
  border-radius: 8px;
  overflow-y: scroll;
  background-color: #f9f9f9;
  padding-top: 10px;
}

.box {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 10px 0;
}

.profile-picture {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

span {
  color: rgba(0, 0, 0, 0.74);
  font-size: small;
}

.small-id {
  color: rgba(128, 128, 128, 0.466);
  font-size: x-small;
}

.v-divider {
  width: 100%;
}

.add-btn {
  background-color: #1cb2b99f;
  color: white;
  border-radius: 50%;
  width: 24px;
  height: 24px;
  min-width: 24px;
  margin-right: 8px;
  box-shadow: none;
}

.search-field {
  width: 250px;
}
</style>
