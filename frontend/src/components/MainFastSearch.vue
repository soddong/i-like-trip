<script setup>
import VSearchInput from '@/components/common/VSearchInput.vue'
import VSearchInputPlan from '@/components/common/VSearchInputPlan.vue'
// VSearchInputPlan.vue
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
            <VSearchInputPlan></VSearchInputPlan>
        </v-row>
    </v-container>
</template>

<style scoped>
.head {
    padding-top: 200px;
    height: 800px;
}

.title {
    font-size: 45px;
}
</style>