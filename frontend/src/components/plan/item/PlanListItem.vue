<template>
  <v-card class="mx-auto my-5" max-width="400" min-width="300">
    <router-link :to="{ name: 'plan-view', params: { planno: plan.planId } }" class="no-border">
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
    </router-link>
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

const count = {
  chunchun: 3,
  gyungsan: 1,
  incheon: 1,
  jeju: 8,
  pusan: 7,
  default: 7
};

const getRandomImagePath = (path, imageCount) => {
  console.log(imageCount)
  const randomIndex = Math.floor(Math.random() * imageCount) + 1; // Assuming you have 10 images in each directory
  return `${path}/${randomIndex.toString().padStart(2, '0')}.jpg`;
};

const imagePath = computed(() => {
  if (props.plan.title.includes('제주')) {
    return getRandomImagePath('src/assets/random-images/jeju', count.jeju);
  } else if (props.plan.title.includes('부산')) {
    return getRandomImagePath('src/assets/random-images/pusan', count.pusan);
  } else if (props.plan.title.includes('인천')) {
    return getRandomImagePath('src/assets/random-images/incheon', count.incheon);
  } else if (props.plan.title.includes('경산')) {
    return getRandomImagePath('src/assets/random-images/gyungsan', count.gyungsan);
  } else if (props.plan.title.includes('춘천')) {
    return getRandomImagePath('src/assets/random-images/chunchun', count.chunchun);
  } else {
    return getRandomImagePath('src/assets/random-images', count.default);
  }
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
  font-size: 12px;
  align-self: flex-end;
}

.v-card-subtitle {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  /* Ellipsis for overflow text */
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

.no-border {
  text-decoration: none;
}
</style>
