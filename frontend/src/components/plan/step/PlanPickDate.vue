<script setup>
import { usePlanStore } from "@/stores/plan"
import { storeToRefs } from "pinia"
import PlanCheckDate from "./item/PlanCheckDate.vue";

const planStore = usePlanStore()
const { period } = storeToRefs(planStore)

</script>

<template>
    <v-container fluid>
        <PlanCheckDate />
        <v-row justify="center">
            <v-date-picker show-adjacent-months multiple="range" v-model="period" />
        </v-row>
    </v-container>

</template>

<style scoped></style>