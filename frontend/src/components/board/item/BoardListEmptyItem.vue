<template>
  <v-card-text class="empty-card">
    <div class="empty-message">
      게시글이 없습니다.
    </div>
  </v-card-text>
</template>

<script>
</script>

<style scoped>
.empty-card {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 1000px;
}

.empty-message {
  font-size: 20px; /* 기존 글자 크기 유지 */
  color: #999; /* 글자색을 회색으로 변경 */
  font-weight: lighter; /* 글자 무게를 라이트로 설정 */
  text-decoration: underline; /* 밑줄 추가 */
  text-decoration-color: #999; /* 밑줄 색상을 회색으로 설정 */
}
</style>
