<template>
  <v-app>
    <v-main>
      <v-container fluid class="border fill-height d-flex flex-column align-center">
        <h3 class="heading-with-underline">여행 일정</h3>
        <v-divider></v-divider>
        <v-row justify="center" class="container">
          <v-col cols="12" md="11">
            <v-row class="info-section">
              <v-col cols="12">
                  <small class="section-label">제목</small>
                  <span>{{title}}</span>
              </v-col>
              <v-col cols="12">
                  <small class="section-label">커멘트</small>
                  <span>{{comment}}</span>
              </v-col>
              <v-col cols="12">
                <small class="section-label">날짜</small>
                <h4>{{ date[0] }} ~ {{date[1]}}</h4>
              </v-col>
            </v-row>
            <small class="section-label mb-4">장소</small>
            <v-row class="places-section">
              <v-card class="mx-auto place" width="310px" height="300px">
                <v-card-content class="center">
                    <v-list 
                        v-for="place in planStore.pickedPlace" 
                        :key="place.id" 
                        class="place-item"
                        @click="mapMove(place.lat, place.lng)"
                    >
                        {{ place.title }}
                    </v-list>
                </v-card-content>
              </v-card>
            </v-row>
            
            <v-row>
              <v-col>
                    <span v-if="isPublic">  전체 공개</span>
                    <span v-else>  동행 공개</span>
              </v-col>
            </v-row>
            <v-row class="generate-section">
                <v-col>
                    <v-btn @click="canclePlan" variant="tonal" block>취소</v-btn>
                </v-col>
                <v-col>
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
import { ref, onMounted, defineEmits, watch } from 'vue';
import { usePlanStore } from '@/stores/plan';
import { dateFromString } from '@/util/time-utils';

const title = ref('');
const date = ref([]);
const comment = ref('');
const isPublic = ref(true);

const planStore = usePlanStore()

const emits = defineEmits(['modify:plan', 'cancle:plan']);

defineProps({
    mapMove: Function,
});

watch(() => planStore.planInfo, (newPlan) => {
    date.value = [
        dateFromString(planStore.getStartEnd()["start"]),
        dateFromString(planStore.getStartEnd()["end"])
    ];
    title.value = newPlan.title;
    comment.value = newPlan.comment;
    isPublic.value = newPlan.visibility === 1? true:false;
});

function modifyPlan() {
  emits('modify:plan');
}

function canclePlan() {
  emits('cancle:plan');
}
</script>

<style scoped>
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
