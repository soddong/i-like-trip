<template>
    <table class="friend-table" v-if="friends.length">
      <thead>
        <tr>
          <th>프로필</th>
          <th>아이디</th>
          <th>닉네임</th>
          <th>상태</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="friend in friends" :key="friend.id">
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
            <v-btn :class="{'friend-button': true, 'friend': friend.isFriend}" @click="toggleFriendStatus(friend)">
              {{ friend.relation == 1 ? '친구' : friend.relation == 2 ? '서로친구' : '손절' }}
            </v-btn>
          </td>
        </tr>
      </tbody>
    </table>
    <div v-else>
      친구 목록이 없습니다.
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { fetchFriends } from '@/api/friend'; 
  import { useUserStore } from '@/stores/user';
  import defaultProfile from '@/assets/default_profile.png';

  const VITE_VUE_API_URL = import.meta.env.VITE_VUE_API_URL;
  
  const userStore = useUserStore();
  const userId = userStore.userId;
  const friends = ref([]);
  
  onMounted(() => {
    fetchFriends(userId, 
      (data) => {
        data.forEach(friend => {
          friends.value.push({
            id: friend.friendId,
            name: friend.friendName,
            profilePicture: friend.friendProfile ? `${VITE_VUE_API_URL}upload?name=${friend.friendProfile}` : defaultProfile,
            relation: friend.relation
          });
        });
      },
      (error) => {
        console.error('Error loading friends:', error);
      }
    );
  });
  
  function toggleFriendStatus(friend) {
    // friend.isFriend = !friend.isFriend;
  }
  </script>
  
  <style scoped>
  .friend-table {
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
  }
  
  .friend {
    background-color: #f3d849;
    color: rgb(77, 74, 70);
  }
  </style>
  