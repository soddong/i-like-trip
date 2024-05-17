<script setup>
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import PlanPickDate from '@/components/plan/step/PlanPickDate.vue'
import { mdiNumeric1, mdiNumeric2, mdiNumeric3, mdiDotsVertical } from '@mdi/js';
import { ref } from 'vue';

const coordinate = {
    lat: 37.566826,
    lng: 126.9786567
};

const rail = ref(true);
</script>

<template>
    <v-navigation-drawer permanent width="150">
        <v-list>
            <v-list-item title="조아요행" :to="{ name: 'Home' }">
                <template v-slot:prepend>
                    <v-avatar tile image="src/assets/logo2.png" size="small">
                    </v-avatar>
                </template></v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list nav>
            <v-list-item value="step1">
                <template #title>
                    <v-sheet class="d-flex flex-column align-center">
                        <h3>STEP 1</h3>
                        <p>날짜 선택</p>
                    </v-sheet>
                </template>
            </v-list-item>
            <v-list-item value="step2">
                <template #title>
                    <v-sheet class="d-flex flex-column align-center">
                        <h3>STEP 2</h3>
                        <p>동행 선택</p>
                    </v-sheet>
                </template>
            </v-list-item>
            <v-list-item value="step3">
                <template #title>
                    <v-sheet class="d-flex flex-column align-center">
                        <h3>STEP 3</h3>
                        <p>장소 선택</p>
                    </v-sheet>
                </template>
            </v-list-item>
            <v-list-item value="step4">
                <template #title>
                    <v-sheet class="d-flex flex-column align-center">
                        <h3>STEP 4</h3>
                        <p>최종 확인</p>
                    </v-sheet>
                </template>
            </v-list-item>
        </v-list>
    </v-navigation-drawer>

    <v-navigation-drawer :rail="rail" permanent rail-width="30" width="400">
        <v-list class="fill-height">
            <v-list-item class="fill-height px-1" :ripple="false">
                <div @click="rail = !rail" class="fill-height" v-if="rail">
                    <v-icon :icon="mdiDotsVertical" size="small"></v-icon>
                </div>

                <v-container v-if="!rail" class="fill-height">
                    <v-row justify="center">
                        <PlanPickDate />
                    </v-row>

                </v-container>
            </v-list-item>
        </v-list>

    </v-navigation-drawer>

    <v-main>
        <KakaoMap :lat="coordinate.lat" :lng="coordinate.lng" :draggable="true" height="100%" width="100%">
            <KakaoMapMarker :lat="coordinate.lat" :lng="coordinate.lng"></KakaoMapMarker>
        </KakaoMap>
    </v-main>

    <!-- <v-navigation-drawer expand-on-hover rail location="right">
        <v-list>
            <v-list-item prepend-avatar="https://randomuser.me/api/portraits/women/85.jpg"
                subtitle="sandra_a88@gmailcom" title="Sandra Adams"></v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list density="compact" nav>
            <v-list-item :prepend-icon="mdiNumeric1" title="My Files" value="myfiles"></v-list-item>
            <v-list-item :prepend-icon="mdiNumeric2" title="Shared with me" value="shared"></v-list-item>
            <v-list-item :prepend-icon="mdiNumeric3" title="Starred" value="starred"></v-list-item>
        </v-list>
    </v-navigation-drawer> -->

</template>

<style scoped></style>