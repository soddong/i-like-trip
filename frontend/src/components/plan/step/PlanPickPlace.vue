<script setup>
import { onMounted, nextTick } from 'vue';
import PlanCheckDate from "./item/PlanCheckDate.vue";
import { GridStack } from 'gridstack';
import { usePlanStore } from '@/stores/plan';
import { storeToRefs } from "pinia";

const props = defineProps({
    attrList: Array
})
const planStore = usePlanStore()
const { pickedPlace } = storeToRefs(planStore)
let grid = null;

onMounted(() => {
    let period = planStore.getPeriodTime()
    grid = GridStack.init({
        float: true,
        cellHeight: "100px",
        row: period * 24,
        column: 1,
        acceptWidgets: true,
        margin: "0 0 0 10",
        resizable: { handles: 's,n' },
        removable: true
    }, document.getElementById('grid2'));


    grid.on("added", (e, items) => {
        pickedPlace.value.push(items[0])
        let attrId = items[0].id.substring(2)
        console.log("추가됨", attrId)
        console.log(props.attrList.find((element) => attrId == element.attractionId))
        pickedPlace.value.push(props.attrList.find((element) => attrId == element.attractionId))
        // nextTick(() => {
        //     grid.batchUpdate(true)
        //     grid.makeWidget(element.id);
        //     grid.batchUpdate(false)
        // });
    })

    // grid.on('dropped', function (event, previousWidget, newWidget) {
    //     // console.log(event)
    //     // console.log("추가됨", previousWidget, newWidget)
    //     // grid.removeWidget(newWidget.id)
    // });
});

</script>

<template>
    <v-container fluid class="border fill-height d-flex">
        <PlanCheckDate />
        <div>

        </div>
        <v-sheet class="border overflow-y-auto overflow-x-hidden position-relative" id="grid2-wrap"
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
            <v-sheet class="grid-stack position-absolute" id="grid2">
                <v-container class="pa-0 grid-stack-item" :gs-x="0" :gs-y="0" :gs-w="1" :gs-h="1" gs-id="w_125406"
                    id="w_125406" key="w_125406" @click="mapMove(w.lat, w.lng)">
                    <v-row class="ma-0 grid-stack-item-content border">
                        <v-col cols="4">
                            <v-img cover rounded style="height: 100%; width: 70px;" :src="'/src/assets/logo2.png'">

                            </v-img>
                        </v-col>
                        <v-col cols="8" align-self="center">
                            <v-row class="pb-1" style="font-size: small;">제목</v-row>

                            <v-row style="font-size: x-small;">23</v-row>
                            <v-row class="text-truncate pb-1" style="font-size: x-small;">ㅈ주소</v-row>
                        </v-col>
                    </v-row>
                </v-container>
            </v-sheet>
        </v-sheet>

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
    background-color: rgba(2, 47, 87, 0);
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