<template>
  <div class="main">
    <template>
      <div>
        <v-container fluid grid-list-md>
          <v-layout row wrap>
            <v-flex class="ml-8" style="  max-width: 320px; 
  min-width: 320px;" xs12 sm6 md4 v-for="exercise in filteredExercises"
      v-bind:exercise="exercise"
      :key="exercise.id">
              <v-card class="mx-auto my-12 pl-3">
                <v-card-title style="font-weight: bold;">{{ exercise.name }}</v-card-title>
                <div vr id="imgDiv" class="pl-3">
                  <div>
                    <h4 class="group">{{ exercise.muscleGroup }}</h4>
                    <h5 class="user_id" v-if="exercise.userId > 0"> {{ exercise.userId }} </h5>
                    <h5 class="type" v-show="exercise.type != 'Cardio'">
                      {{ exercise.type }}
                    </h5>
                    <h6 v-show="exercise.type != 'Cardio'">Reps:</h6>
                    <h4 class="reps" v-show="exercise.type != 'Cardio'">{{ exercise.repRange }}</h4>
                    <h6>Time:</h6>
                    <h4 class="time">{{ exercise.timeRange }} Mins </h4>
                  </div>
                  <img class="img" :src="`../WorkoutImages/${exercise.muscleGroup}.png`" />
                </div>
                <v-card-text class="pl-2 pr-6">{{ exercise.description }}</v-card-text>
                <v-divider class="mx-4"></v-divider>
                <template>
                  <v-container fluid class="my-4">
                    <v-layout justify-space-between>
                      <v-btn left color="success" fab medium dark v-if="isAuthorized" v-on:click="approve(exercise)" class="ml-4">
                        <v-icon>mdi-check</v-icon>
                      </v-btn>

                      <v-btn right color="primary" fab medium dark v-if="isAuthorized"
          v-on:click="reject(exercise)" class="mr-7">
                        <v-icon>mdi-cancel</v-icon>
                      </v-btn>
                    </v-layout>
                  </v-container>
                </template>
              </v-card>
            </v-flex>
          </v-layout>
        </v-container>
      </div>
    </template>

  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService";
export default {
  name: "view-submitted-exercises",

  data() {
    return {
      showEdit: false,

      targetedExercise: {},

      exercises: [],
    };
  },
  components: {
  },

  created() {
    this.getExercises();
  },

  computed: {
    filteredExercises() {
      const exercises = this.exercises;
        return exercises
    
    },

    isAuthorized() {
      if (this.$store.state.user.authorities[0].name === "ROLE_TRAINER") {
        return true;
      } else {
        return false;
      }
    },
  },

  methods: {
    getExercises() {
      exerciseService.getExercisesByStatus(1).then((response) => {
        this.exercises = response.data;
        console.log("111",this.exercises)
      });
    },
    
    approve(exercise) {
      if (this.isAuthorized) {
        exercise.statusId = 2;
      } else {exercise.statusId = 1;
      }
      exerciseService
        .updateExercise(exercise)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("UPDATE_EXERCISE", exercise);
            this.exercise = {};
            this.getExercises();
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.createError = true;
          }
        });
    },

    reject(exercise) {
      if (this.isAuthorized) {
        exercise.statusId = 3;
      } else {exercise.statusId = 1;
      }
      exerciseService
        .updateExercise(exercise)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("UPDATE_EXERCISE", exercise);
            this.exercise = {};
            this.getExercises();
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.createError = true;
          }
        });
    }
  },
};
</script>

<style scoped>
.main {
  display: flex;
  max-width: 100%;
  flex-wrap: wrap;
  min-width: 320px;
}

#imgDiv {
  display: flex;
  justify-content: space-between;
  align-content: center;
}

img {
  height: 100px;
  margin-bottom: 10px;
  border-radius: 5px;
  margin-right: 40px;
}

.exerciseDiv {
  display: inline-block;
  margin: 20px;
  padding: 15px;
  border-radius: 8px/7px;
  background-color: #ebebeb;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  max-width: 275px;
  min-width: 275px;
  height: max-content;
}

.btnDiv {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.edit {
  background-color: rgb(161, 161, 38);
}

.delete {
  background-color: red;
}
button {
  font-size: 14px;
  font-weight: 600;
  color: white;
  text-decoration: none;
  width: 120px;
  height: 30px;
  border-radius: 5px;
  background-color: #00afef;
  box-shadow: 0 3px rgba(58, 87, 175, 0.75);
  padding: 5px;
  margin: 15px;
}

.add:hover {
  top: 3px;
  border: #4c4c4c;
}
</style>