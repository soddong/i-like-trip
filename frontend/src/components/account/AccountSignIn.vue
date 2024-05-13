<script setup>
import { ref } from 'vue';
import { mdiEmailOutline, mdiEyeOff, mdiEye, mdiLockOutline, mdiChevronRight } from '@mdi/js';
import {RouterLink} from 'vue-router'
const visible = ref(false)
</script>

<template>
    <v-card class="mx-auto pa-12 pb-8 mt-6" elevation="8" max-width="448" rounded="lg">
      <div class="text-subtitle-1 text-medium-emphasis">Account</div>

      <v-text-field density="compact" placeholder="Email address" :prepend-inner-icon="mdiEmailOutline"
        variant="outlined"></v-text-field>

      <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
        Password

        <a class="text-caption text-decoration-none text-blue" href="#" rel="noopener noreferrer" target="_blank">
          Forgot login password?</a>
      </div>

      <v-text-field :append-inner-icon="visible ? mdiEyeOff : mdiEye" :type="visible ? 'text' : 'password'"
        density="compact" placeholder="Enter your password" :prepend-inner-icon="mdiLockOutline" variant="outlined"
        @click:append-inner="visible = !visible"></v-text-field>

      <v-card class="mb-12" color="surface-variant" variant="tonal">
        <v-card-text class="text-medium-emphasis text-caption">
          Warning: After 3 consecutive failed login attempts, you account will be temporarily locked for three hours. If
          you must login now, you can also click "Forgot login password?" below to reset the login password.
        </v-card-text>
      </v-card>

      <v-btn class="mb-8" color="blue" size="large" variant="tonal" block>
        Log In
      </v-btn>

      <v-card-text class="text-center">
        <router-link class="text-blue text-decoration-none" :to="{name:'sign-up'}">Sign up now <v-icon :icon="mdiChevronRight"></v-icon></router-link>
      </v-card-text>
    </v-card>

</template>

<style scoped></style>