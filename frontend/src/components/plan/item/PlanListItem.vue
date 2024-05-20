<template>
  <v-card class="mx-auto my-5" max-width="400">
    <v-img :src="imagePath" height="300px" cover>
      <div class="overlay"></div>
      <v-card-title class="fill-height align-end justify-space-between pa-3">
        <div class="d-flex flex-column justify-space-between fill-height">
          <div>
            <h2 class="headline white--text">{{ plan.title }}</h2>
            <span class="subheading white--text">{{ plan.registDate.split(' ')[0] }}</span>
          </div>
          <div class="comment-container">
            <div class="comment white--text" v-html="plan.comment"></div>
          </div>
        </div>
      </v-card-title>
    </v-img>
    <!-- <v-card-subtitle class="pt-4 text-right white--text" style="background-color: rgba(0, 0, 0, 0.5); padding: 10px;">
      <span class="maker-id white--text">{{ plan.makerId }}</span>
    </v-card-subtitle> -->
  </v-card>
</template>

<script setup>
import { defineProps, computed } from 'vue';
const props = defineProps({
  plan: {
    type: Object,
    required: true,
  },
});

const imagePath = computed(() => {
  return `src/assets/random-images/0${props.plan.planId}.jpg`;
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

.maker-id {
  font-size: 12px; /* Smaller font size for maker ID */
  align-self: flex-end;
}

.v-card-subtitle {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; /* Ellipsis for overflow text */
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: 1;
}

.v-card-title {
  position: relative;
  z-index: 2;
}

.comment-container {
  display: flex;
  justify-content: flex-end;
}

.comment {
  font-size: 18px;
}
</style>
