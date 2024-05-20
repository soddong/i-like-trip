<template>
  <v-card class="user-profile-card">
    <div class="center-container">
      <v-avatar size="100">
        <img :src="userProfile" alt="Profile photo" class="cover-image">
      </v-avatar>
    </div>
    <v-card-title class="center-text">{{ userStore.userInfo?.name }}</v-card-title>
    <v-card-text class="text-center">
      <div>
        <!-- 직접적으로 props 참조 -->
        <v-chip class="friend-count">내가 등록한 친구: {{ ilikeCount }}</v-chip>
        <v-chip class="friend-count">나를 등록한 친구: {{ likemeCount }}</v-chip>
      </div>
    </v-card-text>
  </v-card>
</template>



<script setup>
import { ref, watch } from 'vue';
import { useUserStore } from '@/stores/user';
import defaultProfile from '@/assets/default_profile.png';

const VITE_VUE_API_URL = import.meta.env.VITE_VUE_API_URL;

const userStore = useUserStore();
const userProfile = ref(defaultProfile);

watch(
  () => userStore.userInfo?.profileImg,
  (newProfile) => {
    userProfile.value = newProfile ? `${VITE_VUE_API_URL}upload?name=${newProfile}` : defaultProfile;
  },
  { immediate: true }
);

defineProps({
  ilikeCount: Number,
  likemeCount: Number
});

</script>


<style scoped>
.user-profile-card {
  max-width: 500px;
  margin: auto;
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 20px;
}

.center-container {
  display: flex;
  justify-content: center;
}

.center-text {
  text-align: center;
  margin-top: 10px;
}

.friend-count {
  margin: 5px;
}

.cover-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 50%;
}
</style>

