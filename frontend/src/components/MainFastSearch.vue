<script setup>
import { mdiMagnify } from '@mdi/js';
import VSearchInput from '@/components/common/VSearchInput.vue'; 
</script>

<template>
    <v-container fluid class="head">
        <v-row justify="center">
            <div class="title d-flex flex-column align-center justify-center">
                <p class="title">다른사람은 어떻게 계획할까요?</p>
                <br>
            </div>
        </v-row>
        <v-row justify="center">
        <VSearchInput></VSearchInput>
        </v-row>
        <!-- <v-row><v-col><v-sheet  class="bg-surface-variant">검색 결과

        </v-sheet></v-col></v-row> -->
    </v-container>
</template>

<style scoped>
.head {
    background-color: #f9f7f0;
    padding-top: 200px; 
    height: 500px;
}
.title {
    font-size: 45px;
}


</style>