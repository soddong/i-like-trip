<template>
  <v-container class="user-info-container">
    <v-row>
      <v-col cols="12" md="6">
        <v-card class="user-info-card" v-if="userStore.userInfo" outlined>
          <v-card-title>
            <span class="highlight">기본정보</span>
          </v-card-title>
          <v-divider></v-divider>

          <v-list>
            <v-list-item>
              <v-list-item-content class="flex-container">
                <div class="label-container">아이디</div>
                <v-text-field
                      variant="outlined"
                      dense
                      class="flex-item"
                      readonly
                >{{ userStore.userId }}</v-text-field>
              </v-list-item-content>
            </v-list-item>
            <v-list-item v-for="item in userInfoItems" :key="item.key">
              <v-list-item-content class="flex-container">
                <div class="label-container">{{ item.label }}</div>
                <v-text-field
                  v-model="item.value"
                  variant="outlined"
                  dense
                  class="flex-item"
                ></v-text-field>
              </v-list-item-content>
            </v-list-item>
            <v-btn
                  text
                  class="edit-button button"
                  dense
                  @click="pushUpdateButton"
                >수 정</v-btn>
          </v-list>
        </v-card>
      </v-col>

      <v-col cols="12" md="6">
        <v-card class="user-info-card" outlined>
          <v-card-title>
            <span class="highlight">선택정보</span>
          </v-card-title>
          <v-divider></v-divider>

          <v-list>
            <v-list-item v-for="item in electiveInfoItems" :key="item.key">
              <v-list-item-content>
                <v-text-field
                  v-model="item.value"
                  :label="item.label"
                  :type="item.key === 'birthday' ? 'date' : 'text'"
                  outlined
                  dense
                ></v-text-field>
              </v-list-item-content>
            </v-list-item>
          </v-list>
          <v-btn
                  text
                  class="edit-button button"
                  dense
                  @click="pushUpdateButton"
                >수 정</v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { ref, watch } from 'vue';
import { useUserStore } from '@/stores/user';
import { updateUserInfo } from '@/api/member';

export default {
  setup() {
    const userStore = useUserStore();
    const userInfoItems = ref([
      { key: 'password', label: '패스워드', value: '' },
      { key: 'name', label: '닉네임', value: '' },
      { key: 'email', label: '이메일', value: '' },
      { key: 'location', label: '거주지', value: '' }
    ]);
    const electiveInfoItems = ref([
      { key: 'tel', label: '전화번호', value: '010-5936-3877' },
      { key: 'birthday', label: '생일', value: '1998.01.08' },
      { key: 'job', label: '직업', value: '학생' },
    ])

    watch(
      () => userStore.userInfo,
      (newUserInfo) => {
        if (newUserInfo) {
          userInfoItems.value = userInfoItems.value.map(item => ({
            ...item, // 이전 속성을 유지
            value: newUserInfo[item.key] || '' // value 속성만 업데이트
          }));
        }
      },
      { immediate: true }// watch가 즉시 실행되도록 설정
    );
 
    function pushUpdateButton() {
      const updateData = userInfoItems.value.reduce((acc, item) => {
        // 변경점이 있는 애들만 update
        if (item.value !== userStore.userInfo[item.key]) { 
          if ((item.key == 'password' && item.value != '')|| item.key != 'password') {
            acc[item.key] = item.value;
          }
        }
        return acc;
      }, {});

      if (Object.keys(updateData).length > 0) {
        updateUserInfo(updateData, userStore.userId,
          (response) => {
            console.log('Update success', response);
            userStore.getUserInfo();
            alert('수정을 완료하였습니다.')
          },
          (error) => {
            console.error('Update failed', error);
            alert('수정을 실패하였습니다..')
          }
        );
      } else {
        console.log('No changes detected, no update performed.');
      }
    }
    return { userStore, userInfoItems, electiveInfoItems, pushUpdateButton  };
  },
};
</script>


<style scoped>
.user-info-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 75vh;
  background-color: #f0f0f0;
  padding: 20px;
  box-sizing: border-box;
  overflow-y: scroll;
}

.v-row {
  width: 100%;
}

.user-info-card {
  background-color: white;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin: 20px;
  min-height: 580px;
  width: 100%; /* 카드가 가로로 100% 차지 */
  max-width: 80%; /* 최대 너비 제한 제거 */
  margin-left: 10%;
}

h2 {
  margin-bottom: 20px;
  color: #333;
}

h3 {
  margin-bottom: 20px;
  color: #4b4747;
}

.info-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

label {
  font-weight: bold;
  color: #333;
}

input, select {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 60%;
}

button {
  background-color: #18b7be;
  color: white;
  width: 100px;
}

button:hover {
  background-color: #0056b3;
}

.divider {
  border: none;
  border-top: 1px solid #ddd;
  margin: 20px 0;
}

.highlight {
  display: inline-block;
  box-shadow: inset 0 -10px 0 #fcd9e5;
}

.flex-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.flex-item {
  flex-grow: 1;
}

.edit-button {
  margin-left: 40%;
}

.label-container {
  width: 70px; /* 고정된 너비를 지정 */
  font-weight: bold;
  padding-right: 10px; /* 라벨과 텍스트 필드 사이의 간격 */
  white-space: nowrap; /* 줄바꿈 방지 */
  overflow: hidden; /* 내용이 넘칠 경우 숨김 */
  text-overflow: ellipsis; /* 넘치는 텍스트를 ...로 표시 */
}
</style>
