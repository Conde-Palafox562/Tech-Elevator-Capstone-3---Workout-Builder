<template>
  <div class="bg-image" style="
      background-image: url('https://i.imgur.com/UgPKrYV.png');
      background-repeat: no-repeat;
      background-attachment: fixed;
      background-size: 100% 100%;
      height: 100vh;
      width: 100vw;
    ">
    <div class="mask" style="background-color: rgba(0, 0, 0, 0.6)">
      <div class="d-flex justify-content-center align-items-center h-100"></div>
    </div>

    <div style="position: absolute; left: 10%; top: 30%;">
      <template>
  <v-card
    max-width="800"
    class="mx-auto"
  >
    <v-container>
      <v-row dense>
        <v-col
          cols="12"
        >
          <v-card
            :color="'#1F7087'"
            dark
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title
                  class="text-h5"
                  v-text="'ABOUT'"
                ></v-card-title>

                <v-card-subtitle >
                  <v-card-text>
We extend a warm welcome to the FitReady Workout Builder, your ultimate fitness destination. At FitReady, we firmly believe that everyone should have access to a healthy, confident and strong lifestyle. Our mission is to assist you in achieving your fitness objectives by providing tailor-made workout plans that cater to your unique requirements.

Our team of skilled trainers has carefully curated a diverse range of workouts, catering to all levels of fitness- from beginners to advanced athletes. Whether you seek to lose weight, build muscle or enhance your stamina, we have a customized plan designed specifically for you.

Our user-friendly website offers you the ability to create your own personalized workout plan in just a few simple steps. Select workouts that align with your preferences and goals, and our platform will generate a plan to meet your specific requirements. Additionally, you can customize your plan by specifying the days of the week you want to work out and the duration of each session.

We acknowledge that your time is valuable. Thus, our workouts are designed to be effective and can be completed in as little as 30 minutes a day. Our routines target all major muscle groups, ensuring a comprehensive and effective workout experience.

Are you unsure of where to start? Our team of expert trainers is always available to guide you through our platform and answer any queries you may have. We provide a range of resources, including nutrition tips and exercise tutorials, to support you in your fitness journey.

Join the FitReady community today and embark on the first step towards a healthier, stronger version of yourself.
                  </v-card-text>
                </v-card-subtitle>

              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>
    </div>
  </div>
</template>

<script>
  export default {
    data: () => ({
    
    }),
  }
</script>

<style scoped></style>