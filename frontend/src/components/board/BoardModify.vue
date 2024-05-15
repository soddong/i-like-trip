<script setup>
import BoardFormItem from "./item/BoardFormItem.vue";
import '@/assets/styles.css'; 
</script>

<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">
        <v-row justify="center">
          <v-card class="common-card">
            <BoardFormItem type="modify" />
          </v-card>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>
<style scoped></style>
