<template>
  <!-- Background image -->
  <div class="bg-image">
    <div class="mask" style="background-color: rgba(0, 0, 0, 0.6)">
      <div class="d-flex justify-content-center align-items-center h-100"></div>
    </div>
    <router-link :to="{ name: 'Exercise' }">View Exercises</router-link>
    <user-dashboard />
   
  </div>
  <!-- Background image -->
</template>

<script>
import UserDashboard from "../components/UserDashboard.vue";


export default {
  name: "Home",
  components: {
    UserDashboard,
    
  },
};
</script>

<style>
.bg-image {
  background-image: url('https://i.imgur.com/UgPKrYV.png');
      background-repeat: no-repeat;
      background-attachment: fixed;
      background-size: 100% 100%;
      height: 100vh;
      width: 100vw;
      overflow: auto;
}
  /* Other background properties go here */

h1 {
  font-size: 50px;
}

/* p {
    font-size: 100px;
  }

  .home {
    
  } */
</style>