<template>
  <v-container class="user-info-container">
    <v-row>
      <v-col cols="12" md="12">
        <v-div v-if="userStore.userInfo">
          <v-card-title>
            <span class="highlight">기본정보</span>
          </v-card-title>
          <v-divider class="divider"></v-divider>

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
        </v-div>
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
    return { userStore, userInfoItems, pushUpdateButton  };
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
  padding: 20px 20px; /* 좌우 패딩을 20px로 조정 */
  box-sizing: border-box;
  /* overflow-y: scroll; */
}

.v-row {
  width: 100%;
  padding: 0 20px; /* 좌우 패딩을 20px로 조정 */
}

.v-card-title {
  margin-top: 0;
  padding-top: 0;
  text-align: center; /* 타이틀을 중앙으로 정렬 */
}

.divider {
  border: none;
  border-top: 1px solid #ddd;
  margin: 20px 0;
}

.flex-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.flex-item {
  flex-grow: 1;
}

.label-container {
  width: 70px;
  font-weight: bold;
  padding-right: 10px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.edit-button {
  margin-left: auto;
  margin-right: auto;
  display: block;
  background-color: #18b7be;
  color: white;
  width: 100px;
}

button:hover {
  background-color: #0056b3;
}

.highlight {
  display: inline-block;
  box-shadow: inset 0 -10px 0 #fcd9e5;
}
</style>
