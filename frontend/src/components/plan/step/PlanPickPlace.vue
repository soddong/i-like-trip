<script setup>
import { onMounted, nextTick } from 'vue';
import PlanCheckDate from "./item/PlanCheckDate.vue";
import { GridStack } from 'gridstack';
import { usePlanStore } from '@/stores/plan';
import { storeToRefs } from "pinia";
import { attrTypes } from '@/util/attraction-type';

const props = defineProps({
    mapMove: Function,
    attrList: Array,
    makePathPolyline: Function
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
        margin: "15 0 0 10",
        resizable: { handles: 's' },
        removable: true
    }, document.getElementById('grid2'));

    grid.on('dropped', function (event, previousWidget, newWidget) {
        let newId = "w_" + crypto.randomUUID() + "_p"
        let attrId = newWidget.id.substring(2)
        let attr = { ...(props.attrList.find((element) => attrId == element.attractionId)) }
        attr.x = newWidget.x
        attr.y = newWidget.y
        attr.h = newWidget.h
        attr.w = newWidget.w
        attr.id = newId
        pickedPlace.value.push(attr)
        grid.removeWidget(newWidget.id)
        nextTick(() => {
            grid.makeWidget(newId);
            props.makePathPolyline();
        });
    });

    grid.on('removed', function (event, items) {
        if (items[0].id.endsWith("p")) {
            let id = items[0].id
            let idx = pickedPlace.value.findIndex((e) => id === e.id)
            pickedPlace.value.splice(idx, 1)
            props.makePathPolyline();
        }
    });

    grid.on('change', function (event, items) {
        items.forEach((item) => {
            let idx = pickedPlace.value.findIndex((e) => item.id === e.id)
            pickedPlace.value[idx].y = item.y
            pickedPlace.value[idx].h = item.h
            planStore.sortPickedPlace()
            props.makePathPolyline();
        });
    });
});

</script>

<template>
    <v-container fluid class="border fill-height d-flex">
        <PlanCheckDate />
        <div>
            <!-- <v-btn @click="makePathPolyline">btn</v-btn> -->
        </div>
        <v-sheet class="border overflow-y-auto overflow-x-hidden position-relative" id="grid2-wrap"
            style="height: 800px; width: 100%;">
            <div id="time-line" :style="{ height: planStore.getPeriodTime() * 24 * 100 + 'px' }">
                <div class="time-line-day" v-for="day in planStore.getPeriodTime()" :key="day">
                    <div style="width: 10px; background-color: #e4e4e4">{{ day }}일차</div>
                    <div class="time-line-hour">
                        <div v-for="(item, index) in 24" :key="index">
                            <p>{{ item - 1 < 10 ? "0" + (item - 1) : item - 1 }}:00</p>
                        </div>
                    </div>
                </div>
            </div>
            <v-sheet class="grid-stack position-absolute" id="grid2">
                <v-container class="pa-0 grid-stack-item" v-for="place in pickedPlace" :key="place.id" :gs-x="place.x"
                    :gs-y="place.y" :gs-w="place.w" :gs-h="place.h" :gs-id="place.id" :id="place.id">
                    <v-row class="ma-0 grid-stack-item-content border">
                        <v-col cols="4" style="height: 100%;">
                            <v-img cover rounded style="height: 100%; width: 100%;"
                                :src="place.imgSmall ? place.imgSmall : '/src/assets/logo2.png'">
                            </v-img>
                        </v-col>
                        <v-col cols="8">
                            <v-sheet class="d-flex flex-column h-100 justify-space-between">
                                <div class="pb-1 text-truncate " style="font-size: small;">
                                    <v-chip size="x-small" :prepend-icon="attrTypes[place.attractionType].icon">{{
                                        attrTypes[place.attractionType].title }}
                                    </v-chip>
                                    {{ place.title }}
                                </div>
                                <div @click="mapMove(place.lat, place.lng)" class="text-truncate pb-1"
                                    style="font-size: x-small;">{{ place.addr }}</div>
                                <div style="font-size: x-small;">
                                    <input type="text" name="" id="">
                                </div>
                            </v-sheet>
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