<script setup>
import { onMounted, ref, watch } from 'vue';
import { mdiMagnify } from '@mdi/js';
import { getAttraction } from '@/api/plan';
import { GridStack } from 'gridstack';


const searchFilter = ref({
    keyword: "",
    sido: null,
    gugun: null,
    contentType: ""
})
const loaded = ref(false)
const loading = ref(false)
const attractionList = ref([])

let grid = null;
function onClick() {
    loading.value = true
    getAttraction({}, (res) => {
        attractionList.value = res.data
        loading.value = false;
        loaded.value = true;
        grid.batchUpdate(true)
        attractionList.value.forEach(element => {
            grid.addWidget(`
    <div class="v-container v-locale--is-ltr pa-0 grid-stack-item ui-resizable-disabled" gs-x="0" gs-y="0">
        <div class="v-row ma-0 grid-stack-item-content border">
            <div class="v-col v-col-4"><img class="rounded"
                    src="${element.imgSmall ? element.imgSmall : '/src/assets/logo2.png'}" alt=""
                    style="height: 100%; width: 70px; object-fit: cover;"></div>
            <div class="v-col v-col-8 align-self-center">
                <div class="v-row" style="font-size: small;">${element.title}</div>
                <div class="v-row py-1" style="font-size: x-small;">${element.attractionType}</div>
                <div class="v-row text-truncate" style="font-size: x-small;">${element.addr}</div>
            </div>
        </div>
    </div>
    
        `)
        });
        grid.batchUpdate(false)
    }, (e) => {
        loading.value = false;
        console.log(e)
    })
}
onMounted(() => {
    grid = GridStack.init({
        cellHeight: "100px",
        column: 1,
        disableResize: true,
        margin: 0
    }, document.getElementById('grid1'));
})

</script>

<template>
    <v-sheet class="border fill-height d-flex flex-column">
        <v-sheet class="border">
            <v-col>
                <v-text-field density="compact" variant="solo" placeholder="어디로 가시나요?" single-line clearable
                    :append-inner-icon="mdiMagnify" @click:append-inner="onClick" :loading="loading">
                </v-text-field>
                <v-row class="overflow-auto">
                    <v-chip v-for="category in placeCategory" :key="category.value" variant="tonal" size="small"
                        @click="">
                        {{ category.title }}
                    </v-chip>
                </v-row>
                <v-row>
                    <v-chip variant="tonal" size="small" v-if="searchFilter.sido" closable
                        @click:close="resetAddrFilter()">
                        {{ searchFilter.sido.title }}
                    </v-chip>
                    <v-chip variant="tonal" size="small" v-if="searchFilter.gugun" closable
                        @click:close="resetAddrFilter('gugun')">
                        {{ searchFilter.gugun.title }}
                    </v-chip>
                </v-row>

            </v-col>
        </v-sheet>
        <!-- <v-sheet class="grid-stack border fill-height overflow-auto" id="grid1">
            <v-container class="pa-0 grid-stack-item">
                <v-row class="ma-0 grid-stack-item-content border">
                    <v-col cols="4">
                        <img style="height: 100%; width: 70px; object-fit: cover" class="rounded"
                            src="http://tong.visitkorea.or.kr/cms/resource/62/219162_image3_1.jpg" alt="">
                    </v-col>
                    <v-col cols="8" align-self="center">
                        <v-row class="pb-1" style="font-size: small;">비슬산자연휴양림</v-row>

                        <v-row style="font-size: x-small;">관광지</v-row>
                        <v-row class="text-truncate pb-1" style="font-size: x-small;">대구광역시 달성군 유가읍 일연선사길 61</v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-sheet> -->
    </v-sheet>
</template>

<style scoped>
/* 스크롤바 설정*/
#grid1::-webkit-scrollbar {
    width: 7px;
}

/* 스크롤바 막대 설정*/
#grid1::-webkit-scrollbar-thumb {
    background-color: rgb(83, 83, 83);
    /* 스크롤바 둥글게 설정 */
    border-radius: 5px;
}

/* 스크롤바 뒷 배경 설정*/
#grid1::-webkit-scrollbar-track {
    background-color: rgba(0, 0, 0, 0);
}
</style>