<template>
  <v-main class="fill-height top-margin">
    <v-container class="friend-info-container">
    <v-row align="start" justify="center" class="fill-height">
      <v-cols cols="12" md="2"></v-cols>
      <v-col cols="12" md="2" class="fixed-height">
        <v-card class="fill-height">
          <MypageProfileItem></MypageProfileItem>
          <MypageMenuItem v-model:selectedMenu="selectedMenu"></MypageMenuItem>
        </v-card>
      </v-col>
      <v-col cols="10" md="8" class="fixed-height">
        <v-card class="fill-height">
          <v-card-title>
          </v-card-title>
          <v-card-text>
            <div v-if="selectedMenu === 'about'">
              <MypageInfo></MypageInfo>
            </div>
            <div v-if="selectedMenu === 'remove'">
              <MypageSignout></MypageSignout>
            </div>
            <!-- <div v-if="selectedMenu === 'friend'">
                <MypageFriend></MypageFriend>
              </div> -->
            <div v-if="selectedMenu === 'plan'">Plan Page Content</div>
          </v-card-text>
        </v-card>
      </v-col>
      <v-cols cols="12" md="1"></v-cols>
    </v-row>
    </v-container>
  </v-main>
</template>

<script setup>
import { ref } from 'vue';
import MypageProfileItem from '@/components/mypage/item/MypageProfileItem.vue'
import MypageMenuItem from '@/components/mypage/item/MypageMenuItem.vue'
import MypageInfo from '@/components/mypage/MypageInfo.vue' 
import MypageSignout from '@/components/mypage/MypageSignout.vue' 
// import MypageFriend from '@/components/mypage/MypageFriend.vue' MypageSignout.vue
const selectedMenu = ref('about'); // Default to 'about'
</script>

<style>
.fill-height {
  height: 100vh;
}

.fixed-height {
  height: 800px;
}

.top-margin {
  margin-top: 60px;
}

.cover-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.sub-seperate {
    border-bottom: 1px solid #20435C;
    font-size: x-large;
    margin-bottom: 30px
}
</style>
