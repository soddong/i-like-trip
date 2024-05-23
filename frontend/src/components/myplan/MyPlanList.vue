<script setup>
import PlanListItem from "@/components/plan/item/PlanListItem.vue"
import { useRoute } from "vue-router";
import { onMounted, ref, watch } from "vue";
import { listPlan } from '@/api/plan.js';
import PlanListEmptyItem from "@/components/plan/item/PlanListEmptyItem.vue"
const route = useRoute();
const plans = ref([[],[]]);
const param = ref({
    keyword: "",
    word: "",
});

const loadPlans = (params) => {
    listPlan(params, ({ data }) => {
        let plansss=[[],[]]
        data.forEach(plan => {
            const planObject = {
                title: plan.title,
                planId: plan.planId,
                makerId: plan.makerId,
                registDate: plan.registDate,
                visibility: plan.visibility,
                comment: plan.comment,
                img: `@/assets/random-images/0${plan.planId}.jpg`,
                startTime :plan.startTime,
                endTime :plan.endTime,
            };
            if(new Date(plan.endTime)<Date.now()){
                plansss[0].push(planObject)
            }else{
                plansss[1].push(planObject)
            }
           
        });
        plans.value= plansss
        console.log("성공!!!!!!!!!");
        console.log(plans.value);
    }, error => {
        console.error(error);
    });
};
onMounted(()=>{
    loadPlans({onlyMine: true });
})

</script>

<template>
    <v-main>
        <v-container style="margin-top: 110px">
            <div class="sub-seperate">
                예정된 일정
            </div>
            <v-row>
                <template v-if="plans[0].length > 0">
                    <template v-for="plan in plans[0]" :key="plan.planId">
                        <v-col>
                            <PlanListItem :plan="plan" />
                        </v-col>
                    </template>
                </template>
                <template v-else>
                </template>
            </v-row>
            <div class="sub-seperate">
                지난 일정
            </div>
            <v-row>
                <template v-if="plans[1].length > 0">
                    <template v-for="plan in plans[1]" :key="plan.planId">
                        <v-col>
                            <PlanListItem :plan="plan" />
                        </v-col>
                    </template>
                </template>
                <template v-else>
                </template>
            </v-row>
        </v-container>
    </v-main>
</template>

<style scoped>
.sub-seperate {
    border-bottom: 1px solid #20435C;
    font-size: x-large
}
</style>