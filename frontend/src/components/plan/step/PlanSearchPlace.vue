<script setup>
import { onMounted, ref, watch } from 'vue';
import { mdiMagnify } from '@mdi/js';
import { getAttraction } from '@/api/plan';
import { es } from 'vuetify/lib/locale/index.mjs';


const searchFilter = ref({
    keyword: "",
    sido: null,
    gugun: null,
    contentType: ""
})

const loaded = ref(false)
const loading = ref(false)
const attractionList = ref([])
function onClick() {
    loading.value = true
    getAttraction({},(res)=>{
        attractionList.value=res.data
        loading.value=false;
        loaded.value=true;
    },(e)=>{
        loading.value=false;
        console.log(e)
    })
}
onMounted(() => {
})

</script>

<template>
    <v-container class="border">
        <v-row class="border">
            <v-col>
                <v-text-field density="compact"
                    variant="solo" placeholder="어디로 가시나요?" single-line clearable :append-inner-icon="mdiMagnify"
                    @click:append-inner="onClick" :loading="loading"
                    >
                </v-text-field>
                <v-row class="overflow-auto" >
                    <v-chip v-for="category in placeCategory" :key="category.value" variant="tonal" size="small"
                        @click="">
                        {{ category.title }}
                    </v-chip>
                </v-row>
                <v-row>
                    <v-chip variant="tonal" size="small" v-if="searchFilter.sido" closable @click:close="resetAddrFilter()">
                        {{ searchFilter.sido.title }}
                    </v-chip>
                    <v-chip variant="tonal" size="small" v-if="searchFilter.gugun" closable @click:close="resetAddrFilter('gugun')">
                        {{ searchFilter.gugun.title }}
                    </v-chip>
                </v-row>

            </v-col>
        </v-row>
        <v-row class="border">
            <v-col>
                검색 결과
            </v-col>
        </v-row>
    </v-container>
</template>

<style scoped></style>