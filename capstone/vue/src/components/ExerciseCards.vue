<template>
  <div>
    <div class="cards" v-for = "exercise in exercises" v-bind:key="exercise.exercise_id">
      
      <h2> {{exercise.exercise_name}} </h2>
      <p> {{exercise.exercise_description}}</p>
      <p>{{exercise.exercise_duration}}</p>
      <p>{{exercise.target_area}}</p>
      <p>{{exercise.suggested_weight}}</p>
      <p>{{exercise.num_of_reps}}</p>
     

      

    </div>
      </div>
  
</template>

<script>

import ExerciseService from '../services/ExerciseService.js'

export default {
created() {
    ExerciseService.listExercises().then (
      response=> {
        this.exercises=response.data
      }
    )
  },

  data() {
    return {
      exercise: {
        exercise_id: 0,
        exercise_name: "",
        exercise_description: "",
        exercise_duration: 0,
        target_area: "",
        suggested_weight: 0,
        num_of_reps: 0
      },
      exercises: [],
      filters:[],

    };
  },
  methods: {
    
  }

}
</script>

<style>

.cards {
    display: inline-block;
    width: 25%;
    border: solid 3px white;
    padding: 10px;
    margin: 5px;
    border-radius: 15px;;
}

</style>