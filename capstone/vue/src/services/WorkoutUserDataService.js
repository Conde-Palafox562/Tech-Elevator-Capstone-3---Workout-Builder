import axios from "axios";
export default {
  allUserWorkoutData() {
    return axios.get(`/workoutData`);
  },

  addNewWorkoutData(user, date, exercise) {
    return axios.post(`/workoutData/add/${user}/${date}/${exercise}`, user, date, exercise);
  },

  getWorkoutDataForUser(user) {
    return axios.get(`/workoutData/${user}`, user);
  }
  
};