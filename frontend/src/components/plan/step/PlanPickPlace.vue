<script setup>
import { ref, onMounted } from 'vue';
import PlanCheckDate from "./item/PlanCheckDate.vue";
import { GridStack } from 'gridstack';
import { usePlanStore } from '@/stores/plan';

const planStore = usePlanStore()
const info = ref("");
let grid = null;

onMounted(() => {
    let period = planStore.getPeriodTime()
    grid = GridStack.init({
        float: true,
        cellHeight: "100px",
        row: period * 48,
        column: 1,
        acceptWidgets: true,
        margin: 0
    }, document.getElementById('grid2'));

    grid.on("dragstop", function (event, element) {
        const node = element.gridstackNode;
        info.value = `you just dragged node #${node.id} to ${node.x},${node.y} – good job!`;
    });
});

</script>

<template>
    <v-container fluid class="border fill-height d-flex">
        <PlanCheckDate />
        <div class="border overflow-x-hidden" style="height: 700px; width: 100%;">
            <div class="grid-stack" id="grid2">
            </div>
        </div>

    </v-container>

</template>

<style>
@import "gridstack/dist/gridstack.css";
</style>