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
    <v-card-title class="center-text">{{ userStore.userInfo.name }}</v-card-title>
    <v-card-subtitle class="center-text mb-10">{{ userStore.userInfo.email }}</v-card-subtitle>
    <input type="file" ref="fileInput" style="display:none" @change="onFileChange"/>
  </v-card>
</template>

<script>
import { ref, watch } from 'vue';
import profile from '@/assets/default_profile.png';
import { useUserStore } from '@/stores/user';
import { updateUserInfo } from '@/api/member';
import { mdiLeadPencil } from '@mdi/js';

export default {
  setup() {
    const fileInput = ref(null);
    const userStore = useUserStore();
    const userProfile = ref(profile);

    watch(
      () => userStore.userInfo.profile,
      (newProfile) => {
        if (newProfile) {
          userProfile.value = newProfile;
        }
      },
      { immediate: true }
    );

    function onEditClick() {
      fileInput.value.click();
    }

    function onFileChange(event) {
      const files = event.target.files;
      if (files.length !== 0) {
        const fileReader = new FileReader();
        fileReader.onload = (e) => {
          userProfile.value = e.target.result;
        };
        fileReader.readAsDataURL(files[0]);
        console.log(userProfile.value)
      }
    }

    return {
      userStore,
      userProfile,
      onEditClick,
      onFileChange,
      fileInput,
      mdiLeadPencil,
      updateUserInfo
    };
  },
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
