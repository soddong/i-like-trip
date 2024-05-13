<script setup>
import BoardFormItem from "./item/BoardFormItem.vue";
</script>

<template>
  <v-row>
    <v-col cols="3" />
    <v-col cols="5">
    <BoardFormItem type="modify" />
    </v-col>
    <v-col cols="3" />
  </v-row>
</template>

<style scoped></style>
