<script setup>
import { ref, nextTick, onMounted } from 'vue';
import { mdiMagnify } from '@mdi/js';
import { getAttraction } from '@/api/plan';
import { GridStack } from 'gridstack';
import { attrTypes } from '@/util/attraction-type';

const searchFilter = ref({
    keyword: null,
    sidoCode: null,
    gugunCode: null,
    contentType: null
})
const loaded = ref(false)
const loading = ref(false)
const emit = defineEmits(['changeAttrList', 'openDetail'])
const props = defineProps({
    mapMove: Function,
    attrList: Array
})
const searchHistory = ref([])

function search() {
    loading.value = true
    getAttraction(searchFilter.value, (res) => {
        loading.value = false;
        loaded.value = true;

        res.data.map((element) => {
            element.id = "w_" + element.attractionId
        });
        emit('changeAttrList', res.data)
        if (searchHistory.value.length >= 5) {
            searchHistory.value.pop()
        }
        searchHistory.value = [searchFilter.value.keyword, ...searchHistory.value]

        nextTick(() => {
            GridStack.setupDragIn('.grid-stack-item', {
                helper: (e) => {
                    let element = e.target
                    while (!element.classList.contains("grid-stack-item")) {
                        element = element.parentElement
                    }
                    emit('openDetail')
                    let clone = element.cloneNode(true)
                    return clone;
                }
            });
        });


    }, (e) => {
        loading.value = false;
        console.log(e)
    })
}

onMounted(() => {
    search()
})
</script>

<template>
    <v-sheet class="border fill-height d-flex flex-column">
        <v-sheet style="height: 150px;">
            <v-col>
                <v-text-field class="mb-2" density="compact" variant="solo" placeholder="어디로 가시나요?" single-line
                    clearable v-model="searchFilter.keyword" :append-inner-icon="mdiMagnify"
                    @click:append-inner="search" @keyup.enter="search" :loading="loading" hide-details>
                </v-text-field>
                <v-chip-group column multiple v-model="searchFilter.contentType">
                    <v-chip class="ma-1" v-for="(type, idx, key) in attrTypes" :key="idx" variant="tonal" size="x-small"
                        :prepend-icon="type.icon" :value="idx">
                        {{ type.title }}
                    </v-chip>
                </v-chip-group>
                <!-- <v-sheet>
                    <v-chip variant="tonal" size="x-small" v-for="(his, index) in searchHistory" :key="his"
                        @click="">
                        {{ his }}
                    </v-chip>
                </v-sheet> -->

            </v-col>
        </v-sheet>
        <v-sheet class="fill-height overflow-auto" id="grid1">
            <v-container class="pa-0 grid-stack-item" v-for="w in attrList" :gs-id="w.id" :key="w.id" :id="w.id"
                @click="mapMove(w.lat, w.lng)" style="height: 100px;">
                <v-row class="ma-0 grid-stack-item-content " style="height: 100%;">
                    <v-col cols="4" style="height: 100%;">
                        <v-img cover rounded style="height: 100%; width: 100%;"
                            :src="w.imgSmall ? w.imgSmall : '/src/assets/logo2.png'">
                        </v-img>
                    </v-col>
                    <v-col cols="8" >
                        <v-sheet class="d-flex flex-column h-100 justify-space-evenly">
                            <div class="text-truncate pb-1" style="font-size: small;">{{ w.title }}</div>
                            <div class="text-truncate pb-1" style="font-size: x-small;">{{ w.addr }}</div>
                            <div style="font-size: x-small;">
                                <v-chip size="x-small" :prepend-icon="attrTypes[w.attractionType].icon">{{
                                    attrTypes[w.attractionType].title }}</v-chip>
                            </div>
                        </v-sheet>
                    </v-col>
                </v-row>
            </v-container>
        </v-sheet>
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