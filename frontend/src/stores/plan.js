import { ref } from "vue";
import { defineStore } from "pinia";

export const usePlanStore = defineStore("planStore", () => {
  const period = ref([new Date(new Date().toDateString())]);
  const pickedPlace = ref([]);
  const planInfo = ref([]);
  const content = ref([]);

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
    let start = new Date(period.value[0].getTime() + 1000 * 60 * 60 * pickedPlace.value[idx].y);
    let end = new Date(start.getTime() + 1000 * 60 * 60 * pickedPlace.value[idx].h - 1);
    return { start, end };
  };

  const sortPickedPlace = () => {
    pickedPlace.value.sort((a, b) => a.y - b.y);
  };

  const resetPlan = () => {
    period.value = [new Date(new Date().toDateString())];
    pickedPlace.value = [];
  };

  const isEmpty = () => {
    return pickedPlace.value.length > 0 || period.value[0] != null || period.value[1] != null
      ? false
      : true;
  };

  const updatePickedPlace = (places, baseTime) => {
    console.log(baseTime);
    console.log(places);
    pickedPlace.value = places.map((place) => {
      let placeStartTime = new Date(place.startTime);
      let placeEndTime = new Date(place.endTime);
      return {
        attractionId: place.place.attractionId,
        attractionType: place.place.attractionType,
        title: place.place.title,
        addr: place.place.addr,
        zipcode: place.place.zipcode,
        tel: place.place.tel,
        imgSmall: place.place.imgSmall,
        imgBig: place.place.imgBig,
        lat: place.place.lat,
        lng: place.place.lng,
        id: "w_" + crypto.randomUUID() + "_p",
        x: 0,
        y: Math.round((placeStartTime - baseTime) / 3600000),
        h: Math.round((placeEndTime - placeStartTime) / 3600000),
        w: 1,
      };
    });
  };

  const updatePlanInfo = (plan) => {
    let endTime = new Date(plan.endTime);
    let iterTime = new Date(plan.startTime);
    let newPeriod = [];
    while (iterTime < endTime) {
      newPeriod.push(new Date(iterTime));
      iterTime.setTime(iterTime.getTime() + 3600000 * 24);
    }
    newPeriod.push(new Date(iterTime - 1));
    period.value = newPeriod;
    planInfo.value = plan;
  };

  const getPlacePerDay = () => {
    let allTime = getPeriodTime();
    let placePerDay = [];
    for (let index = 0; index < allTime; index++) {
      placePerDay.push([]);
    }

    let curDayCount = 0;
    pickedPlace.value.forEach((e, idx) => {
      let { start, end } = getPlaceStartEnd(idx);
      const timeDifference =
        new Date(`${end.getFullYear()}-${end.getMonth()}-${end.getDate()}`) -
        new Date(`${start.getFullYear()}-${start.getMonth()}-${start.getDate()}`);
      const millisecondsPerDay = 24 * 60 * 60 * 1000;
      let containDayCount = Math.ceil(timeDifference / millisecondsPerDay) + 1;
      if (containDayCount == 1) {
        placePerDay[curDayCount].push({ ...e, start, end });
      } else {
        for (let i = 0; i < containDayCount; i++) {
          if (i != 0) curDayCount++;
          if (i === 0) {
            placePerDay[curDayCount].push({ ...e, start });
          } else if (i === containDayCount - 1) {
            placePerDay[curDayCount].push({ ...e, end });
          } else {
            placePerDay[curDayCount].push({ ...e });
          }
        }
      }

      if (end.getHours() == 23) curDayCount++;
    });
    return placePerDay;
  };

  return {
    period,
    pickedPlace,
    content,
    planInfo,
    getStartEnd,
    getPeriodTime,
    getPlaceStartEnd,
    sortPickedPlace,
    resetPlan,
    isEmpty,
    updatePickedPlace,
    updatePlanInfo,
    getPlacePerDay,
  };
});
