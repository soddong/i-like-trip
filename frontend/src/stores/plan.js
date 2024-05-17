import { ref } from "vue";
import { defineStore } from "pinia";

export const usePlanStore = defineStore("planStore", () => {
  const period = ref([new Date()]);

  const getStartEnd = () => {
    let start = period.value[0].toDateString();
    let end = period.value[period.value.length - 1].toDateString();
    return { start, end };
  };

  return {
    period,
    getStartEnd,
  };
});
