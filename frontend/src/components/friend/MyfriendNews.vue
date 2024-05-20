<template>
  <v-container class="friend-info-container">
    <v-row>
      <v-col cols="12">
        <v-card class="friend-info-card" outlined>
          <h3 class="highlight">친구 알림</h3>
          <hr class="divider">

        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>

</script>

<style scoped>
.friend-info-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 85vh;
  /* background-color: #f0f0f0; */
  padding: 20px;
  box-sizing: border-box;
}

.friend-info-card {
  background-color: white;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  margin-bottom: 20px;
  height: 80vh;
  overflow-y: auto;
}

h3 {
  /* color: #666; */
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

</style>
