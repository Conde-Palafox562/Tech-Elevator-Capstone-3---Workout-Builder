<template>
  <div id="exercise" class="testbox">
    <form class="form-signin" @submit.prevent="pushWorkout()">
      <h1 class="h3 mb-3 font-weight-normal">Create Workout</h1>
      <div class="alert" role="alert" v-if="createError">
        Unable to Generate Workout. <span>{{ errorMsg }}</span>
      </div>

      <div class="success" v-if="createSuccess">Workout Submitted.</div>

      <v-text-field name="name" id="name" label="Workout Name" autocomplete="off" v-model="workout.workoutName" required
        autofocus></v-text-field>
      <v-select v-model="checkBoxes" :items="targets" label="Target areas" multiple chips persistent-hint
        @change="updateFilter"></v-select>
        <div style="text-align: center;">
        <v-select :items="computedOptions" label="Trainer" required v-model="workout.trainerId"
          v-on:click="generateWorkout(checkBoxes)">
        </v-select>
      </div>
      <!-- <div style="text-align: center;">
        <v-select :items="computedOptions" :label="computedLabel" required v-model="workout.trainerId"
          v-on:click="generateWorkout(checkBoxes)">
        </v-select>
      </div> -->
      <div class="send">
        <v-btn depressed color="primary" type="submit">
          Create
        </v-btn>
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';
import ExerciseService from "../services/ExerciseService";
import workoutService from "../services/WorkoutService";

export default {
  name: "create-workout",
  components: {},

  data() {
    return {

      errorMsg: "",

      trainers: [],

      trainer: {},

      checkBoxes: [],
      allSelected: false,

      tempArr: [],
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

      workout: {
        workoutName: "",
        trainerId: null,
        userId: this.$store.state.user.id,
        completed: false,
        dateCompleted: null,
      },

      createError: false,
      createSuccess: false,
    };
  },

  created() {
    this.getTrainers();
  },
  computed: {
    computedLabel() {
    return this.$store.state.user.authorities[0].name === "ROLE_TRAINER"? 'Trainer' : 'User';
  },
    checkRandom() {
      if (this.checkBoxes.includes("All")) {
        return true;
      } else return false;
    },
    computedOptions() {
      return this.trainers.map(value => ({ value: `${value.id}`, text: `${value.username}` }))
    }
  },

  methods: {
    updateFilter() {
      if (this.checkBoxes.includes("All")) {
        this.allSelected = true;
        this.checkBoxes = [
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
      } else if (this.allSelected & !this.checkBoxes.includes("All")) {
        this.allSelected = false;
        this.checkBoxes = [];
      }
    },
    // getTrainers() {
    //   authService.getUsers().then((response) => {
    //     if (response.status == 200) {
    //       let users = response.data;
    //       console.log("users", users)
    //       if (this.$store.state.user.authorities[0].name === "ROLE_TRAINER") {
    //         this.trainers = users.filter((u) => {
    //           return u.authorities[0].name === "ROLE_TRAINER" & u.id === this.$store.state.user.id
    //         })
    //       } 
    //       if (this.$store.state.user.authorities[0].name === "ROLE_USER") {
    //         this.trainers = users.filter((u) => {
    //           return u.authorities[0].name === "ROLE_USER" & u.id === this.$store.state.user.id
    //         })
    //       }

    //     }
    //   })
    // },
    getTrainers() {
      authService.getUsers().then((response) => {
        if (response.status == 200) {
          let users = response.data;
          this.trainers = users.filter((u) => {
            return u.authorities[0].name === "ROLE_TRAINER"
          })
        }
      })
    },
    generateWorkout(focusArr) {
      let ids = [];

      if (focusArr.includes("All")) {
        focusArr = [
          "Chest",
          "Back",
          "Biceps",
          "Triceps",
          "Abs",
          "Legs",
          "Cardio",
          "TotalBody"
        ];
      } else if (focusArr.length === 0) {
        // this.createError = true;
      }

      for (let i = 0; i < focusArr.length * 3; i++) {
        let exerciseFocus =
          focusArr[Math.floor(Math.random() * focusArr.length)];
        console.log("exerciseFocus, focusArr", exerciseFocus, focusArr)
        ExerciseService.getExercisesByMuscleGroup(exerciseFocus).then(
          (response) => {
            if (response.status == 200) {
              let exercises = response.data;

              // let exercise =
              //   exercises[[Math.floor(Math.random() * exercises.length)]];

              // ids.push(exercise);
              ids = exercises;
              // console.log("ids", ids)
              this.tempArr = ids;
            }
          })
          .catch((error) => {
            const response = error.response;
            this.createError = true;
            if (response.status != 400) {
              this.errorMsg = 'No Exercises Available!';
            }
          });
      }
      this.getTrainers();
    },

    pushWorkout() {
      const map = {};
      const newArray = [];
      this.tempArr.forEach((element) => {
        if (!map[JSON.stringify(element)]) {
          map[JSON.stringify(element)] = true;
          newArray.push(element);
        }
      });
      this.tempArr = newArray;


      workoutService.addWorkout(this.workout).then((response) => {
        console.log(this.workout)
        if (response.status == 200) {
          this.createSuccess = true;

          let newWorkoutId = response.data.id;

          this.tempArr.forEach((exercise) => {
            let temp = {
              workoutId: newWorkoutId,
              exerciseId: exercise.id
            }
            workoutService
              .sendExercises(newWorkoutId, temp)
          });
        }
      });
    },
  },
};
</script>
<style scoped>
h1 {
  font-size: 32px;
  font-weight: 300;
  color: #4c4c4c;
  text-align: center;
  padding-top: 10px;
  margin-bottom: 10px;
  max-width: 100%;
}

.focusDiv {
  display: inline-flex;
  flex-wrap: wrap;
  width: 100%;
}

.testbox {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin: 20px auto;
  border-radius: 8px/7px;
  background-color: #ebebeb;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  max-width: 640px;
  min-width: 300px;

}

.success {
  box-shadow: 1px 2px 5px black;
  text-align: center;
  font-size: 14px;
  font-weight: 600;
  background-color: #3a57af;
  color: #ebebeb;
  border-radius: 5px;
  backface-visibility: hidden;
  perspective: 1000px;
}

.alert {
  box-shadow: 1px 2px 5px black;
  text-align: center;
  font-size: 14px;
  font-weight: 600;
  background-color: rgb(240, 52, 52);
  color: #ebebeb;
  border-radius: 5px;
  animation: shake;
  animation: shake 0.82s cubic-bezier(0.36, 0.07, 0.19, 0.97) both;
  transform: translate3d(0, 0, 0);
  backface-visibility: hidden;
  perspective: 1000px;
}

@keyframes shake {

  10%,
  90% {
    transform: translate3d(-1px, 0, 0);
  }

  20%,
  80% {
    transform: translate3d(2px, 0, 0);
  }

  30%,
  50%,
  70% {
    transform: translate3d(-4px, 0, 0);
  }

  40%,
  60% {
    transform: translate3d(4px, 0, 0);
  }
}

hr {
  color: #f86c6c;
  opacity: 0.3;
}

form {
  margin: 0 30px;
  max-width: 100%;
}

input[type="checkbox"] {
  margin-left: 5px;
}

/* input[type="text"] {
  width: 60%;
  height: 39px;
  border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
  background-color: #fff;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  margin-left: -5px;
  margin-top: 20px;
  padding-left: 0px;
  max-width: 100%;
  text-align: center;
} */

#muscle-group {
  margin-top: 10px;
}

/* select {
  width: 50%;
  height: 39px;
  border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
  background-color: #fff;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  margin-left: -5px;
  margin-top: 20px;
  padding-left: 10px;
  max-width: 100%;
  text-align: center;
} */

textarea {
  border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
  background-color: #fff;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  margin-top: 13px;
  margin-left: 10px;
  padding-left: 10px;
  max-height: 200px;
  max-width: 90%;
}

/* input {
  margin-bottom: 15px;
} */

#icon {
  width: 160px;
  background-color: #5d54a4;
  border-radius: 5px;
  padding: 8px 8px 8px 12px;
  margin: 1rem;
  color: white;
  box-shadow: 2px 3px 3px rgb(58 87 175 / 75%);
  border: solid 1px #cbc9c9;
  position: relative;
}

#user-icon {
  margin-right: 4px;
}

.send {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  margin: 10px;
}

button {
  font-size: 14px;
  font-weight: 600;
  color: white;
  text-decoration: none;
  width: 100px;
  height: 30px;
  border-radius: 5px;
  background-color: #3a57af;
  box-shadow: 0 3px rgba(58, 87, 175, 0.75);
  align-self: center;
  margin-bottom: 10px;
}

@media only screen and (max-width: 600px) {
  .testbox {
    max-width: 70vw;
  }

  #icon {
    width: 200%;
  }
}
</style>