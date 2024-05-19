<template>
  <v-container class="friend-info-container">
    <v-row>
      <v-col cols="12">
        <v-card class="friend-info-card" outlined>
          <h3 class="highlight">내 친구 목록</h3>
          <hr class="divider">
          <FriendMemberSearch :existingFriends="friends" :userId="userId" @add-friend="handleAddFriend"/>
          <FriendList :friends="friends" @add-friend="handleAddFriend" @remove-friend="handleRemoveFriend"/>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import FriendMemberSearch from '@/components/friend/FriendMemberSearch.vue';
import FriendList from '@/components/friend/FriendList.vue';
import { fetchFriends, addFriend, removeFriend } from '@/api/friend'; 
import { useUserStore } from '@/stores/user';

const friends = ref([]);

const userStore = useUserStore();
const userId = userStore.userId;

function updateFriendsList() {
  fetchFriends(userId, 
    (data) => {
      friends.value = data.map(friend => ({
        id: friend.friendId,
        name: friend.friendName,
        profilePicture: friend.friendProfile,
        relation: friend.relation
      }));
    },
    (error) => {
      console.error('Error loading friends:', error);
    }
  );
}

onMounted(() => {
  updateFriendsList();
});

function handleAddFriend(friend) {
  addFriend(
    friend,
    (data) => {
      console.log('Friend added successfully:', data);
      alert(`${friend.friendId}님을 친구로 추가하였습니다.`)
      updateFriendsList();
    },
    (error) => {
      console.error('Error adding friend:', error);
    }
  );
}

function handleRemoveFriend(friend) {
  removeFriend(
    friend,
    (data) => {
      console.log('Friend removed successfully:', data);
      alert(`${friend.friendId}님을 손절하였습니다.`);
      updateFriendsList();
    },
    (error) => {
      console.error('Error removing friend:', error);
    }
  );
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
  height: 70vh;
  overflow-y: auto;
}

h3 {
  color: #666;
}

.divider {
  border: none;
  border-top: 1px solid #ddd;
  margin: 20px 0;
}
</style>
