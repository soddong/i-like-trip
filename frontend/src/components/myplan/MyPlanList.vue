<script setup>
import PlanListItem from "@/components/plan/item/PlanListItem.vue"
import { useRoute } from "vue-router";
import { ref, watch } from "vue";
import { listPlan } from '@/api/plan.js';
import PlanListEmptyItem from "@/components/plan/item/PlanListEmptyItem.vue"
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
        loadPlans({ key: 'subject', word: newKeyword });
    } else {
        loadPlans(param.value);
    }
}, { immediate: true });

</script>

<template>
    <v-main>
        <v-container class="mt-10">
            <div class="sub-seperate">
                예정된 일정
            </div>
            <v-row>
                <template v-if="plans.length > 0">
                    <template v-for="plan in plans" :key="plan.planId">
                        <v-col>
                            <PlanListItem :plan="plan" />
                        </v-col>
                    </template>
                </template>
                <template v-else>
                    <PlanListEmptyItem />
                </template>
            </v-row>
            <div class="sub-seperate">
                지난 일정
            </div>
        </v-container>
    </v-main>
</template>

<style scoped>
.sub-seperate {
    border-bottom: 1px solid #20435C;
    font-size: x-large
}
</style>