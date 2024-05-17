<template>
  <v-card>
    <div class="center-container">
      <v-avatar size="100">
        <img :src="userProfile" alt="Profile photo" class="cover-image">
      </v-avatar>
      <v-btn icon class="edit-icon" @click="onEditClick">
        <v-icon :icon="mdiLeadPencil"></v-icon>
      </v-btn>
    </div>
    <v-card-title class="center-text">{{ userStore.userInfo?.name }}</v-card-title>
    <v-card-subtitle class="center-text mb-10">{{ userStore.userInfo?.email }}</v-card-subtitle>
    <input type="file" ref="fileInput" style="display:none" @change="onFileChange"/>
  </v-card>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useUserStore } from '@/stores/user';
import { updateUserInfo } from '@/api/member';
import { mdiLeadPencil } from '@mdi/js';
import { uploadImage } from "@/api/image.js";
import defaultProfile from '@/assets/default_profile.png';

const VITE_VUE_API_URL = import.meta.env.VITE_VUE_API_URL;

async function handleUploadImage(blob) {
  const formData = new FormData();
  formData.append('image', blob);

  return new Promise((resolve, reject) => {
    uploadImage(formData, 
      (response) => {
        if (response.data && response.data.imageAccessUrl) {
          resolve(response.data.imageAccessUrl);
        } else {
          reject(new Error('No imageAccessUrl in response'));
        }
      }, 
      (error) => {
        console.error('Upload failed:', error);
        reject(error);
      });
  });
}

const fileInput = ref(null);
const userStore = useUserStore();
const userProfile = ref(defaultProfile);

watch(
  () => userStore.userInfo?.profileImg,
  (newProfile) => {
    if (newProfile) {
      userProfile.value = `${VITE_VUE_API_URL}upload?name=${newProfile}`;
      console.log("Updated userProfile URL:", userProfile.value);
    }
  },
  { immediate: true }
);

const onEditClick = () => {
  fileInput.value.click();
};

const onFileChange = async (event) => {
  const files = event.target.files;

  if (files.length !== 0) {
    const file = files[0];
    
    try {
      const imageAccessUrl = await handleUploadImage(file);
      const userInfo = {
        profileImg: imageAccessUrl,
      };

      updateUserInfo(userInfo, userStore.userId,
          (response) => {
            console.log('Update success', response);
            alert('수정을 완료하였습니다.');
            userStore.getUserInfo();
          },
          (error) => {
            console.error('Update failed', error);
            alert('수정을 실패하였습니다.');
          }
      );

    } catch (error) {
      console.error('Image upload failed:', error);
    }
  }
};

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
  color: gray;
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
