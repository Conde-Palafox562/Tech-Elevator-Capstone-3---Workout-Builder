<template>
  <div class="main">
    <update-exercise v-if="$store.state.showEdit === true" />
    <add-workout v-if="$store.state.showAddWorkout === true" />
    <template>
      <div>
        <div class="mt-4" style="text-align: center; width: 600px; position: absolute; 
    right: 100px;" v-show="$store.state.showEdit != true && $store.state.showAddWorkout != true">
          <v-select v-model="filter" :items="targets" label="Target areas" multiple chips solo persistent-hint
            @change="updateFilter"></v-select>
        </div>
        <v-container fluid grid-list-md style="margin-top: 100px;">
          <v-layout row wrap>
            <v-flex class="ml-8" style="  max-width: 320px; 
  min-width: 320px;" xs12 sm6 md4 v-show="$store.state.showEdit != true && $store.state.showAddWorkout != true"
              v-for=" exercise  in           filteredExercises          " v-bind:exercise="exercise" :key="exercise.id">
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
                <v-textarea outlined name="input-7-4" label="Description" :value=exercise.description style="font-size: 0.875rem;
    font-weight: 400; 
    line-height: 1.375rem;
    letter-spacing: 0.0071428571em;" class="pl-2 pr-6 mt-6"></v-textarea>
                <v-divider class="mx-4"></v-divider>
                <template>
                  <v-container fluid class="my-4">
                    <v-layout justify-space-between>
                      <v-btn left color="success" fab medium dark v-on:click="toggleAddWorkout();
                      targetExercise(exercise)
                                                                  " class="ml-4">
                        <v-icon>mdi-plus</v-icon>
                      </v-btn>
                      <v-btn center class="mx-2" fab dark medium color="cyan" v-if=" isAuthorized " v-on:click="
                        toggleEditButton();
                        targetExercise(exercise);
                      ">
                        <v-icon dark>
                          mdi-pencil
                        </v-icon>
                      </v-btn>
                      <v-btn right color="primary" fab medium dark v-if=" isAuthorized "
                        v-on:click=" deleteExercise(exercise) " class="mr-7">
                        <v-icon>mdi-delete</v-icon>
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
import UpdateExercise from "../components/UpdateExercise.vue";
import exerciseService from "../services/ExerciseService";
import addWorkout from "../components/AddWorkout.vue";
import workoutService from "../services/WorkoutService";

export default {
  name: "view-exercises",

  data() {
    return {
      showEdit: false,
      filter: this.$store.state.filter ? this.$store.state.filter : [],
      targetedExercise: {},
      allSelected: false,
      exercises: [],
      targets: ['All', 'Chest',
        'Back',
        'Shoulders',
        'Biceps',
        'Triceps',
        'Legs',
        'Abs',
        'Cardio',
        'TotalBody'
      ],

    };
  },
  components: {
    UpdateExercise,
    addWorkout

  },

  created() {
    this.getExercises();
  },

  computed: {
    filteredExercises() {
      console.log("his.$store.state.filter", this.$store.state.filter)
      const exerciseFilter = this.$store.state.filter ? this.$store.state.filter : [];
      const exercises = this.$store.state.exercises;
      var temps = [];
      for (var i = 0; i < exerciseFilter.length; i++) {
        const exerciseOne = exerciseFilter[i];
        const temp = exercises.filter((exercise) => {
          return exerciseOne == exercise.muscleGroup;
        });
        for (var j = 0; j < temp.length; j++) {
          temps.push(temp[j]);
        }

      }
      console.log("temps", temps)
      return temps;
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
    updateFilter() {
      if (this.filter.includes('All')) {
        this.allSelected = true;
        this.filter = [
          'All',
          "Chest",
          "Back",
          'Shoulders',
          "Biceps",
          "Triceps",
          "Abs",
          "Legs",
          "Cardio",
          "TotalBody"
        ];
      } else if (this.allSelected & !this.filter.includes("All")) {
        this.allSelected = false;
        this.filter = [];
      } else if (this.filter.length === 9) {
        this.filter = [];
      }
      console.log("this.filter", this.filter)
      this.$store.commit("UPDATE_FILTER", this.filter)
      // this.filter = [];
    },
    getExercises() {
      exerciseService.getExercisesByStatus(2).then((response) => {
        let exercises = response.data;
        this.$store.state.exercises = exercises;
      });
    },

    toggleEditButton() {
      this.$store.state.showEdit = true
      this.$store.state.showAddWorkout = false;
    },

    toggleAddWorkout() {
      this.$store.state.showAddWorkout = true
      this.$store.state.showEdit = false;
    },

    targetExercise(exercise) {
      this.targetedExercise = exercise;
      this.$store.commit("SELECT_EXERCISE", this.targetedExercise);
    },

    deleteExercise(exercise) {
      workoutService.deleteExerciseWorkout(exercise.id).then((response) => {
        if (response.status == 200) {
          exerciseService
            .deleteExercise(exercise)
            .then((response) => {
              console.log(response);
              if (response.status == 200) {
                this.$store.commit("DELETE_EXERCISE", exercise.id);
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
      })

    },

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

p {
  margin-top: 10px;
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

.add:hover {
  top: 3px;
  border: #4c4c4c;
}

@media only screen and (max-width: 600px) {
  .main {
    display: flex;
    justify-content: center;
  }
}
</style>