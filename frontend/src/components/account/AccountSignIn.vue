<script setup>
import { ref } from 'vue';
import { mdiEmailOutline, mdiEyeOff, mdiEye, mdiLockOutline, mdiChevronRight } from '@mdi/js';
import { RouterLink } from 'vue-router'
import { useUserStore } from "@/stores/user"

const visible = ref(false)
const loginData = ref({
  id: "",
  password: ""
})
const isLoginError = ref(false)
const userStore = useUserStore()
const { userSignIn } = userStore

const login = async () => {
  await userSignIn(loginData.value)
  if (!userStore.isSignIn) {
    isLoginError.value = true;
  }
}
</script>

<template>
  <v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg" style="margin-top: 150px">
    <div class="text-subtitle-1 text-medium-emphasis">ID</div>

    <v-text-field density="compact" placeholder="id" :prepend-inner-icon="mdiEmailOutline" variant="outlined"
      v-model="loginData.id"></v-text-field>

    <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
      Password

      <a class="text-caption text-decoration-none text-blue" href="#" rel="noopener noreferrer" target="_blank">
        Forgot login password?</a>
    </div>

    <v-text-field :append-inner-icon="visible ? mdiEyeOff : mdiEye" :type="visible ? 'text' : 'password'"
      density="compact" placeholder="Enter your password" :prepend-inner-icon="mdiLockOutline" variant="outlined"
      @click:append-inner="visible = !visible" v-model="loginData.password"></v-text-field>

    <v-card class="mb-12" color="surface-variant" variant="tonal" v-show="isLoginError">
      <v-card-text class="text-medium-emphasis text-caption">
        아이디와 비밀번호를 확인해주세요.
      </v-card-text>
    </v-card>

    <v-btn class="mb-8" color="blue" size="large" variant="tonal" block @click="login">
      Log In
    </v-btn>

    <v-card-text class="text-center">
      <router-link class="text-blue text-decoration-none" :to="{ name: 'sign-up' }">Sign up now <v-icon
          :icon="mdiChevronRight"></v-icon></router-link>
    </v-card-text>
  </v-card>

</template>

<style scoped></style>