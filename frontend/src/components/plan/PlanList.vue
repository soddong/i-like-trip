<script setup>
import { ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { listPlan } from '@/api/plan.js';
import PlanListItem from '@/components/plan/item/PlanListItem.vue';
import PlanListEmptyItem from '@/components/plan/item/PlanListEmptyItem.vue';
import PlanHeaderItem from '@/components/plan/item/PlanHeaderItem.vue';

const router = useRouter();
const route = useRoute();
const plans = ref([]);
const param = ref({
  keyword: "",
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
      };
      plans.value.push(planObject);
    });
    console.log("성공!!!!!!!!!");
    console.log(plans.value);
  }, error => {
    console.error(error);
  });
};


// const loadArticles = (params) => {
//   listArticle(params, ({ data }) => {
//     articles.value = data.articles;
//     hasError.value = false;
//     articles.value.forEach(article => {
//       getMainImageUrl(article.articleNo, (blob) => {
//         const imageUrl = URL.createObjectURL(blob);
//         articleImages.value[article.articleNo] = imageUrl;
//       }, error => {
//         console.error(error);
//       });
//     });
//   }, error => {
//     console.error(error);
//     hasError.value = true;
//   });
// };

watch(() => route.query.keyword, (newKeyword) => {
  if (newKeyword) {
    loadPlans({ key: 'subject', word: newKeyword });
  } else {
    loadPlans(param.value);
  }
}, { immediate: true });

// const moveWrite = () => {
//   router.push({ name: 'plan-write' });
// };

</script>

<template>
  <v-app>
    <v-main>
      <v-container>
        <PlanHeaderItem style="margin-top: 75px; margin-bottom: 20px; text-align: center;" />
        <v-row justify="center">
          <v-col cols="12" class="text-right">
            <!-- Uncomment the button below if you want to use it -->
            <v-btn class="main-button" @click="moveWrite" color="#072a40">게시글 작성하기</v-btn>
          </v-col>
          <v-col cols="12">
            <v-card class="common-card">
              <template v-if="plans.length > 0">
                <v-row>
                  <v-col v-for="plan in plans" :key="plan.planId" class="py-2">
                    <PlanListItem :plan="plan" />
                  </v-col>
                </v-row>
              </template>
              <template v-else>
                <PlanListEmptyItem />
              </template>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'YourComponentName',
  data() {
    return {
      plans: [] // Replace with your actual data source
    };
  },
  methods: {
    moveWrite() {
      // Implement your moveWrite function here
    }
  }
};
</script>

<style scoped>
.common-card {
  display: flex;
  flex-wrap: wrap; 
  justify-content: flex-start; 
  align-items: flex-start; 
  gap: 16px; 
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}
.main-button {
  background-color: #072a40;
  color: white;
}
</style>

