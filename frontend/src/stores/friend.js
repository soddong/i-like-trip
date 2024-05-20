import { ref } from "vue";
import { defineStore } from "pinia";
import { fetchFriends, addFriend, removeFriend } from '@/api/friend';
import defaultProfile from '@/assets/default_profile.png';
const VITE_VUE_API_URL = import.meta.env.VITE_VUE_API_URL;


export const useFriendStore = defineStore("friendStore", () => {
  const friends = ref([]);
  const ilikeCount = ref(0);
  const likemeCount = ref(0);
  const isDataLoaded = ref(false);

  const getFriends = (userId) => {
    isDataLoaded.value = false;
    fetchFriends(userId, 
      (data) => {
        friends.value = data.map(friend => ({
          id: friend.friendId,
          name: friend.friendName,
          profilePicture: friend.friendProfile ? `${VITE_VUE_API_URL}upload?name=${friend.friendProfile}` : defaultProfile,
          relation: friend.relation
        }));
        ilikeCount.value = friends.value.filter(f => f.relation === 1 || f.relation === 3).length;
        likemeCount.value = friends.value.filter(f => f.relation === 2 || f.relation === 3).length;
        isDataLoaded.value = true;
      }, 
      (error) => {
        console.error('Error loading friends:', error);
      }
    );
  };

  const handleAddFriend = (friend) => {
    addFriend(friend, 
      () => {
        alert(`${friend.friendId}님을 친구로 추가하였습니다.`);
        isDataLoaded.value = false;
        getFriends(friend.userId); 
        isDataLoaded.value = true;
      }, 
      (error) => {
        console.error('Error adding friend:', error);
      }
    );
  };

  const handleRemoveFriend = (friend) => {
    removeFriend(friend, 
      () => {
        alert(`${friend.friendId}님을 손절하였습니다.`);
        isDataLoaded.value = false;
        console.log(isDataLoaded.value)
        getFriends(friend.userId);
        isDataLoaded.value = true;
        console.log(isDataLoaded.value)
      }, 
      (error) => {
        console.error('Error removing friend:', error);
      }
    );
  };

  return {
    friends,
    ilikeCount,
    likemeCount,
    getFriends,
    handleAddFriend,
    handleRemoveFriend,
    isDataLoaded
  };
});
