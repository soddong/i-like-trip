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
            @click="selectMenu('remove')" 
            ripple
            :class="{ selected: selectedMenu === 'remove' }"
            >
            회원 탈퇴
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