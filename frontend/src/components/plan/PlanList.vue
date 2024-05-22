<script setup>
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import { listPlan } from '@/api/plan.js';
import PlanListItem from '@/components/plan/item/PlanListItem.vue';
import PlanListEmptyItem from '@/components/plan/item/PlanListEmptyItem.vue';

const route = useRoute();
const plans = ref([]);

const param = ref({
  keyword: "title",
  word: "",
});

const loadPlans = (params) => {
  listPlan(params, ({ data }) => {
    plans.value = [];
    data.forEach(plan => {
      const planObject = {
        title: plan.title,
        planId: plan.planId,
        makerId: plan.makerId,
        registDate: plan.registDate,
        visibility: plan.visibility,
        comment: plan.comment,
        img: `@/assets/random-images/0${plan.planId}.jpg`
      };
      plans.value.push(planObject);
      plans.value.push(planObject);
    });
    console.log("성공!!!!!!!!!");
    console.log(plans.value);
  }, error => {
    console.error(error);
  });
};

watch(() => route.query.keyword, (newKeyword) => {
  if (newKeyword) {
    loadPlans({ keyword: 'title', word: route.query.word });
  } else {
    loadPlans(param.value);
  }
}, { immediate: true });
</script>
<template>
  <v-main>
    <v-container>
      <v-row justify="center">
        <v-col cols="12" class="text-right">
          <v-btn rounded class="main-button" :to="{ name: 'plan-write' }" color="#F0BBB1"
            style=" width: 250px; font-size: 15px; color: white">게시글 작성하기</v-btn>
        </v-col>
        <v-col cols="12">
          <v-card class="common-card">
            <template v-if="plans.length > 0">
              <div class="grid-container">
                <div v-for="plan in plans" :key="plan.planId" class="grid-item">
                  <PlanListItem :plan="plan" />
                </div>
              </div>
            </template>
            <template v-else>
              <PlanListEmptyItem />
            </template>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: 'YourComponentName',
};
</script>

<style scoped>
.common-card {
  border: none;
  box-shadow: none;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  /* 한 줄에 네 개의 항목 */
  gap: 16px;
  /* 각 항목 사이의 간격 */
}

.grid-item {
  width: 100%;
}
</style>
