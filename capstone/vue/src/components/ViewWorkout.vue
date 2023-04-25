<template>
  <div class="main">
    <add-workout v-if="$store.state.showWorkout === true" />
    <template>
      <div>
        <v-container fluid grid-list-md>
          <v-layout row wrap>
            <v-flex class="ml-8" style="  max-width: 320px;  
  min-width: 320px;" xs12 sm6 md4 v-for="workout in $store.state.workouts" :key="workout.workoutId">
              <v-card :loading="loading" class="mx-auto my-12 pl-6" style="height: 430px;">
                <template slot="progress">
                  <v-progress-linear color="deep-purple" height="10" indeterminate></v-progress-linear>
                </template>

                <v-card-title style="font-weight: bold;">{{ workout.workoutName }}</v-card-title>

                <v-card-text>
                  <v-row align="center" class="mx-0">
                    <!-- <v-rating :value="4.5" color="amber" dense half-increments readonly size="14"></v-rating> -->

                    <div class=" ms-4" v-if="workout.dateCompleted"> Completed On : {{ workout.dateCompleted }}
                    </div>
                  </v-row>

                  <div class="mt-4 mb-2 text-subtitle-1" style="font-weight: bold;">
                    <!-- {{ $store.state.user.authorities[0].name === 'ROLE_TRAINER' ? 'Trainer: ' : 'User: ' }} {{ workout.trainer
                    }} -->
                    Trainer: {{ workout.trainer
                    }}
                  </div>
                  <v-row align="center" class="mx-0">
                    <div class=" ms-4">
                      <!-- {{ $store.state.user.authorities[0].name === 'ROLE_TRAINER' ? 'Trainer Id: ' : 'User Id: ' }} {{
                        workout.userId }} -->
                        User Id: {{
                        workout.userId }}
                    </div>
                  </v-row>
                  <br />
                  <v-carousel class="carousel" height="200px" hide-delimiter-background hide-delimiters
                    show-arrows-on-hover>
                    <v-carousel-item id="carocards" v-for="exercise in workout.exercises" :key="exercise.id">
                      <h4 class="reps">{{ exercise.name }}</h4>
                      <div vr id="imgDiv">
                        <div id="info">
                          <h4 class="group">{{ exercise.muscleGroup }}</h4>
                          <h5 class="type" v-show="exercise.type != 'Cardio'">
                            {{ exercise.type }}
                          </h5>
                          <h6 v-show="exercise.type != 'Cardio'">Reps:</h6>
                          <h4 class="reps" v-show="exercise.type != 'Cardio'">
                            {{ exercise.repRange }}
                          </h4>
                          <h6>Time:</h6>
                          <h4 class="time">{{ exercise.timeRange }} Mins</h4>
                        </div>
                        <img class="img" :src="`../WorkoutImages/${exercise.muscleGroup}.png`" />
                      </div>
                      <v-container fluid style="margin-top: -21px;">
                        <v-row justify="center">
                          <v-btn color="primary" icon fab small dark v-on:click="deleteExerciseFromWorkout(exercise, workout)" class="mr-6">
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                        </v-row>
                      </v-container>

                    </v-carousel-item>
                  </v-carousel>

                </v-card-text>

                <v-divider class="mx-4" v-bind:class="{ 'progress': !workout.completed }"></v-divider>

                <div class="my-3">
                  <v-row>
                    <v-col class="text-left" cols="6">
                      <v-btn color="success" fab medium dark v-if="workout.completed == false"
                        v-on:click="completeWorkout(workout)" class="ml-9">
                        <v-icon>mdi-check</v-icon>
                      </v-btn>
                    </v-col>

                    <v-col class="text-right" cols="6">
                      <v-btn color="primary" fab medium dark v-on:click="deleteWorkout(workout)" class="mr-12">
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                    </v-col>
                  </v-row>
                </div>
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
import addWorkout from "../components/AddWorkout.vue";
import workoutService from "../services/WorkoutService";
import AuthService from '../services/AuthService';

export default {
  name: "view-workout",

  data() {
    return {

      showEdit: false,

      date: null,

      targetedExercise: {},

      // defaultExercises: [ this.$store.state.user.id ],
      defaultExercises: [],


      showWorkout: false,
    };
  },
  components: {
    addWorkout,
  },

  created() {
    this.getWorkouts();
    this.getTrainers();
  },

  computed: {
    filteredWorkouts() {
      const exerciseFilter = this.$store.state.filter;
      const exercises = this.exercises;
      return exercises.filter((exercise) => {
        return exercise.statusId === 2 && exerciseFilter == ""
          ? true
          : exerciseFilter == exercise.muscleGroup;
      });
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
    getUsername(id) {
      AuthService.getUserNameById(id).then((response) => {
        console.log(response.data.username)
        return response.data.username
      })
    },

    getTrainers() {
      AuthService.getUsers().then((response) => {
        if (response.status == 200) {
          let users = response.data;
          this.trainers = users.filter((u) => {
            return u.authorities[0].name === "ROLE_TRAINER"
          })
          this.trainers.forEach((t) => {
            // this.defaultExercises.push(t)
            this.defaultExercises.push(t.id)
          })
        }
      })
        .then(() => {
          console.log("this.defaultExercises", this.defaultExercises, this.$store.state.user)
          const id = this.$store.state.user ? this.$store.state.user.id : 1;
          // this.defaultExercises.forEach((id) => {
          workoutService.getWorkoutsByUserID(id).then((response) => {
            let workouts = response.data;
            console.log("workouts", workouts, this.$store.state.workouts)
            workouts.forEach((workout) => {
              exerciseService
                .getExercisesByWorkouts(workout.id)
                .then((response) => {
                  workout.workoutId = workout.id;
                  workout.exercises = response.data;
                  AuthService.getUserNameById(workout.trainerId).then((response) => {
                    workout.trainer = response.data.username
                    this.$store.commit("ADD_WORKOUT", workout);
                  })
                })
            });
          });
          // });
        })
    },

    getWorkouts() {
      console.log("this.defaultExercises", this.defaultExercises)
      this.defaultExercises.forEach((id) => {
        workoutService.getWorkoutsByUserID(id).then((response) => {
          let workouts = response.data;
          console.log("workouts", workouts)
          workouts.forEach((workout) => {
            exerciseService
              .getExercisesByWorkouts(workout.id)
              .then((response) => {
                workout.workoutId = workout.id;
                workout.exercises = response.data;
                AuthService.getUserNameById(workout.trainerId).then((response) => {
                  workout.trainer = response.data.username
                  this.$store.commit("ADD_WORKOUT", workout);
                })
              })
          });
        });
      });
    },

    deleteWorkout(workout) {
      if (this.$store.state.user.id != workout.userId) {
        alert("Only the creating User can delete this Workout");
      } else {
        workoutService.deleteWorkoutExercise(workout.id).then((response) => {
          if (response.status == 200) {
            workoutService.deleteWorkout(workout.id);
            this.$store.commit("DELETE_WORKOUT", workout.workoutId);
          }
        });
      }
    },
    deleteExerciseFromWorkout(exercise, workout) {
      workoutService.deleteExerciseFromWorkout(exercise.id, workout.workoutId);
      const tempExercise = {
        exerciseId: exercise.id,
        workoutId:  workout.workoutId
      }
      this.$store.commit("DELETE_EXERCISE_FROM_WORKOUT", tempExercise);
      this.getTrainers();
    },
    getCurrentDate() {
      let dateTime = new Date();
      return dateTime.toJSON().slice(0, 10)
    },

    completeWorkout(workout) {
      workout.completed = true;
      this.getCurrentDate;
      workout.dateCompleted = this.getCurrentDate()
      console.log(workout)
      workoutService.editWorkout(workout.id, workout).then((response) => {
        if (response.status == 200) {
          this.$store.commit("UPDATE_WORKOUT", workout);
        }
      });
    },
  },
};
</script>

<style scoped>
.progress {
  margin-top: 20px;
}

.main {
  display: flex;
  max-width: 100%;
  flex-wrap: wrap;
  min-width: 320px;
  max-height: max-content;
}

#imgDiv {
  display: flex;
  justify-content: space-between;
  justify-content: center;
  align-content: center;
}

#head {
  text-align: center;
}

#info {
  margin-bottom: 30px;
}

img {
  height: 100px;
  margin-bottom: 10px;
  border-radius: 5px;
  margin-right: 40px;
}

.exerciseDiv {
  display: inline-block;
  justify-content: center;
  margin: 20px;
  padding: 15px;
  border-radius: 8px/7px;
  background-color: #ebebeb;
  box-shadow: 1px 2px 5px black;
  border: solid 1px black;
  max-width: 275px;
  min-width: 275px;
  height: max-content;
}

/* .btnDiv {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;

} */

.edit {
  background-color: rgb(161, 161, 38);
}

.delete {
  background-color: red;
}

/* button {
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
} */

.add:hover {
  top: 3px;
  border: #4c4c4c;
}</style>