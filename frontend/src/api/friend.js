// friend.js
import { localAxios } from "@/util/http-commons";

const local = localAxios();

function fetchFriends(userId, success, fail) {
  local.get(`/friend/${userId}`)
    .then(response => {
      if (response.status === 200) {
        success(response.data);
      } 
    })
    .catch(error => {
      console.error('Failed to fetch friends:', error);
      fail(error);
    });
}

function addFriend(relation, success, fail) {
    local.post('/friend/relation', relation)
      .then(response => {
        if (response.status === 200) {
            success(response.data);
          } 
      })
      .catch(error => {
        console.error('Failed to add friend: ', error);
        fail(error);
      });
}

export {
  fetchFriends,
  addFriend
};
