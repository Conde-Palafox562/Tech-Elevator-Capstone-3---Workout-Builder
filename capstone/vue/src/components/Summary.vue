<template>
  <div style="margin-top: 60px; margin-left: 120px; text-align: center;">
    <table>
      <thead>
        <tr>
          <th>Workout</th>
          <th>Trainer</th>
          <th>Date</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody v-for="workout in $store.state.workouts" :key="workout.workoutId">
        <tr>
          <td>{{ workout.workoutName }}</td>
          <td>{{ workout.trainer }}</td>
          <td>{{ workout.dateCompleted }}</td>
          <td>Completed</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService.js";
import workoutService from "../services/WorkoutService";
import AuthService from '../services/AuthService';

export default {
  name: "summary-exercise",
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
      defaultExercises: [],
      createError: false,
      createSuccess: false,



    };
  },
  created() {
    this.getTrainers();
  },

  methods: {
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
          const id = this.$store.state.user.id;
          // this.defaultExercises.forEach((id) => {
          workoutService.getWorkoutsByUserID(id).then((response) => {
            let workouts = response.data;
            this.$store.state.workouts = [];
            workouts.forEach((workout) => {
              exerciseService
                .getExercisesByWorkouts(workout.id)
                .then((response) => {
                  workout.workoutId = workout.id;
                  workout.exercises = response.data;
                  AuthService.getUserNameById(workout.trainerId).then((response) => {
                    workout.trainer = response.data.username
                    console.log("workout", workout)
                    if (workout.completed) this.$store.commit("ADD_WORKOUT", workout);
                  })
                })
            });
          });
          // });
        })
    },
  },
};
</script>
<style scoped>
.container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

table {
  width: 800px;
  border-collapse: collapse;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

th,
td {
  padding: 15px;
  background-color: rgba(255, 255, 255, 0.2);
  color: #fff;
}

thead th {
  background-color: #55608f;
}

tbody tr:hover {
  background-color: rgba(255, 255, 255, 0.3);
}

tbody td {
  position: relative;
}

tbody td:hover:before {
  content: "";
  position: absolute;
  left: 0;
  right: 0;
  top: -9999px;
  bottom: -9999px;
  background-color: rgba(255, 255, 255, 0.2);
  z-index: -1;
}
</style>
