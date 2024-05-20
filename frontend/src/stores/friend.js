import { ref } from "vue";
import { defineStore } from "pinia";
import { useUserStore } from '@/stores/user';
import { fetchFriends } from '@/api/friend';

const VITE_VUE_API_URL = import.meta.env.VITE_VUE_API_URL;
const defaultProfile = ''; // Ensure you have a default profile URL or image

export const useFriendStore = defineStore("friendStore", () => {
  const userStore = useUserStore();

  const getFriends = () => {
    let friends = ref([]);
    let isDataLoaded = ref(false);

    isDataLoaded.value = false;
    console.log("get friends 호출")
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

    return friends;
  };

  // Make friends and isDataLoaded available to other components
  return {
    getFriends
  };
});
