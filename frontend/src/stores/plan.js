import { ref } from "vue";
import { defineStore } from "pinia";

export const usePlanStore = defineStore("planStore", () => {
  const period = ref([new Date(new Date().toDateString())]);
  const pickedPlace = ref([]);

  const getStartEnd = () => {
    let start = period.value[0].toDateString();
    let end = period.value[period.value.length - 1].toDateString();
    return { start, end };
  };

  const getPeriodTime = () => {
    if (period.value.length < 2) {
      return 1;
    } else {
      return Math.round(
        (period.value[period.value.length - 1] - period.value[0] + 1) / 60000 / 60 / 24
      );
    }
  };

  return {
    period,
    pickedPlace,
    getStartEnd,
    getPeriodTime,
  };
});
