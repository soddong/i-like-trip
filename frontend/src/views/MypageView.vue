<template>
    <v-app>
      <v-container class="fill-height top-margin">
        <v-row align="start" justify="center" class="fill-height">
          <v-col cols="12" md="2" class="fixed-height">
            <v-card class="fill-height">
              <MypageProfileItem></MypageProfileItem>
              <v-card-text>
                <v-list>
                  <v-list-item 
                    @click="selectMenu('about')" 
                    ripple
                    :class="{ selected: selectedMenu === 'about' }"
                  >
                    회원 정보
                  </v-list-item>
                  <v-list-item 
                    @click="selectMenu('friend')" 
                    ripple
                    :class="{ selected: selectedMenu === 'friend' }"
                  >
                    친구 관리
                  </v-list-item>
                  <v-list-item 
                    @click="selectMenu('plan')" 
                    ripple
                    :class="{ selected: selectedMenu === 'plan' }"
                  >
                    내 계획
                  </v-list-item>
                </v-list>
              </v-card-text>
            </v-card>
          </v-col>
          <v-col cols="12" md="10" class="fixed-height">
            <v-card class="fill-height">
              <v-card-title>{{ selectedMenu }}</v-card-title>
              <v-card-text>
                <div v-if="selectedMenu === 'about'">About Page Content</div>
                <div v-if="selectedMenu === 'friend'">Friend Page Content</div>
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
  const selectedMenu = ref('about'); // Default to 'about'
  
  function selectMenu(menu) {
    selectedMenu.value = menu;
  }
  </script>
  
  <style>
  .fill-height {
    height: 100vh; /* Ensures the container takes full viewport height */
  }
  
  .fixed-height {
    height: 800px; /* Example fixed height */
  }
  
  .top-margin {
    margin-top: 60px; /* Additional top margin */
  }
  
  .selected {
    background-color: rgba(0, 0, 0, 0.08); /* Light gray background for selected item */
    font-weight: bold; /* 글자 두께를 더 진하게 */
  }

  .cover-image {
  width: 100%; /* 컨테이너의 전체 너비를 사용 */
  height: 100%; /* 컨테이너의 전체 높이를 사용 */
  object-fit: cover; /* 컨테이너를 채우면서 이미지의 비율을 유지 */
}


  </style>
  