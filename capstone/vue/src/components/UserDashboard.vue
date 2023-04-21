<template>
  <div>
    <div
      class="userData"
      v-for="workoutuserdata in workoutuserdataservices"
      v-bind:key="workoutuserdata.workout_user_data_id"
    >
     <h2>
       Name:
      {{workoutuserdata.exercise_name}}
      </h2>
      <h2>
      Date:
      {{workoutuserdata.workout_date}}
      </h2>
      <h2>
      
      </h2>
    </div>
  </div>
</template>

<script>
import WorkoutUserDataService from "../services/WorkoutUserDataService.js";


export default {
  created() {
    WorkoutUserDataService.getWorkoutDataForUser().then (
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
        exercise_name: ""

      },
      workoutuserdataservices: [],
      filters: [],
    };
  },
};
</script>

<style>






</style>