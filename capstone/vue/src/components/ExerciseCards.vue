<template>
  <div class="card-container">
    <div class="cards" v-for="exercise in exercises" :key="exercise.exercise_id">
      <h2>{{ exercise.exercise_name }}</h2>
      <p>{{ exercise.exercise_description }}</p>
      <div class="exercise-details">
        <p><strong>Duration:</strong> {{ exercise.exercise_duration }}</p>
        <p><strong>Target Area:</strong> {{ exercise.target_area }}</p>
        <p><strong>Suggested Weight:</strong> {{ exercise.suggested_weight }}</p>
        <p><strong>Number of Reps:</strong> {{ exercise.num_of_reps }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import ExerciseService from "../services/ExerciseService.js";

export default {
  created() {
    ExerciseService.listExercises().then((response) => {
      this.exercises = response.data;
    });
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
        num_of_reps: 0,
      },
      exercises: [],
      filters: [],
    };
  },
  methods: {},
};
</script>

<style>
.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.cards {
  display: flex;
  flex-direction: column;
  width: calc(33.33% - 10px);
  margin: 5px;
  border-radius: 15px;
  background-color: #f7f7f7;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  padding: 10px;
}

h2 {
  font-size: 1.5rem;
  margin: 0;
}

.exercise-details {
  margin-top: auto;
}
</style>
