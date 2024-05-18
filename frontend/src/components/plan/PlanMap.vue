<script setup>
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import PlanPickDate from '@/components/plan/step/PlanPickDate.vue'
import PlanPickTripwith from '@/components/plan/step/PlanPickTripwith.vue'
import PlanPickPlace from '@/components/plan/step/PlanPickPlace.vue'
import PlanSearchPlace from '@/components/plan/step/PlanSearchPlace.vue'
import { mdiDotsVertical } from '@mdi/js';
import { ref } from 'vue';

const coordinate = {
    lat: 37.566826,
    lng: 126.9786567
};

const drawerWidth = 120
const stepDetailwidth = 350
const stepDetailFold = ref(true);

const curStep = ref(1);

const map = ref();

const onLoadKakaoMap = (mapRef) => {
    map.value = mapRef;

    kakao.maps.event.addListener(map.value, 'click', function (mouseEvent) {
        stepDetailFold.value = true;
    });
    kakao.maps.event.addListener(map.value, 'dragstart', function (mouseEvent) {
        stepDetailFold.value = true;
    });
};

</script>

<template>
    <v-navigation-drawer permanent :width="drawerWidth">
        <v-list>
            <v-list-item :to="{ name: 'Home' }">
                <template #title>
                    <v-sheet class="d-flex align-center justify-center">
                        <v-avatar tile image="src/assets/logo2.png" size="x-small">
                        </v-avatar>
                        <p class="ml-1" style="font-size: small; font-weight: bold">조아요행</p>
                    </v-sheet>

                </template>
            </v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list nav>
            <v-list-item value="step1" @click="curStep = 1">
                <template #title>
                    <v-sheet class="d-flex flex-column align-center">
                        <h3>STEP 1</h3>
                        <p>날짜 선택</p>
                    </v-sheet>
                </template>
            </v-list-item>
            <v-list-item value="step2" @click="curStep = 2">
                <template #title>
                    <v-sheet class="d-flex flex-column align-center">
                        <h3>STEP 2</h3>
                        <p>동행 선택</p>
                    </v-sheet>
                </template>
            </v-list-item>
            <v-list-item value="step3" @click="curStep = 3">
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
    <v-sheet :width="stepDetailFold ? 25 : stepDetailwidth"
        class="position-absolute border-e fill-height overflow-hidden" :style="{
            zIndex: 1004, left: drawerWidth + 'px', transitionProperty: 'width',
            transitionDuration: '0.2s',
            transitionTimingFunction: 'cubic-bezier(0.4, 0, 0.2, 1)'
        }">
        <div @click="stepDetailFold = !stepDetailFold" v-if="stepDetailFold"
            class="h-screen d-flex flex-column justify-center align-center">
            <v-icon :icon="mdiDotsVertical" size="small"></v-icon>
        </div>

        <v-container v-if="!stepDetailFold" class="h-screen d-flex flex-column justify-center"
            :style="{ minWidth: stepDetailwidth + 'px' }">
            <PlanPickDate v-if="curStep === 1" />
            <PlanPickTripwith v-if="curStep === 2" />
            <PlanPickPlace v-if="curStep === 3" />
        </v-container>
    </v-sheet>
    <v-main>
        <KakaoMap @on-load-kakao-map="onLoadKakaoMap" :lat="coordinate.lat" :lng="coordinate.lng" :draggable="true"
            height="100%" width="100%">
            <KakaoMapMarker :lat="coordinate.lat" :lng="coordinate.lng"></KakaoMapMarker>
        </KakaoMap>
    </v-main>

    <v-navigation-drawer permanent :width="curStep == 3 ? stepDetailwidth - 100 : 1" location="right">
        <PlanSearchPlace />
    </v-navigation-drawer>
</template>

<style>
html {
    overflow-y: hidden
}
</style>