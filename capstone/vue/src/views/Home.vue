<template>
  <!-- Background image -->
  <div id="home">
    <v-card >
      <div class="articleDiv">
      <article>
        <div id="reviewDiv">
        <h3>
        At FitReady, we are dedicated to promoting a passion for fitness and wellness on a global scale. 
        We take great care in selecting our team of trainers, handpicking individuals who are best suited 
        to meet our clients' unique requirements. Whether you seek to incorporate a diverse range of workouts 
        into your routine or desire a tailored training experience with a world-renowned expert, FitReady is 
        your premier destination. We invite you to take advantage of our services and experience the difference for yourself. 
        Join us today and elevate your fitness journey to the next level.
        </h3>
      </div>
      </article>
    </div>
    </v-card>

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
  data() {
    return {
    }
  }
};
</script>

<style scoped>
#home {
  min-height: 100vh;
  background: linear-gradient(90deg, #C7C5F4, #776BCC);
  background-image: url('https://i.imgur.com/rI97hPo.png');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
  height: 100vh;
  width: 100vw;
  overflow: auto;
}

#reviewDiv{
display: flex;
font-size: 15px;
}

h1 {
  font-size: 50px;
}

.articleDiv{
  position: absolute;
  z-index: 1000;
  left:100px;
  top:360px;
  width: 600px;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  flex-direction: row;
  padding: 1rem;
  height: 100%;
}

article{
  box-shadow: 3px 3px 10px 5px gray;
  margin: 1rem;
  padding: 10px;
  max-width: 80%;
  min-width: 300px;
  background-color: white;
  height: 240px;
    min-width: 500px;
}

@media (min-width: 1020px) {
  article{
 
      max-width: 30%;
      min-width: 500px;
  
  }
}
</style>