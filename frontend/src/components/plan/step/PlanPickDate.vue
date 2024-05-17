<script setup>
import { ref } from 'vue';

const value = ref([new Date()])

</script>

<template>
    <v-sheet>
        <v-row  >
            <v-col>
                출발 <br>
                {{ value[0].toDateString() }}
            </v-col>
            ~
            <v-col>
                도착 <br>
                {{ value.length>1?value[value.length-1].toDateString():"" }}
            </v-col>
        </v-row>
    </v-sheet>
    <v-date-picker show-adjacent-months multiple="range" v-model="value"></v-date-picker>

</template>

<style scoped></style>