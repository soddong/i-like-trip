<script setup>
import { usePlanStore } from "@/stores/plan"
import { mdiPlay } from '@mdi/js';

const planStore = usePlanStore()
const { getStartEnd } = planStore
</script>

<template>
    <v-row justify="center">
        <v-col class="text-center ">
            <p style="font-size: x-large;">출발</p>
            <p style="font-size: small;">
                {{ getStartEnd().start }}
            </p>
        </v-col>
        <div class="d-flex align-center" >
            <v-icon :icon="mdiPlay"></v-icon>
        </div>
        
        <v-col class="text-center">
            <p style="font-size: x-large;">도착</p>
            <p style="font-size: small;">
                {{ getStartEnd().end }}
            </p>
        </v-col>
    </v-row>
</template>

<style scoped></style>