<template>
  <div class="background">
    <div>
      <label>Filter by Target Area:</label>
      <select v-model="selectedFilter">
        <option v-for="filter in filters" v-bind:key="filter">{{ filter }}</option>
      </select>
    </div>
    <div class="card-container">
      <div class="cards" v-for="exercise in filteredExercises" v-bind:key="exercise.id">
        <h2>{{ exercise.exercise_name }}</h2>
        <p>{{ exercise.exercise_description }}</p>
        <div class="exercise-details">
          <p><strong>Duration in minutes:</strong> {{ exercise.exercise_duration }}</p>
          <p><strong>Target Area:</strong> {{ exercise.target_area }}</p>
          <p><strong>Suggested Weight in Lbs:</strong> {{ exercise.suggested_weight }}</p>
          <p><strong>Number of Reps:</strong> {{ exercise.num_of_reps }}</p>
          <button v-on:click="add(exercise)">Add To Workout</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ExerciseService from "../services/ExerciseService.js";
import WorkoutUserDataService from "../services/WorkoutUserDataService.js";
export default {
  created() {
    ExerciseService.listExercises().then((response) => {
      this.exercises = response.data;
      this.filters = [...new Set(this.exercises.map((exercise) => exercise.target_area))];
      this.selectedFilter = this.filters[0];
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
      selectedFilter: "",
    };
  },

  computed: {
    filteredExercises() {
      if (this.selectedFilter === "") {
        return this.exercises;
      } else {
        return this.exercises.filter((exercise) => exercise.target_area === this.selectedFilter);
      }
    },
  },

  methods: {

    add(exercise) {
      WorkoutUserDataService.addNewWorkoutData(exercise).then (
        () => {
          alert("Exercise Added")
        }
      )
      
    }
  },
};
</script>

<style>
.background {
  background-image: url('https://i.imgur.com/oy6FeMt.png');
  background-size: cover;
  background-position: center center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
  height: 100vh;
  width: 100vw;
}

.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.cards {
  display: flex;
  flex-direction: column;
  width: calc(20% - 10px);
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