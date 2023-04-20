<template>
  <div>
      <div class="trainerInfo" 
      v-for= "trainer in trainers" 
      v-bind:key= "trainer.trainer_id">

    </div>
  </div>
</template>

<script>
import TrainerService from "../services/TrainerService.js";

export default {
    created() {
        TrainerService.listTrainers().then (
            response=> {
                this.TrainerService = response.data
            }
        )
    },

    data () {
        return {
            trainers: {
                trainer_id: 0,
                trainer_name: "",

            },
            trainer: [],
            filters: [],
        }
    }

}
</script>

<style>

</style>