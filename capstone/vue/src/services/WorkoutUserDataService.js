import axios from "axios";
export default {
  allUserWorkoutData() {
    return axios.get(`/workoutData`);
  },

  addNewWorkoutData(exercise) {
    return axios.post(`/workoutData/add/`, exercise);
  },

  getWorkoutDataForUser() {
    return axios.get(`/workoutData/me`);
  }
  
};