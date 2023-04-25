<template>
  <div id="faq">
    <div style="position: absolute; left: 10%; top: 10%;">
      <template>
        <v-card max-width="800" class="mx-auto">

          <!-- <v-container> -->
            <!-- <v-row>
              <v-col cols="12"> -->
                <v-card :color="'#1F7087'" dark>
                  <div class="d-flex flex-no-wrap justify-space-between">
                    <div>
                      <v-card-title class="text-h5" v-text="'FAQ - FitReady Workout Builder'"></v-card-title>

                      <v-container>
                        <v-row>
                          <v-col v-for="(section, index) in faqSections" :key="index" cols="12">
                            <v-card dark>
                              <v-card-title>{{ section.title }}</v-card-title>
                              <v-card-text>
                                <v-list>
                                  <v-list-item-group>
                                    <v-list-item v-for="(question, index) in section.questions" :key="index">
                                      <v-list-item-content>
                                        <v-list-item-title>{{ question.q }}</v-list-item-title>
                                        <v-list-item-text>{{ question.a }}</v-list-item-text>
                                      </v-list-item-content>
                                    </v-list-item>
                                  </v-list-item-group>
                                </v-list>
                              </v-card-text>
                            </v-card>
                          </v-col>
                        </v-row>
                      </v-container>

                    </div>
                  </div>
                </v-card>
              <!-- </v-col>
            </v-row> -->
          <!-- </v-container> -->
        </v-card>
      </template>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      faqSections: [
        {
          title: "General Questions",
          questions: [
            {
              q: "Q: What is the FitReady Workout Builder?",
              a: "A: The Workout Builder is an online tool that helps you create personalized workout plans based on your fitness goals, experience level, and equipment availability."
            },
            {
              q: "Q: Do I need any special equipment or gym membership to use the FitReady Workout Builder?",
              a: "A: Not necessarily. You can create workouts that require little to no equipment if you prefer to exercise at home or outdoors. However, if you have access to a gym or certain equipment, the Workout Builder can incorporate those into your workouts as well."
            }
          ]
        },
        {
          title: "Building Workouts",
          questions: [
            {
              q: "Q: Can I customize my workouts based on my personal preferences?",
              a: "A: Absolutely. You can choose from a wide variety of exercises, set your own repetitions, sets, and rest intervals, and even add your own notes to each exercise to keep track of modifications or specific instructions."
            },
          ]
        }
      ]
    };
  }
};
</script>

<style scoped>

#faq {
  margin-left: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background: linear-gradient(90deg, #C7C5F4, #776BCC);
  background-image: url('https://i.imgur.com/OCYGfq8.png');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
  height: 100vh;
  width: 100vw;
  overflow: auto;
}
.v-card-text {
  padding: 0;
}
.v-list-item-title {
  font-weight: bold;
}
</style>