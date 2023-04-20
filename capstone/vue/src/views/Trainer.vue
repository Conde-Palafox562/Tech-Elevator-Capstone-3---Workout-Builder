<template>
  <div>
      <trainer-admin />
      <exercise-cards />
      <create-exercise />

  </div>
</template>

<script>
import ExerciseCards from '../components/ExerciseCards.vue'
import CreateExercise from '../components/CreateExercise.vue'
import TrainerAdmin from '../components/TrainerAdmin.vue'
export default {
  components: { TrainerAdmin, CreateExercise, ExerciseCards },
    name: "Trainer"
}
</script>

<style>
</style>
