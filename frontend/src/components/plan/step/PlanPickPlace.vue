<script setup>
import { onMounted } from 'vue';
import PlanCheckDate from "./item/PlanCheckDate.vue";
import { GridStack } from 'gridstack';
import { usePlanStore } from '@/stores/plan';

const planStore = usePlanStore()
let grid = null;

onMounted(() => {
    let period = planStore.getPeriodTime()
    grid = GridStack.init({
        float: true,
        cellHeight: "100px",
        row: period * 24,
        column: 1,
        acceptWidgets: true,
        margin: "0 0 0 10"
    }, document.getElementById('grid2'));
});

</script>

<template>
    <v-container fluid class="border fill-height d-flex">
        <PlanCheckDate />
        <div>

        </div>
        <div class="border overflow-y-auto overflow-x-hidden position-relative" id="grid2-wrap"
            style="height: 800px; width: 100%;">
            <div id="time-line">
                <div class="time-line-day" v-for="day in planStore.getPeriodTime()" :key="day">
                    <div style="width: 10px; background-color: aqua">{{ day }}일차</div>
                    <div class="time-line-hour">
                        <div v-for="(item, index) in 24" :key="index">
                            <p>{{ item - 1 }}시</p>
                        </div>
                    </div>

                </div>

            </div>
            <div class="grid-stack position-absolute" id="grid2">
            </div>

        </div>

    </v-container>

</template>

<style>
@import "gridstack/dist/gridstack.css";

#memo {
    display: block
}

.grid-stack-item-content {
    background-color: white;
}

#time-line {
    height: 9600px;
    width: 100%;
    box-sizing: border-box;
}

#time-line div {
    /* border-top: 1px solid black; */
    box-sizing: border-box;
    width: 100%;
    text-align: center;
    font-size: x-small;
}

.time-line-day {
    display: flex;
    border-top: 1px gray solid
}

.time-line-hour div {
    height: 100px;
    border-top: 1px rgba(128, 128, 128, 0.397) solid
}

#grid2 {
    width: 100%;
    top: 0px;
}

/* 스크롤바 설정*/
#grid2-wrap::-webkit-scrollbar {
    width: 7px;
}

/* 스크롤바 막대 설정*/
#grid2-wrap::-webkit-scrollbar-thumb {
    background-color: rgb(83, 83, 83);
    /* 스크롤바 둥글게 설정 */
    border-radius: 5px;
}

/* 스크롤바 뒷 배경 설정*/
#grid2-wrap::-webkit-scrollbar-track {
    background-color: rgba(0, 0, 0, 0);
}
</style>