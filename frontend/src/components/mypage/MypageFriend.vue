<template>
  <div class="friend-info-container">
    <div class="friend-info-card">
      <h3 class="highlight">내 친구 목록</h3>
      <hr class="divider">
      <table>
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
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      friends: [
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
        },
        {
          id: 5,
          profilePicture: 'https://via.placeholder.com/50',
          nickname: '친구5',
          memo: '친구5에 대한 메모', 
          isFriend: true
        },
        {
          id: 6,
          profilePicture: 'https://via.placeholder.com/50',
          nickname: '친구6',
          memo: '친구6에 대한 메모', 
          isFriend: true
        },
        {
          id: 7,
          profilePicture: 'https://via.placeholder.com/50',
          nickname: '친구7',
          memo: '친구7에 대한 메모', 
          isFriend: true
        },
        {
          id: 8,
          profilePicture: 'https://via.placeholder.com/50',
          nickname: '친구8',
          memo: '친구8에 대한 메모', 
          isFriend: true
        },
        {
          id: 9,
          profilePicture: 'https://via.placeholder.com/50',
          nickname: '친구9',
          memo: '친구9에 대한 메모', 
          isFriend: true
        },
        {
          id: 10,
          profilePicture: 'https://via.placeholder.com/50',
          nickname: '친구10',
          memo: '친구10에 대한 메모', 
          isFriend: false
        },
        {
          id: 11,
          profilePicture: 'https://via.placeholder.com/50',
          nickname: '친구11',
          memo: '친구11에 대한 메모', 
          isFriend: false
        },
        // 더 많은 친구 데이터를 추가할 수 있습니다.
      ]
    };
  },
  methods: {
  toggleFriendStatus(friend) {
    friend.isFriend = !friend.isFriend;
  }
}
};


</script>

<style scoped>
.friend-info-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100vh;
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
  max-width: 500px;
  width: 100%;
  margin-bottom: 20px;
  max-height: 400px; /* Adjust this value as needed */
  overflow-y: auto;
}


h3 {
  margin-bottom: 20px;
  color: #666;
}

table {
  width: 100%;
  border-collapse: collapse;
  background-color: white;
}

th, td {
  padding: 12px;
  text-align: left;
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
/* background: #f6fcd9; */
display: inline-block;
box-shadow: inset 0 -10px 0 #fcd9e5; 
}
</style>
