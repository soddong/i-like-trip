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

  const getPlaceStartEnd = (idx) => {
    pickedPlace.value[idx]
    let start = new Date(period.value[0].getTime()+(1000*60*60*pickedPlace.value[idx].y))
    let end = new Date(start.getTime()+(1000*60*60*pickedPlace.value[idx].h)-1)
    return {start, end}
  };

  return {
    period,
    pickedPlace,
    getStartEnd,
    getPeriodTime,
    getPlaceStartEnd
  };
});
