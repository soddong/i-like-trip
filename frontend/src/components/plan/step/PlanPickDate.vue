<script setup>
import { usePlanStore } from "@/stores/plan"
import { storeToRefs } from "pinia"

const planStore = usePlanStore()
const { getStartEnd } = planStore
const { period } = storeToRefs(planStore)

</script>

<template>
    <v-container min-width="400">
        <v-row justify="center">
            <v-col>
                출발 <br>
                {{ getStartEnd().start }}
            </v-col>
            ~
            <v-col>
                도착 <br>
                {{ getStartEnd().end }}
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-date-picker show-adjacent-months multiple="range" v-model="period" />
        </v-row>
    </v-container>

</template>

<style scoped></style>