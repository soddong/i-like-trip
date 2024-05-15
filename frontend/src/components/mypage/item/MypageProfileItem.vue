<template>
    <v-card>
      <div class="center-container">
        <v-avatar size="100">
          <img :src="profile" alt="Profile photo" class="cover-image">
        </v-avatar>
        <v-btn icon class="edit-icon" @click="onEditClick">
          <v-icon :icon="mdiLeadPencil"></v-icon>
        </v-btn>
      </div>
      <v-card-title class="center-text">최소현</v-card-title>
      <v-card-subtitle class="center-text mb-10">gus9300@naver.com</v-card-subtitle>
      <input type="file" ref="fileInput" style="display:none" @change="onFileChange"/>
    </v-card>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import profile from '@/assets/default_profile.png';
  import { mdiLeadPencil } from '@mdi/js'
  
  const fileInput = ref(null);
  
  function onEditClick() {
    fileInput.value.click();
  }
  
function onFileChange(event) {
  const files = event.target.files;
  console.log(files)
  if (files.length !== 0) {
    const fileReader = new FileReader();
    fileReader.onload = (e) => {
      profile.value = e.target.result;
    };
    fileReader.readAsDataURL(files[0]);
  }
}
  </script>
  
  <style>
  .center-container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    height: 100px;
    margin-top: 40px;
  }
  
  .edit-icon {
    position: absolute; 
    right: 35%; 
    bottom: 0; 
    background-color: #FFFFFF; 
    border-radius: 50%;
    width: 10px;
    color: gray
  }
  
  .cover-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  
  .center-text {
    display: block;
    text-align: center;
    width: 100%;
  }
  </style>
  