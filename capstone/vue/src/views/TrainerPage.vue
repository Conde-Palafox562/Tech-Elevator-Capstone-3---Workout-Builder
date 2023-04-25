<template>
  <div id="app">
    <v-app id="inspire">
      <v-card id="lateral">
        <v-toolbar id="toolbar" dark tabs flat color="light-blue darken-4">
          <img class="img" :src="`../logo.png`" width="340px" style="padding-top: 40px; margin-left: -100px;" 
    />
          <v-spacer id="vspace"></v-spacer>
          <v-toolbar-title class="mr-6 mt-6 font-weight-bold">{{ this.$store.state.user.username }}</v-toolbar-title>

          <!-- DashBoard and tabs /-->
          <template v-slot:extension>
            <v-tabs v-model="tabs" centered>
              <v-tab class="font-weight-bold"> Summary</v-tab>
              <v-tab class="font-weight-bold"> Create Workout</v-tab>
              <v-tab class="font-weight-bold"> Workouts </v-tab>
              <v-tab class="font-weight-bold"> Exercises </v-tab>
              <v-tab class="font-weight-bold"> Add Exercise </v-tab>
              <v-tab class="font-weight-bold"> Submitted Exercise </v-tab>
              <v-tabs-slider color="pink"></v-tabs-slider>
            </v-tabs>
          </template>

          <!-- Main content area below tabs -->
        </v-toolbar>

        <!-- Might not need card text. Ideally on the selection of a tab we display 
      the corresponding component -->
        <v-card-text id="text">
          <v-tabs-items v-model="tabs">
            <v-tab-item v-for="content in ['one', 'two', 'three', 'four', 'five']" :key="content" :value="content">
              <v-card> </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-card-text>
      </v-card>
      <div id="trainer">
        <summary-exercise v-if="this.tabs === 0" />
        <create-workout v-if="this.tabs === 1" />
        <view-workout v-if="this.tabs === 2" />
        <view-exercises v-if="this.tabs === 3" />
        <add-exercise v-if="this.tabs === 4" />
        <view-submitted-exercises v-if="this.tabs === 5" />
      </div>
    </v-app>
  </div>
</template>

<script>
import summaryExercise from "../components/Summary.vue";
import addExercise from "../components/AddExercise.vue";
import viewExercises from "../components/ViewExercises.vue";
import viewSubmittedExercises from "../components/ViewSubmittedExercises.vue";
import viewWorkout from "../components/ViewWorkout.vue";
import createWorkout from "../components/CreateWorkout.vue";
export default {
  name: "trainer-page",
  components: {
    summaryExercise,
    addExercise,
    viewExercises,
    viewSubmittedExercises,
    viewWorkout,
    createWorkout,
  },

  data() {

    return {
      items: ['All', 'Cardio', 'Chest', 'Back', 'Biceps', 'Triceps', 'Shoulders', 'Legs', 'Total Body'],
      created: false,
      fab: false,
      hidden: false,
      tabs: null,
      filter: ""
    };
  },

  created() {
    this.updateFilter(this.filter)
  },

  methods: {

    updateFilter() {
      if (this.filter === 'All') this.filter = '';
      if (this.filter === 'Total Body') this.filter = 'TotalBody';
      console.log("this.filter", this.filter)
      this.$store.commit("UPDATE_FILTER", this.filter)
      this.filter = "";
    }

  }
};
</script>

<style scoped>
#trainer {
  min-height: 100vh;
  background: linear-gradient(90deg, #C7C5F4, #776BCC);
  background-image: url('https://i.imgur.com/UgPKrYV.png');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
  /* height: 100vh; */
  width: 100vw;
}

#main {
  height: 90%;
}

#filter {
  background-color: white;
  min-width: 80px;
  width: max-content;
}

option {
  min-width: 80px;
  width: 15%;
}

#inspire {
  height: min-content;

}

#text {
  padding: 0;
}
</style>