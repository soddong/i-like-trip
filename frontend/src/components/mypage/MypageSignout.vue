<template>
  <v-container class="deletion-container">
    <v-row>
      <v-col cols="12" md="12">
          <v-card-title class="text-h5">회원 탈퇴</v-card-title>
          <v-card-text>
            탈퇴하시면 모든 이벤트 및 할인 혜택을 받을 수 없습니다. 또한, 일단 탈퇴 처리된 계정은 복구가 불가능합니다.
            정말로 탈퇴하시겠습니까?
          </v-card-text>
          <v-card-actions>
            <v-btn color="red" @click="confirmDeletion">탈퇴하기</v-btn>
          </v-card-actions>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { ref } from 'vue';
import { useUserStore } from '@/stores/user';
// import { deleteUser } from '@/api/member';

export default {
  setup() {
    const userStore = useUserStore();
    
    function confirmDeletion() {
    //   if (confirm("정말로 회원 탈퇴를 진행하시겠습니까?")) {
    //     deleteUser(userStore.userId, 
    //       () => {
    //         alert("회원 탈퇴 처리가 완료되었습니다.");
    //         // 로그아웃 로직 실행 등의 추가 동작 필요
    //       },
    //       (error) => {
    //         console.error("탈퇴 처리 중 오류가 발생했습니다.", error);
    //         alert("탈퇴 처리에 실패하였습니다.");
    //       }
    //     );
    //   }
        confirm("정말로 회원 탈퇴를 진행하시겠습니까?");
    }

    return { confirmDeletion };
  }
};
</script>
<style scoped>
.deletion-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}

.v-card {
  max-width: 500px;
  text-align: center;
}

.v-btn {
  width: 100%;
}
</style>
