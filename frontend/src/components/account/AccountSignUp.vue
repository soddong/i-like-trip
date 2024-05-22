<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router"
import { useUserStore } from "@/stores/user"
const userStore = useUserStore()
const router = useRouter();

const signUpData = ref({
    id: "",
    password: "",
    email: "",
    name: "",
    location: ""
})

const signup = async () => {
    await userStore.userSingUp(signUpData.value);
}
</script>

<template>
    <v-card class="mx-auto pa-12 pb-8 mt-6" elevation="8" max-width="448" rounded="lg">
        <div class="text-subtitle-1 text-medium-emphasis">ID</div>

        <v-text-field density="compact" placeholder="ID" :prepend-inner-icon="mdiEmailOutline" variant="outlined"
            v-model="signUpData.id"></v-text-field>

        <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
            Password
        </div>

        <v-text-field :append-inner-icon="visible ? mdiEyeOff : mdiEye" :type="visible ? 'text' : 'password'"
            density="compact" placeholder="Enter your password" :prepend-inner-icon="mdiLockOutline" variant="outlined"
            @click:append-inner="visible = !visible" v-model="signUpData.password"></v-text-field>

        <div class="text-subtitle-1 text-medium-emphasis">Email</div>

        <v-text-field density="compact" placeholder="Email address" :prepend-inner-icon="mdiEmailOutline"
            variant="outlined" v-model="signUpData.email"></v-text-field>

        <div class="text-subtitle-1 text-medium-emphasis">Nickname</div>

        <v-text-field density="compact" placeholder="Nickname" :prepend-inner-icon="mdiEmailOutline" variant="outlined"
            v-model="signUpData.name"></v-text-field>

        <div class="text-subtitle-1 text-medium-emphasis">Location</div>

        <v-text-field density="compact" placeholder="location" :prepend-inner-icon="mdiEmailOutline" variant="outlined"
            v-model="signUpData.location"></v-text-field>

        <v-btn class="mb-8" color="blue" size="large" variant="tonal" block @click="signup">
            Sign Up
        </v-btn>
    </v-card>
</template>

<style scoped></style>