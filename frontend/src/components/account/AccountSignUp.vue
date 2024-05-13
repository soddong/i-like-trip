<script setup>

</script>

<template>
    <v-card class="mx-auto pa-12 pb-8 mt-6" elevation="8" max-width="448" rounded="lg">
        <div class="text-subtitle-1 text-medium-emphasis">ID</div>

        <v-text-field density="compact" placeholder="Email address" :prepend-inner-icon="mdiEmailOutline"
            variant="outlined"></v-text-field>

        <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
            Password
        </div>

        <v-text-field :append-inner-icon="visible ? mdiEyeOff : mdiEye" :type="visible ? 'text' : 'password'"
            density="compact" placeholder="Enter your password" :prepend-inner-icon="mdiLockOutline" variant="outlined"
            @click:append-inner="visible = !visible"></v-text-field>

        <div class="text-subtitle-1 text-medium-emphasis">전화번호</div>

        <v-text-field density="compact" placeholder="Email address" :prepend-inner-icon="mdiEmailOutline"
            variant="outlined"></v-text-field>

        <div class="text-subtitle-1 text-medium-emphasis">이름</div>

        <v-text-field density="compact" placeholder="Email address" :prepend-inner-icon="mdiEmailOutline"
            variant="outlined"></v-text-field>

        <div class="text-subtitle-1 text-medium-emphasis">거주지역</div>

        <v-text-field density="compact" placeholder="Email address" :prepend-inner-icon="mdiEmailOutline"
            variant="outlined"></v-text-field>

        <v-btn class="mb-8" color="blue" size="large" variant="tonal" block>
            Sign Up
        </v-btn>
    </v-card>
</template>

<style scoped></style>