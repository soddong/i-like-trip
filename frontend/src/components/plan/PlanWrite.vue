<script setup>
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import PlanPickDate from '@/components/plan/step/PlanPickDate.vue'
import PlanPickTripwith from '@/components/plan/step/PlanPickTripwith.vue'
import PlanPickTripwithSearch from '@/components/plan/step/item/PlanPickTripwithSearch.vue'
import PlanPickPlace from '@/components/plan/step/PlanPickPlace.vue'
import PlanSearchPlace from '@/components/plan/step/PlanSearchPlace.vue'
import { usePlanStore } from "@/stores/plan";
import { useTripwithStore } from "@/stores/tripwith";
import { onBeforeRouteLeave } from "vue-router"
import createPlan from '@/api/plan.js'
import { mdiDotsVertical } from '@mdi/js';
import { ref } from 'vue';

const coordinate = {
    lat: 37.566826,
    lng: 126.9786567
};

const planStore = usePlanStore();
const tripwithStore = useTripwithStore()

const drawerWidth = 150
const stepDetailwidth = 400
const stepDetailFold = ref(true);

const curStep = ref(1);

const map = ref();

const tripwith = ref([]);
const attrList = ref([]);
const onLoadKakaoMap = (mapRef) => {
    map.value = mapRef;

    kakao.maps.event.addListener(map.value, 'click', function (mouseEvent) {
        stepDetailFold.value = true;
    });
    kakao.maps.event.addListener(map.value, 'dragstart', function (mouseEvent) {
        stepDetailFold.value = true;
    });
};
const changeAttrList = (data) => {
    attrList.value = data
}

const openDetail = () => {
    stepDetailFold.value = false;
}

const mapMove = (lat, lng) => {
    console.log(lat, lng, "불림")
    if (map.value) {
        // 지도 중심을 부드럽게 이동시킵니다
        // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
        map.value.panTo(new kakao.maps.LatLng(lat, lng));
    }
}

const handleCreatePlan = () => {
  const newPlan = {
    plan: {

    }, 
    places: attrList.value.map((attr, index) => ({
      order: index + 1,
      startTime: '',
      endTime: '',
      comment: '',
      place: attr,
    })),
    members: tripwith.value,
  };

  createPlan(
    newPlan,
    () => {
      alert('Plan created successfully!');
    },
    (error) => {
      console.error(error);
      alert('Failed to create plan');
    }
  );
};

onBeforeRouteLeave(() => {
    planStore.resetPlan();
    tripwithStore.resetTripwith();
});
</script>

<template>
    <v-navigation-drawer permanent :width="drawerWidth">
        <v-list>
            <v-list-item title="조아요행" :to="{ name: 'Home' }">
                <template v-slot:prepend>
                    <v-avatar tile image="src/assets/logo2.png" size="small">
                    </v-avatar>
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

        <v-container class="h-screen d-flex flex-column justify-center" :style="{ minWidth: stepDetailwidth + 'px' }">
            <PlanPickDate v-if="curStep === 1" />
            <PlanPickTripwith v-if="curStep === 2" v-model="tripwith" />
            <PlanPickPlace v-if="curStep === 3" />
        </v-container>
    </v-sheet>
    <v-main>
        <KakaoMap @on-load-kakao-map="onLoadKakaoMap" :lat="coordinate.lat" :lng="coordinate.lng" :draggable="true"
            height="100%" width="100%">
            <KakaoMapMarker v-for="item in attrList" :key="item.attractionId" :lat="item.lat" :lng="item.lng" :image="{
                imageSrc: 'src/assets/marker/type12.png',
                imageWidth: 25,
                imageHeight: 35,
                imageOption: {}
            }"></KakaoMapMarker>
        </KakaoMap>
    </v-main>

    <v-navigation-drawer permanent :width="curStep == 2 ? stepDetailwidth : 1" location="right">
        <v-sheet>
            <PlanPickTripwithSearch v-if="curStep == 2" v-model="tripwith" />
        </v-sheet>
    </v-navigation-drawer>
    <v-navigation-drawer permanent :width="curStep == 3 ? stepDetailwidth - 50 : 1" location="right">
        <PlanSearchPlace @open-detail="openDetail" @change-attr-list="changeAttrList" :mapMove="mapMove"
            :attrList="attrList" />
    </v-navigation-drawer>
    <v-btn @click="handleCreatePlan">Create Plan</v-btn>
    
</template>

<style>
html {
    overflow: auto;
}
</style>