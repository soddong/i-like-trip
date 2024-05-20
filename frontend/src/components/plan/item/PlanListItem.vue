<template>
  <v-card class="mx-auto my-5" max-width="400">
    <v-img src="/assets/random-images/01.jpg" height="300px">
      <div class="overlay"></div>
      <v-card-title class="fill-height align-end justify-space-between pa-3">
        <div>
          <h2 class="headline white--text">{{ plan.title }}</h2>
          <span class="subheading white--text">{{ plan.registDate.split(' ')[0] }}</span>
        </div>
        <span class="white--text">From. {{ plan.makerId }}</span>
      </v-card-title>
    </v-img>
    <v-card-subtitle class="pt-4 text-left white--text" style="background-color: rgba(0, 0, 0, 0.5); padding: 10px;">
      <div v-html="plan.comment"></div>
    </v-card-subtitle>
  </v-card>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  plan: {
    type: Object,
    required: true,
  },
});
</script>

<style scoped>
.headline {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 5px;
}

.subheading {
  font-size: 16px;
}

.white--text {
  color: white;
}

.v-card-subtitle {
  white-space: pre-wrap;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1;
}

.v-card-title {
  position: relative;
  z-index: 2;
}
</style>
