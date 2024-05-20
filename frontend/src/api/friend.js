// friend.js
import { localAxiosInstance } from "@/util/http-commons";

const local = localAxiosInstance;

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
    console.log(relation)
    local.post('/friend/relation', relation)
      .then(response => {
        if (response.status === 201) {
            success(response.data);
          } 
      })
      .catch(error => {
        console.error('Failed to add friend: ', error);
        fail(error);
      });
}

function removeFriend(relation, success, fail) {
    local.delete('/friend/relation', { data: relation }) 
        .then(response => {
            if (response.status === 204) {
                success(response.data);
            } else {
                fail(new Error('Unexpected response status: ' + response.status));
            }
        })
        .catch(error => {
            console.error('Failed to remove friend:', error);
            fail(error);
        });
}


export {
  fetchFriends,
  addFriend,
  removeFriend
};
