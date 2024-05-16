<script setup>
  const selectedMenu = defineModel('selectedMenu')
  
  function selectMenu(menu) {
    selectedMenu.value = menu;
  }
</script>

<template>
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
</template>

<style scoped>
  .selected {
    background-color: rgba(0, 0, 0, 0.08); /* Light gray background for selected item */
    font-weight: bold; /* 글자 두께를 더 진하게 */
  }
</style>