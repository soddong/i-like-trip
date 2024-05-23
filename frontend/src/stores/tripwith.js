import { ref } from "vue";
import { defineStore } from "pinia";

export const useTripwithStore = defineStore("tripwithStore", () => {
  const tripwith = ref([]);

  const handleAddTripwith = (friend) => {
        console.log('Attempting to add friend:', friend);

        if (!Array.isArray(tripwith.value)) {
          console.log('tripwith.value is not an array, initializing with the new friend.');
          tripwith.value = [friend];
        } else {
          const exists = tripwith.value.some(f => f.id === friend.id);
          if (!exists) {
            tripwith.value = [...tripwith.value, friend];
            console.log('Friend added:', friend);
          } else {
            console.log('Friend not added, already exists with id:', friend.id);
          }
        }
      
        console.log('Current trip companions:', tripwith.value);
    };
      

  const handleRemoveTripwith = (id) => {
    tripwith.value = tripwith.value.filter(companion => companion.id !== id);
  };

  const resetTripwith = () => {
    tripwith.value = [];
  };

  const isEmpty = () => {
    return tripwith.value.length > 0 ? false : true;
  }

  const getTripwithSize = () => {
    return tripwith.value.length;
  }

  const updateTripwith = (companions) => {
    let newArr=[]
    companions.forEach(element => {
      newArr.push({
        id:element.id,
        name: element.name,
        email:element.email,
        profileNo : element.profileNo,
        profileImg : element.profileImg
      })
    });
    tripwith.value = newArr;
  };

  return {
    handleAddTripwith,
    handleRemoveTripwith,
    resetTripwith,
    isEmpty,
    getTripwithSize,
    updateTripwith,
    tripwith,
  };
});
