<script setup>
import { mdiMagnify } from '@mdi/js';
</script>

<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="8">
                <v-text-field density="compact" placeholder="어디로 여행을 떠나시나요?" :prepend-inner-icon="mdiMagnify"
                    variant="outlined"></v-text-field>
            </v-col>
        </v-row>
        <v-row><v-col><v-sheet  class="bg-surface-variant">검색 결과

        </v-sheet></v-col></v-row>
    </v-container>
</template>

<style scoped></style>