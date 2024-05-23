<template>
  <v-app>
    <v-main>
      <v-container fluid class="border fill-height d-flex flex-column align-center">
        <v-row justify="center" class="container">
          <v-col cols="12" md="11">
            <v-row class="info-section">
              <!-- <v-col cols="12">
                  <small class="section-label">제목</small>
                  <v-text-field v-model="title" placeholder="제목을 입력하세요." variant="underlined" width="280px"/>
              </v-col>
              <v-col cols="12">
                  <small class="section-label">커멘트</small>
                  <v-text-field v-model="comment" variant="underlined" placeholder="커멘트를 입력하세요." width="280px"/>
              </v-col> -->
              <v-col cols="12">
                <h2>{{ date[0] }} ~ {{ date[1] }}</h2>
                <v-btn-toggle v-model="selectedDay" variant="outlined" divided style="height: 30px;" mandatory>
                  <v-btn size="x-small" :value="0">
                    all
                  </v-btn>
                  <v-btn size="x-small" v-for="item in planStore.getPeriodTime()" :key="item" :value="item">
                    {{ item }}
                  </v-btn>
                </v-btn-toggle>
              </v-col>
            </v-row>
            <small class="section-label mb-4">장소</small>
            <v-row class="places-section">
              <v-sheet>

              </v-sheet>
              <v-card class="mx-auto place" width="350px" height="600px">
                <v-card-content class="center">
                  <div class="days">
                    <div class="day d-flex" style="border-bottom: 1px solid rgb(70 113 142);color: rgb(32 67 92);"
                      v-for="(day, index) in planStore.getPlacePerDay()" :key="index">
                      <template v-if="(selectedDay == 0 || selectedDay == (index + 1))">
                        <div class="day-side"
                          style="writing-mode: vertical-rl; text-orientation: upright; font-size: x-small; background-color: rgb(204 226 232); color: rgb(32 67 92) ">
                          {{ index + 1 }}일차
                        </div>
                        <div class=" d-flex flex-column" style="flex-grow: 1;">
                          <template v-if="day.length == 0">
                            <div class="my-2">
                              일정이 없어요... <v-icon :icon="mdiEmoticonCryOutline"></v-icon>
                            </div>
                          </template>
                          <template v-else>
                            <div class="stopover" v-for="stopOver in day" :key="stopOver.id">
                              <div class="hours start-hour">
                                {{ stopOver.start ? stopOver.start.getHours() : 0 }}:00
                              </div>
                              <div class="place-detail">
                                <v-container class="pa-0 grid-stack-item" style="height: 100px;">
                                  <v-row class="ma-0 grid-stack-item-content " style="height: 100%;">
                                    <v-col cols="4" style="height: 100%;">
                                      <v-img cover rounded style="height: 100%; width: 100%;"
                                        :src="'/src/assets/logo2.png'">
                                      </v-img>
                                    </v-col>
                                    <v-col cols="8">
                                      <v-sheet class="d-flex flex-column h-100 justify-space-evenly">
                                        <div class="text-truncate pb-1" style="font-size: small;">{{ stopOver.title }}
                                        </div>
                                        <div class="text-truncate pb-1" style="font-size: x-small;">{{ stopOver.addr }}
                                        </div>
                                        <div style="font-size: x-small;">
                                          <v-chip size="x-small"
                                            :prepend-icon="attrTypes[stopOver.attractionType].icon">{{
                                              attrTypes[stopOver.attractionType].title }}</v-chip>
                                        </div>
                                      </v-sheet>
                                    </v-col>
                                  </v-row>
                                </v-container>
                              </div>
                              <div class="hours end-hour">
                                {{ stopOver.end ? stopOver.end.getHours() + 1 : 24 }}:00
                              </div>
                              <div class="divider my-1">
                                <v-icon :icon="mdiCarSide"></v-icon>
                                <!-- <v-icon :icon="mdiDotsVertical"  ></v-icon> -->
                                <!-- <v-icon :icon="mdiWalk"></v-icon> -->
                              </div>
                            </div>
                          </template>
                        </div>
                      </template>
                    </div>
                  </div>
                </v-card-content>
              </v-card>
            </v-row>

            <v-row>
              <v-col>
                <input type="checkbox" v-model="isPublic" />
                <span> 전체 공개</span>
              </v-col>
            </v-row>
            <v-row class="generate-section">
              <v-col>
                <v-btn @click="canclePlan" variant="tonal" block>취소</v-btn>
              </v-col>
              <v-col>
                <v-btn color="primary" @click="handleRegisterPlan" variant="tonal" block>일정 생성</v-btn>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref, onMounted, defineEmits, watch } from 'vue';
import { usePlanStore } from '@/stores/plan';
import { storeToRefs } from "pinia";

import { dateFromString } from '@/util/time-utils';

import { attrTypes } from '@/util/attraction-type';
import { mdiDotsVertical, mdiEmoticonCryOutline, mdiCarSide, mdiWalk } from '@mdi/js';

const title = ref('');
const date = ref([]);
const comment = ref('');
const isPublic = ref(true);

const planStore = usePlanStore()
const { pickedPlace } = storeToRefs(planStore)

const emits = defineEmits(['registerPlan', 'update:plan', 'cancle:plan']);

const props = defineProps({
  mapMove: Function,
  attrList: Array,
  makePathPolyline: Function,
  plan: Object
});

onMounted(() => {
  date.value = [
    dateFromString(planStore.getStartEnd()["start"]),
    dateFromString(planStore.getStartEnd()["end"])
  ];
  title.value = planStore.planInfo.title;
  comment.value = planStore.planInfo.comment;
  isPublic.value = planStore.planInfo.visibility === 1 ? true : false;
});

function handleRegisterPlan() {
  console.log("handle register")
  emits('update:plan', {
    ...props.plan,
    title: title.value,
    comment: comment.value,
    visibility: isPublic.value === true ? 1 : 2,
  });
  emits('registerPlan');
}

function canclePlan() {
  emits('cancle:plan');
}

const selectedDay = ref(0)

</script>

<style scoped>
.hours {
  font-size: x-small;
  box-sizing: border-box;
}

.start-hour {
  border-top: 1px solid rgb(204 226 232);
}

.end-hour {
  border-bottom: 1px solid rgb(204 226 232);
}


.container {
  margin-top: 20px;
}

.card-section {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 20px;
  background-color: #fff;
}

.main-image {
  border-radius: 8px;
  margin-bottom: 20px;
}

.info-section {
  margin-bottom: 20px;
}

.editable-section {
  margin-bottom: 20px;
}

.editable-display {
  display: flex;
  align-items: center;
  justify-content: space-between;
  cursor: pointer;
}

.editable-input {
  display: flex;
  align-items: center;
}

.editable-input .v-text-field {
  flex: 1;
}

.places-section {
  margin-bottom: 20px;
}

.place-image {
  border-radius: 8px;
  margin-bottom: 10px;
  cursor: pointer;
}

.accommodation-section {
  margin-bottom: 20px;
}

.buttons-section {
  margin-bottom: 20px;
}

.edit-text {
  margin-left: 10px;
  color: #1e88e5;
  cursor: pointer;
  text-decoration: underline;
}

.section-label {
  font-size: 0.9em;
  color: gray;
  display: block;
  margin-bottom: 5px;
}

.place {
  overflow-y: scroll
}


/* 스크롤바 설정*/
.place::-webkit-scrollbar {
  width: 5px;
}

/* 스크롤바 막대 설정*/
.place::-webkit-scrollbar-thumb {
  background-color: rgb(83, 83, 83);
  /* 스크롤바 둥글게 설정 */
  border-radius: 5px;
}

/* 스크롤바 뒷 배경 설정*/
.place::-webkit-scrollbar-track {
  background-color: rgba(0, 0, 0, 0);
}

.center {
  justify-content: center;
  align-items: center;
  height: 100%;
  text-align: center;
}

.place-item {
  cursor: pointer;
  transition: background-color 0.3s;
}

.place-item:hover {
  background-color: rgba(0, 0, 0, 0.1);
}
</style>
