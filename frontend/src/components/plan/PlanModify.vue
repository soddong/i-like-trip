<script setup>
import { KakaoMap, KakaoMapMarker, KakaoMapPolyline } from 'vue3-kakao-maps';
import PlanPickDate from '@/components/plan/step/PlanPickDate.vue'
import PlanPickTripwith from '@/components/plan/step/PlanPickTripwith.vue'
import PlanPickTripwithSearch from '@/components/plan/step/item/PlanPickTripwithSearch.vue'
import PlanPickPlace from '@/components/plan/step/PlanPickPlace.vue'
import PlanSearchPlace from '@/components/plan/step/PlanSearchPlace.vue'
import PlanPickResult from '@/components/plan/step/PlanPickResult.vue'
import { mdiDotsVertical } from '@mdi/js';
import { ref, computed } from 'vue';
import { createPlan } from "@/api/plan";
import { usePlanStore } from "@/stores/plan";
import { useTripwithStore } from "@/stores/tripwith";
import { useUserStore } from "@/stores/user";
import { useRouter, onBeforeRouteLeave } from "vue-router"
import { getPlanPath } from '@/api/plan';

const router = useRouter()
const coordinate = {
  lat: 33.45, lng: 126.571
};

const drawerWidth = 150
const stepDetailwidth = 400
const stepDetailFold = ref(true);

const curStep = ref(1);

const map = ref();

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
  if (map.value) {
    map.value.panTo(new kakao.maps.LatLng(lat, lng));
  }
}

const planStore = usePlanStore()
const { pickedPlace, } = planStore
const tripwithStore = useTripwithStore()
const userStore = useUserStore()

const latLngList = ref([]);

const plan = ref({
  title: "",
  makerId: computed(() => userStore.userId),
  visibility: "",
  comment: ""
});

function makePathPolyline() {
  let data = {
    "origin": {
      "name": pickedPlace[0].title,
      "x": pickedPlace[0].lng,
      "y": pickedPlace[0].lat,
    },
    "destination": {
      "name": pickedPlace[pickedPlace.length - 1].title,
      "x": pickedPlace[pickedPlace.length - 1].lng,
      "y": pickedPlace[pickedPlace.length - 1].lat,
    }
  }
  if (planStore.pickedPlace.length > 2) {
    const waypoints = planStore.pickedPlace.slice(1, -1).map(place => ({
      name: place.title,
      x: place.lng,
      y: place.lat,
    }));
    data.waypoints = waypoints;
  }
  getPlanPath(data, (res) => {
    const path = [];
    const result = res.data.routes[0];
    if (result.result_code != 0) return;

    result.sections.forEach(section => {
      section.roads.forEach(road => {
        for (let index = 0; index < road.vertexes.length; index += 2) {
          path.push({
            lat: road.vertexes[index + 1],
            lng: road.vertexes[index]
          });
        }
      });
    });
    latLngList.value = path;
  }, (e) => { console.log(e); });
}

function registerPlan() {
  if (!pickedPlace) {
    return;
  }

  const newPlan = {
    plan: { ...plan.value, startTime: planStore.period[0], endTime: planStore.period[planStore.period.length - 1] },
    places: pickedPlace.map((attr, index) => {
      const { start, end } = planStore.getPlaceStartEnd(index);
      return {
        order: index + 1,
        startTime: start,
        endTime: end,
        comment: '',
        place: {
          attractionId: attr.attractionId,
          lat: attr.lat,
          lng: attr.lng,
        },
      };
    }),
    members: tripwithStore.tripwith,
  };

  createPlan(newPlan, () => {
    alert('계획을 등록하였습니다.');

    planStore.resetPlan();
    tripwithStore.resetTripwith();

    moveList();
  }, (error) => {
    console.error(error);
    alert('등록에 실패했습니다.');
  });
}

function canclePlan() {
  planStore.resetPlan();
  tripwithStore.resetTripwith();

  moveList();
}

function moveList() {
  router.replace({ name: "plan-list" })
}

function moveToStep4() {
  if (planStore.isEmpty()) {
    alert('STEP 1, 2, 3을 완료해야 합니다.');
    return;
  }
  attrList.value = []
  curStep.value = 4;
}

function completeStep(step) {
  if (step === 1) {
    plan.value.startDate = planStore.getStartEnd().start;
    plan.value.endDate = planStore.getStartEnd().end;
  } else if (step === 2) {
    // Additional logic for step 2 completion
  } else if (step === 3) {
    makePathPolyline();
  }
  curStep.value = step + 1;
}

function updatePlan(updatedPlan) {
  plan.value = { ...plan.value, ...updatedPlan };
}

onBeforeRouteLeave(() => {
  planStore.resetPlan();
  tripwithStore.resetTripwith();
});


</script>

<template>
  <v-navigation-drawer permanent :width="drawerWidth">
    <v-list>
      <v-list-item title="조아요행" :to="{ name: 'Home' }">
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
      <v-list-item value="step4" @click="moveToStep4">
        <template #title>
          <v-sheet class="d-flex flex-column align-center">
            <h3>STEP 4</h3>
            <p>최종 확인</p>
          </v-sheet>
        </template>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>

  <v-sheet :width="stepDetailFold ? 25 : stepDetailwidth" class="position-absolute border-e fill-height overflow-hidden"
    :style="{
      zIndex: 1004, left: drawerWidth + 'px', transitionProperty: 'width',
      transitionDuration: '0.2s', transitionTimingFunction: 'cubic-bezier(0.4, 0, 0.2, 1)'
    }">
    <div @click="stepDetailFold = !stepDetailFold" v-if="stepDetailFold"
      class="h-screen d-flex flex-column justify-center align-center">
      <v-icon :icon="mdiDotsVertical" size="small"></v-icon>
    </div>
    <v-container class="h-screen d-flex flex-column justify-center" :style="{ minWidth: stepDetailwidth + 'px' }">
      <PlanPickDate v-if="curStep === 1" @complete="completeStep(1)" />
      <PlanPickTripwith v-if="curStep === 2" @complete="completeStep(2)" />
      <PlanPickPlace v-if="curStep === 3" :attrList="attrList" :mapMove="mapMove" :makePathPolyline="makePathPolyline"
        @complete="completeStep(3)" />
      <PlanPickResult v-if="curStep === 4" :attrList="attrList" :mapMove="mapMove" v-model="plan"
        @registerPlan="registerPlan" @cancle:plan="canclePlan" @update:plan="updatePlan" />
    </v-container>
  </v-sheet>
  <v-main>
    <KakaoMap @on-load-kakao-map="onLoadKakaoMap" :lat="coordinate.lat" :lng="coordinate.lng" :draggable="true"
      height="100%" width="100%">
      <KakaoMapMarker v-for="item in attrList" :key="item.attractionId" :lat="item.lat" :lng="item.lng" :image="{
        imageSrc: `/src/assets/marker/type${item.attractionType}.png`,
        imageWidth: 25,
        imageHeight: 35,
        imageOption: {}
      }"></KakaoMapMarker>
            <KakaoMapMarker v-for="item in pickedPlace" :key="item.attractionId" :lat="item.lat" :lng="item.lng" :image="{
        imageSrc: `/src/assets/map-marker-check.png`,
        imageWidth: 40,
        imageHeight: 40,
        imageOption: {}
      }" :icon="mdiMapMarkerCheck"></KakaoMapMarker>
      <KakaoMapPolyline :latLngList="latLngList" />
    </KakaoMap>
  </v-main>

  <v-navigation-drawer permanent :width="curStep == 2 ? stepDetailwidth : 1" location="right">
    <v-sheet>
      <PlanPickTripwithSearch v-if="curStep == 2" />
    </v-sheet>
  </v-navigation-drawer>
  <v-navigation-drawer permanent :width="curStep == 3 ? stepDetailwidth - 50 : 1" location="right">
    <PlanSearchPlace @open-detail="openDetail" @change-attr-list="changeAttrList" :mapMove="mapMove"
      :attrList="attrList" />
  </v-navigation-drawer>
</template>

<style>
html {
  overflow: auto;
}
</style>
