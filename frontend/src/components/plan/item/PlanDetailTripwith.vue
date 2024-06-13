<script setup>
import { computed, watch } from 'vue';
import { mdiMinus } from '@mdi/js';
import { useTripwithStore } from '@/stores/tripwith';
import PlanDetailChat from '@/components/plan/item/PlanDetailChat.vue';


import defaultProfile from '@/assets/default_profile.png';

const tripwithStore = useTripwithStore()
const tripwith = computed(() => tripwithStore.tripwith);

</script>

<template>
    <v-container fluid class="border fill-height d-flex flex-column align-center">
        <h3 class="heading-with-underline">동행 목록</h3>
        <v-divider></v-divider>
        <v-row class="playground">
            <v-col class="col">
                <v-card v-for="(person) in tripwith" :key="person.id" class="box">
                    <v-row align="center" no-gutters>
                        <v-col cols="4" class="d-flex justify-center">
                            <img :src="person.profilePicture ? person.profilePicture : defaultProfile" alt="프로필 사진" class="profile-picture">
                        </v-col>
                        <v-col cols="4" class="d-flex flex-column align-start justify-center">
                            <span>{{ person.name }}</span>
                            <span class="small-id">{{ person.id }}</span>
                        </v-col>
                    </v-row>
                </v-card>
            </v-col>
        </v-row>
        <h3 class="heading-with-underline">채팅</h3>
        <v-divider></v-divider>
        <PlanDetailChat></PlanDetailChat>

    </v-container>
</template>

<style scoped>
.playground {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    margin-top: 20px;

}

.col {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
}

.box {
    display: flex;
    flex-direction: column;
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 10px;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.profile-picture {
    width: 50px;
    height: 50px;
    border-radius: 50%;
}

span {
    color: rgba(0, 0, 0, 0.74);
    font-size: small;
}

.small-id {
    color: rgba(128, 128, 128, 0.466);
    font-size: x-small;
}

.heading-with-underline {
    position: relative;
    display: inline-block;
    margin-bottom: 20px;
    padding-bottom: 5px;
    font-weight: bold;
}

.heading-with-underline::after {
    content: '';
    position: absolute;
    width: 100%;
    height: 2px;
    background-color: black;
    left: 0;
    bottom: 0;
}

.remove-btn {
    background-color: #f44336bd;
    color: white;
    border-radius: 50%;
    width: 24px; /* 버튼 크기 조정 */
    height: 24px;
    min-width: 24px;
    margin-right: 8px; /* 추가 마진 */
    box-shadow: none;
}
</style>
