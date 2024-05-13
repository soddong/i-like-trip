<template>
  <v-app>
    <HeaderNav />
    <RouterView />
  </v-app>
</template>

<script setup>
import HeaderNav from "@/components/layout/HeaderNav.vue";
</script>

<style>
.v-application {
  font-family: 'seolleimcool-SemiBold', sans-serif !important;
}
@font-face {
    font-family: 'seolleimcool-SemiBold';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2312-1@1.1/seolleimcool-SemiBold.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
</style>