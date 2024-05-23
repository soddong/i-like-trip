<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
const userStore = useUserStore();
const router = useRouter();

const signUpData = ref({
    id: "",
    password: "",
    email: "",
    emailDomain: "",
    name: "",
    location: ""
});

const errors = ref({
    id: "",
    password: "",
    email: "",
    name: ""
});

const emailDomains = ["naver.com", "gmail.com", "daum.net", "ssafy.com"];

const validateInput = () => {
    let valid = true;
    errors.value.id = "";
    errors.value.password = "";
    errors.value.email = "";
    errors.value.name = "";

    if (signUpData.value.id.length < 3) {
        errors.value.id = "ID는 3자 이상이어야 합니다";
        valid = false;
    }

    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])/;
    if (!passwordRegex.test(signUpData.value.password)) {
        errors.value.password = "암호에는 문자, 숫자 및 특수 문자가 포함되어야 합니다";
        valid = false;
    }

    if (signUpData.value.name.trim() === "") {
        errors.value.name = "닉네임은 필수로 입력해야 합니다";
        valid = false;
    }

    return valid;
};

const signup = async () => {
    if (validateInput()) {
        signUpData.value.email = `${signUpData.value.email}@${signUpData.value.emailDomain}`;
        console.log("하이")
        await userStore.userSingUp(signUpData.value);
    }
};
</script>

<template>
    <v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg" style="margin-top: 150px">
        <div class="text-subtitle-1 text-medium-emphasis">ID</div>
        <v-text-field density="compact" placeholder="ID" :prepend-inner-icon="mdiEmailOutline" variant="outlined"
            v-model="signUpData.id"></v-text-field>
        <span v-if="errors.id" class="error-message">{{ errors.id }}</span>

        <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
            Password
        </div>
        <v-text-field :append-inner-icon="visible ? mdiEyeOff : mdiEye" :type="visible ? 'text' : 'password'"
            density="compact" placeholder="Enter your password" :prepend-inner-icon="mdiLockOutline" variant="outlined"
            @click:append-inner="visible = !visible" v-model="signUpData.password"></v-text-field>
        <span v-if="errors.password" class="error-message">{{ errors.password }}</span>

        <div class="text-subtitle-1 text-medium-emphasis">Email</div>
        <div class="d-flex">
            <v-text-field density="compact" placeholder="Email" :prepend-inner-icon="mdiEmailOutline" variant="outlined"
                v-model="signUpData.email" style="flex: 1;"></v-text-field>
            <span class="email-at">@</span>
            <v-autocomplete density="compact" placeholder="Select domain" :items="emailDomains" variant="outlined"
                v-model="signUpData.emailDomain" style="flex: 1;"></v-autocomplete>
        </div>
        <span v-if="errors.email" class="error-message">{{ errors.email }}</span>

        <div class="text-subtitle-1 text-medium-emphasis">Nickname</div>
        <v-text-field density="compact" placeholder="Nickname" :prepend-inner-icon="mdiEmailOutline" variant="outlined"
            v-model="signUpData.name"></v-text-field>
        <span v-if="errors.name" class="error-message">{{ errors.name }}</span>

        <div class="text-subtitle-1 text-medium-emphasis">Location</div>
        <v-text-field density="compact" placeholder="Location" :prepend-inner-icon="mdiEmailOutline" variant="outlined"
            v-model="signUpData.location"></v-text-field>

        <v-btn class="mb-8" color="blue" size="large" variant="tonal" block @click="signup">
            Sign Up
        </v-btn>
    </v-card>
</template>

<style scoped>
.error-message {
    color: red;
    font-size: 12px;
}
.email-at {
    align-self: center;
    margin: 0 10px;
}


</style>
