<template>
  <div>
    <div
      class="userData"
      v-for="workoutuserdata in workoutuserdataservices"
      v-bind:key="workoutuserdata.workout_user_data_id"
    ></div>
  </div>
</template>

<script>
import WorkoutUserDataService from "../services/WorkoutUserDataService.js";


export default {
  created() {
    WorkoutUserDataService.allUserWorkoutData().then (
        response => {
      this.workoutuserdataservices = response.data
    })
  },
    
  data() {
    return {
      workoutuserdata: {
        workout_user_data_id: 0,
        user_id: 0,
        exercise_id: 0,
        workout_date: "",
        abs_duration: 0,
        legs_duration: 0,
        back_duration: 0,
        cardio_duration: 0,
        arms_duration: 0,
      },
      workoutuserdataservices: [],
      filters: [],
    };
  },
};
</script>

<style>
</style>