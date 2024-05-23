<script setup>
import { KakaoMap, KakaoMapMarker, KakaoMapPolyline } from 'vue3-kakao-maps';
import PlanPickDate from '@/components/plan/step/PlanPickDate.vue'
import PlanPickTripwith from '@/components/plan/step/PlanPickTripwith.vue'
import PlanPickTripwithSearch from '@/components/plan/step/item/PlanPickTripwithSearch.vue'
import PlanPickPlace from '@/components/plan/step/PlanPickPlace.vue'
import PlanSearchPlace from '@/components/plan/step/PlanSearchPlace.vue'
import PlanPickResult from '@/components/plan/step/PlanPickResult.vue'
import PlanDetailItem from '@/components/plan/item/PlanDetailItem.vue'
import PlanDetailTripwith from '@/components/plan/item/PlanDetailTripwith.vue'

import { mdiDotsVertical } from '@mdi/js';
import { ref, computed, onMounted, watch } from 'vue';
import { getPlanPath, createPlan, detailPlan } from "@/api/plan";
import { usePlanStore } from "@/stores/plan";
import { useTripwithStore } from "@/stores/tripwith";
import { useUserStore } from "@/stores/user";
import { useRoute, useRouter, onBeforeRouteLeave } from "vue-router"


const router = useRouter()
const route = useRoute();
const planno = ref(null);

const coordinate = { 
    lat: 33.45, lng: 126.571
};

const drawerWidth = 150
const stepDetailwidth = 400
const stepDetailFold = ref(true);
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

const mapMove = (lat, lng) => {
    if (map.value) {
        map.value.panTo(new kakao.maps.LatLng(lat, lng));
    }
}

const planStore = usePlanStore();
const { pickedPlace } = planStore;
const tripwithStore = useTripwithStore()

const latLngList = ref([
    { lat: 33.45, lng: 126.571 },
    { lat: 33.449, lng: 126.5705 },
    { lat: 33.45, lng: 126.5725 }
]);

onMounted(() => {
  planno.value = route.params.planno;
  getPlan();
});

watch(() => planStore.pickedPlace, (newValue) => {
  if (newValue.length > 0) {
    makePathPolyline();
  }
}, { immediate: true });

onBeforeRouteLeave(() => {
    planStore.resetPlan();
    tripwithStore.resetTripwith();
});

const getPlan = () => {
  detailPlan(
    planno.value,
    ({ data }) => {
      planStore.updatePickedPlace(data.places);
      planStore.updatePlanInfo(data.plan);
      tripwithStore.updateTripwith(data.members);
    },
    (error) => {
      console.log(error);
    }
  );
};

const makePathPolyline = () => {
  const data = {
    origin: {
      name: planStore.pickedPlace[0].title,
      x: planStore.pickedPlace[0].lng,
      y: planStore.pickedPlace[0].lat,
    },
    destination: {
      name: planStore.pickedPlace[planStore.pickedPlace.length - 1].title,
      x: planStore.pickedPlace[planStore.pickedPlace.length - 1].lng,
      y: planStore.pickedPlace[planStore.pickedPlace.length - 1].lat,
    }
  };

  if (planStore.pickedPlace.length > 2) {
    const waypoints = planStore.pickedPlace.slice(1, -1).map(place => ({
      name: place.title,
      x: place.lng,
      y: place.lat,
    }));
    data.waypoints = waypoints;
  }

  // getPlanPath(data, (res) => {
  //   const path = [];
  //   const result = res.data.routes[0];
  //   if (result.result_code != 0) return;

  //   result.sections.forEach(section => {
  //     section.roads.forEach(road => {
  //       for (let index = 0; index < road.vertexes.length; index += 2) {
  //         path.push({
  //           lat: road.vertexes[index + 1],
  //           lng: road.vertexes[index]
  //         });
  //       }
  //     });
  //   });
  //   latLngList.value = path;
  // }, (e) => { console.log(e); });
};
function registerPlan() {
  if (!pickedPlace) {
    return;
  }

  const newPlan = {
    plan: { ...plan.value },
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
          title: attr.title
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
    alert('계획이 취소되었습니다.');

    planStore.resetPlan();
    tripwithStore.resetTripwith();

    moveList();
}

function moveList() {
  router.replace({ name: "plan-list" })
}

function moveModify() {
  router.replace({ name: "plan-modify", params: planStore.planInfo.planId });
}

</script>

<template>
    <v-navigation-drawer permanent :width="drawerWidth">
      <v-list>
        <v-list-item title="조아요행" :to="{ name: 'Home' }">
          <template v-slot:prepend>
            <v-avatar tile image="src/assets/logo2.png" size="small"></v-avatar>
          </template>
        </v-list-item>
      </v-list>
  
      <v-divider></v-divider>

    </v-navigation-drawer>
  
    <v-sheet class="position-absolute border-e fill-height overflow-hidden" :style="{
               zIndex: 1004, left: drawerWidth + 'px', transitionProperty: 'width',
               transitionDuration: '0.2s', transitionTimingFunction: 'cubic-bezier(0.4, 0, 0.2, 1)',
               width: stepDetailwidth + 'px'
             }">
      <v-container class="h-screen d-flex flex-column justify-center" :style="{ minWidth: stepDetailwidth + 'px' }">
        <PlanDetailItem :mapMove="mapMove" @cancle:plan="canclePlan" @modify:plan="moveModify"/>
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
            <KakaoMapPolyline :latLngList="latLngList" />
        </KakaoMap>
    </v-main>

    <v-navigation-drawer permanent :style="{ width: stepDetailwidth + 'px' }" location="right">
        <v-sheet>
            <PlanDetailTripwith />
        </v-sheet>
    </v-navigation-drawer>
</template>

<style>
html {
    overflow: auto;
}
</style>
