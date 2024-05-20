<template>
  <v-app>
    <v-container class="fill-height top-margin">
      <v-row align="start" justify="center" class="fill-height">
        <v-col cols="12" md="2" class="fixed-height">
          <v-card class="fill-height">
            <MypageProfileItem></MypageProfileItem>
            <MypageMenuItem v-model:selectedMenu="selectedMenu"></MypageMenuItem>
          </v-card>
        </v-col>
        <v-col cols="12" md="10" class="fixed-height">
          <v-card class="fill-height">
            <v-card-title>
              <div v-if="selectedMenu === 'about'">
                <span>회원 정보</span>
              </div>
              <!-- <div v-if="selectedMenu === 'friend'">                  
                <span>친구 관리</span>
              </div> -->
              <div v-if="selectedMenu === 'plan'">
                <span>내 계획</span>
              </div>
            </v-card-title>
            <v-card-text>
              <div v-if="selectedMenu === 'about'">
                <MypageInfo></MypageInfo>
              </div>
              <!-- <div v-if="selectedMenu === 'friend'">
                <MypageFriend></MypageFriend>
              </div> -->
              <div v-if="selectedMenu === 'plan'">Plan Page Content</div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>
  
<script setup>
import { ref } from 'vue';
import MypageProfileItem from '@/components/mypage/item/MypageProfileItem.vue'
import MypageMenuItem from '@/components/mypage/item/MypageMenuItem.vue'
import MypageInfo from '@/components/mypage/MypageInfo.vue'
// import MypageFriend from '@/components/mypage/MypageFriend.vue'
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


</style>
