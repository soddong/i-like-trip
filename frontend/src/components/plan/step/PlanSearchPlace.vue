<script setup>
import { ref } from 'vue';
import { mdiMagnify } from '@mdi/js';

const searchCondition = ref({
    keyword: ""
})

const loaded = ref(false)
const loading = ref(false)

function onClick() {
    loading.value = true

    setTimeout(() => {
        loading.value = false
        loaded.value = true
    }, 2000)
}

const items = ref([
    { value: 1, title: "서울" },
    { value: 2, title: "인천" },
    { value: 3, title: "대전" },
    { value: 4, title: "대구" },
    { value: 5, title: "광주" },
    { value: 6, title: "부산" },
    { value: 7, title: "울산" },
    { value: 8, title: "세종특별자치시" },
    { value: 31, title: "경기도" },
    { value: 32, title: "강원도" },
    { value: 33, title: "충청북도" },
    { value: 34, title: "충청남도" },
    { value: 35, title: "경상북도" },
    { value: 36, title: "경상남도" },
    { value: 37, title: "전라북도" },
    { value: 38, title: "전라남도" },
    { value: 39, title: "제주도" }
])

const placeCategory = ref([
    { value: 12, title: "관광지" },
    { value: 14, title: "문화시설" },
    { value: 15, title: "축제공연행사" },
    { value: 25, title: "여행코스" },
    { value: 28, title: "레포츠" },
    { value: 32, title: "숙박" },
    { value: 38, title: "쇼핑" },
    { value: 39, title: "음식점" },

])

function handleItemClick(item, index, temp) {
    console.log(temp)
    console.log(`Item clicked:`, item, `at index`, index);
}

</script>

<template>
    <v-container class="border">
        <v-row class="border">
            <v-col>
                <v-autocomplete hide-no-data v-model="searchCondition.keyword" menu-icon="" density="compact"
                    variant="solo" placeholder="어디로 가시나요?" single-line clearable :append-inner-icon="mdiMagnify"
                    @click:append-inner="onClick" :loading="loading" auto-select-first :items="items" multiple>
                    <template #item="{ item, index, props }">
                        <v-list-item v-bind="props" @click="handleItemClick(item, index, props)">

                        </v-list-item>
                    </template>
                    <template #selection="{ item, index }">
                    </template>
                </v-autocomplete>
                <v-row>
                    <v-chip v-for="category in placeCategory" :key="category.value" variant="tonal" size="small" @click="">
                        {{ category.title }}
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