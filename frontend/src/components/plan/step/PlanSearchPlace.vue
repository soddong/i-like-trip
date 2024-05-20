<script setup>
import { ref, nextTick } from 'vue';
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
const emit = defineEmits(['changeAttrList', 'openDetail'])
const props = defineProps({
    mapMove: Function,
    attrList: Array
})

function onClick() {
    loading.value = true
    getAttraction({}, (res) => {

        loading.value = false;
        loaded.value = true;

        res.data.map((element) => {
            element.id = "w_" + element.attractionId
        });
        emit('changeAttrList', res.data)

        nextTick(() => {
            GridStack.setupDragIn('.grid-stack-item', {
                helper: "clone"
            });
        });


    }, (e) => {
        loading.value = false;
        console.log(e)
    })
}

</script>

<template>
    <v-sheet class="border fill-height d-flex flex-column">
        <v-sheet class="border">
            <v-col>
                <v-text-field density="compact" variant="solo" placeholder="어디로 가시나요?" single-line clearable
                    :append-inner-icon="mdiMagnify" @click:append-inner="onClick" :loading="loading">
                </v-text-field>
                <v-row class="overflow-auto">
                    <v-chip v-for="category in placeCategory" :key="category.value" variant="tonal" size="small">
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
        <v-sheet class="border fill-height overflow-auto" id="grid1">
            <v-container class="pa-0 grid-stack-item" v-for="w in attrList" :gs-id="w.id" :key="w.id" :id="w.id"
                @click="mapMove(w.lat, w.lng)" style="height: 100px;">
                <v-row class="ma-0 grid-stack-item-content border" style="height: 100%;">
                    <v-col cols="4">
                        <v-img cover rounded style="height: 100%; width: 70px;"
                            :src="w.imgSmall ? w.imgSmall : '/src/assets/logo2.png'">

                        </v-img>
                    </v-col>
                    <v-col cols="8" align-self="center">
                        <v-row class="pb-1" style="font-size: small;">{{ w.title }}</v-row>

                        <v-row style="font-size: x-small;">{{ w.attractionType }}</v-row>
                        <v-row class="text-truncate pb-1" style="font-size: x-small;">{{ w.addr }}</v-row>
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