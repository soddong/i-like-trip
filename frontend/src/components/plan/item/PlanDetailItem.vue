<template>
  <v-app>
    <v-main>
      <v-container fluid class="border fill-height d-flex flex-column align-center">
        <v-row justify="center">
          <v-col cols="12" md="11">
            <v-row>
              <v-col cols="12">
                <v-text-field :value="title" placeholder="제목을 입력하세요." variant="plain" width="280px" hide-details
                  readonly density="compact">
                  <template #prepend>
                    <span>제목 : </span>
                  </template>
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field :value="comment" variant="plain" placeholder="메모를 입력하세요." width="280px" hide-details
                  readonly density="compact">
                  <template #prepend>
                    <span>메모 : </span>
                  </template>
                </v-text-field>
              </v-col>
              <v-col cols="12" style="text-align: center;">
                <h3>{{ date[0] }} ~ {{ date[1] }}</h3>
                <v-btn-toggle v-model="selectedDay" variant="outlined" divided class="place"
                  style="height: 30px; width: 300px; overflow-y: hidden; overflow-x: scroll" mandatory>
                  <v-btn size="x-small" :value="0">
                    all
                  </v-btn>
                  <v-btn size="x-small" v-for="item in planStore.getPeriodTime()" :key="item" :value="item">
                    {{ item }}
                  </v-btn>
                </v-btn-toggle>
              </v-col>
            </v-row>
            <v-row class="my-0">
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
                              <div style="  font-size: x-small;
  box-sizing: border-box;
  border-top: 1px solid rgb(204 226 232);">
                                {{ stopOver.start ? stopOver.start.getHours() : 0 }}:00
                              </div>
                              <div class="place-detail" @click="mapMove(stopOver.lat, stopOver.lng)">
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
                              <div class="hours end-hour" style="  font-size: x-small;
  box-sizing: border-box;
  border-bottom: 1px solid rgb(204 226 232);">
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
                <span v-if="isPublic"> 전체 공개</span>
                <span v-else> 동행 공개</span>
              </v-col>
            </v-row>
            <v-row class="generate-section">
              <v-col>
                <v-btn @click="canclePlan" variant="tonal" block>뒤로가기</v-btn>
              </v-col>
              <v-col v-if="canModify">
                <v-btn color="green" @click="modifyPlan" variant="tonal" block>수정</v-btn>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref, defineEmits, watch } from 'vue';
import { usePlanStore } from '@/stores/plan';
import { dateFromString } from '@/util/time-utils';
import { attrTypes } from '@/util/attraction-type';
import { mdiEmoticonCryOutline, mdiCarSide } from '@mdi/js';

const title = ref('');
const date = ref([]);
const comment = ref('');
const isPublic = ref(true);
const selectedDay = ref(0)
const planStore = usePlanStore()

const emits = defineEmits(['modify:plan', 'cancle:plan']);

defineProps({
  mapMove: Function,
  canModify: Boolean,
});

watch(() => planStore.planInfo, (newPlan) => {
  date.value = [
    dateFromString(planStore.getStartEnd()["start"]),
    dateFromString(planStore.getStartEnd()["end"])
  ];
  title.value = newPlan.title;
  comment.value = newPlan.comment;
  isPublic.value = newPlan.visibility === 1 ? true : false;
});

function modifyPlan() {
  emits('modify:plan');
}

function canclePlan() {
  emits('cancle:plan');
}
</script>

<style scoped>
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
  height: 5px;
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
