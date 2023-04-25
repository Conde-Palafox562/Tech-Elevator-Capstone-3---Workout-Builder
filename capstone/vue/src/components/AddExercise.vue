<template>
  <div id="exercise" class="testbox">
    <form class="form-signin" @submit.prevent="addExercise()">
      <h1 class="h3 mb-3 font-weight-normal">Create Exercise</h1>
      <div class="alert" role="alert" v-if="createError">
        Unable to create exercise.
      </div>

      <div class="success" v-if="createSuccess">
        Exercise Submitted.
      </div>
      <v-text-field class="mt-9" name="name" dense label="Exercise Name" autocomplete="off" v-model="exercise.name" required
        autofocus></v-text-field>
      <div>
        <v-select :items="muscleGroupItems" label="Target Area" required v-model="exercise.muscleGroup">
        </v-select>
      </div>
      <div>
        <v-select :items="exerciseTypeItems" label="Rep Ranges" required v-model="exercise.repRange">
        </v-select>
      </div>
      <div>
        <v-select :items="timeRangeItems" label="Time-In-Minutes" required v-model="exercise.timeRange">
        </v-select>
      </div>
      <div>
        <v-select :items="exerciseType1Items" label="Exercise Type" required v-model="exercise.type">
        </v-select>
      </div>
      <div>
        <v-textarea v-model="exercise.description" required autofocus outlined name="name"
          label="Description"></v-textarea>
      </div>
      <div class="send">
        <v-btn depressed color="primary" type="submit">
          Create
        </v-btn>
      </div>
    </form>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService.js";

export default {
  name: "add-exercise",
  components: {},

  data() {
    return {
      exercise: {
        id: "",
        name: "",
        userId: null,
        description: "",
        muscleGroup: "",
        repRange: "",
        type: "",
        timeRange: "",
        statusId: 1,
      },

      createError: false,
      createSuccess: false,
      muscleGroupItems: [
        { value: 'Cardio', text: 'Cardio' },
        { value: 'Chest', text: 'Chest' },
        { value: 'Back', text: 'Back' },
        { value: 'Biceps', text: 'Biceps' },
        { value: 'Triceps', text: 'Triceps' },
        { value: 'Shoulders', text: 'Shoulders' },
        { value: 'Legs', text: 'Legs' },
        { value: 'TotalBody', text: 'Total Body' }
      ],
      exerciseTypeItems: [
        { value: 'Cardio', text: 'Cardio' },
        { value: '1 - 5', text: '1 - 5' },
        { value: '5 - 8', text: '5 - 8' },
        { value: '8 - 12', text: '8 - 12' },
        { value: '12+', text: '12+' },
      ],
      timeRangeItems: [
        { value: '1', text: '1 min' },
        { value: '2', text: '2 min' },
        { value: '3', text: '3 min' },
        { value: '4', text: '4 min' },
        { value: '5', text: '5 min' },
      ],
      exerciseType1Items: [
        { value: 'Strength', text: 'Strength' },
        { value: 'Cardio', text: 'Cardio' },
      ],


    };
  },
  methods: {
    addExercise() {
      if (this.$store.state.user.authorities[0].name === "ROLE_TRAINER") {
        this.exercise.statusId = 2;
      } else {
        this.exercise.statusId = 1;
      }
      this.exercise.userId = this.$store.state.user.id,
        exerciseService
          .addExercise(this.exercise)
          .then((response) => {
            if (response.status == 200) {
              this.createSuccess = true;
              this.$store.commit("ADD_EXERCISE", this.exercise);
              this.exercise = {};
            }
          })
          .catch((error) => {
            const response = error.response;

            if (response.status != 200) {
              this.createError = true;
            }
          })

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

.testbox {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin: 20px auto;
  border-radius: 8px/7px;
  background-color: #ebebeb;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  max-width: 400px;
  min-width: 310px;
  text-align: center;
}

::-webkit-input-placeholder {
  text-align: center;
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

input[type="text"] {
  width: 70%;
  height: 39px;
  border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
  background-color: #fff;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  margin-left: -5px;
  margin-top: 20px;
  padding-right: 5px;
  max-width: 100%;
  text-align: center;
}

#muscle-group {
  margin-top: 10px;
}

select {
  width: 70%;
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
}



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

input {
  margin-bottom: 15px;
}

#icon {
  display: inline-block;
  width: 40px;
  background-color: #3a57af;
  padding: 8px 8px 8px 12px;
  margin-left: 1rem;
  margin-right: 10px;
  color: white;
  box-shadow: 1px 2px 5px rgba(0, 0, 0, 0.09);
  border: solid 0px #cbc9c9;
  position: relative;
}

.send {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  margin-bottom: 10px;
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

@media (max-width: 340px) {
  .testbox {
    max-width: 70vw;
  }


}
</style>
