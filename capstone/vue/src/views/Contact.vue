 
<template>
  <v-app>
    <div id="contact">

      <div style="position: absolute; left: 10%; top: 30%;">
        <div class="container">
          <div class="content">
            <div class="left-side">
              <div class="address details">
                <i class="fas fa-map-marker-alt"></i>
                <div class="topic">Address</div>
                <div class="text-one">123 FitRoad</div>
                <div class="text-two">Fit City, OH 45202</div>
              </div>
              <div class="phone details">
                <i class="fas fa-phone-alt"></i>
                <div class="topic">Phone</div>
                <div class="text-one">(513) 123-4567</div>
              </div>
              <div class="email details">
                <i class="fas fa-envelope"></i>
                <div class="topic">Email</div>
                <div class="text-one">info@fitready.com</div>
              </div>
            </div>
            <div class="right-side">
              <div class="topic-text">Send us a message</div>
              <p>Do you have any questions, comments or concerns? Feel free to reach out to us. We are here for you!</p>
              <form action="#">
                <div class="input-box">
                  <input type="text" v-model="name" placeholder="Enter your name">
                </div>
                <div class="input-box">
                  <input type="text" v-model="email" placeholder="Enter your email">
                </div>
                <div class="input-box message-box">
                  <input type="text" v-model="message" placeholder="Enter your message">
                </div>
                <v-btn color="primary" dark v-on:click="send()">
                  Send Now
                </v-btn>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <template>
      <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="290">
          <!-- <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" dark v-bind="attrs" v-on="on">
              Open Dialog
            </v-btn>
          </template> -->
          <v-card>
            <v-card-title class="text-h5">
              Are you sure you want to send?
            </v-card-title>
            <v-card-text></v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text v-on:click="init()">
                Go Back
              </v-btn>
              <v-btn color="green darken-1" text v-on:click="init()">
                Send
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </template>
  </v-app>
</template>

<script>

export default {
  data() {
    return {
      name: "",
      email: "",
      message: "",
      dialog: false,
      title: 'Confirmation',
    }
  },
  methods: {
    confirm() {
      // Do something...
      this.dialog = false
    },
    send() {
      this.dialog = true;
      console.log(this.name, this.email, this.message)
    },
    init() {
      this.name = "";
      this.email = "";
      this.message = "";
      this.dialog = false;
    }
  }
}
</script>

<style scoped>
#contact {
  min-height: 100vh;
  background: linear-gradient(90deg, #C7C5F4, #776BCC);
  background-image: url('https://i.imgur.com/2VrF4bC.png');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
  height: 100vh;
  width: 100vw;
  overflow: auto;
}

@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

body {
  min-height: 100vh;
  width: 100%;
  background: #c8e8e9;
  display: flex;
  align-items: center;
  justify-content: center;
}

.container {
  width: 85%;
  background: #fff;
  border-radius: 6px;
  padding: 20px 60px 30px 40px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.container .content {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.container .content .left-side {
  width: 25%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 15px;
  position: relative;
}

.content .left-side::before {
  content: '';
  position: absolute;
  height: 70%;
  width: 2px;
  right: -15px;
  top: 50%;
  transform: translateY(-50%);
  background: #afafb6;
}

.content .left-side .details {
  margin: 14px;
  text-align: center;
}

.content .left-side .details i {
  font-size: 30px;
  color: #3e2093;
  margin-bottom: 10px;
}

.content .left-side .details .topic {
  font-size: 18px;
  font-weight: 500;
}

.content .left-side .details .text-one,
.content .left-side .details .text-two {
  font-size: 14px;
  color: #afafb6;
}

.container .content .right-side {
  width: 75%;
  margin-left: 75px;
}

.content .right-side .topic-text {
  font-size: 23px;
  font-weight: 600;
  color: #3e2093;
}

.right-side .input-box {
  height: 50px;
  width: 100%;
  margin: 12px 0;
}

.right-side .input-box input,
.right-side .input-box textarea {
  height: 100%;
  width: 100%;
  border: none;
  outline: none;
  font-size: 16px;
  background: #F0F1F8;
  border-radius: 6px;
  padding: 0 15px;
  resize: none;
}

.right-side .message-box {
  min-height: 110px;
}

.right-side .input-box textarea {
  padding-top: 6px;
}

.right-side .button {
  display: inline-block;
  margin-top: 12px;
}

.right-side .button input[type="button"] {
  color: #fff;
  font-size: 18px;
  outline: none;
  border: none;
  padding: 8px 16px;
  border-radius: 6px;
  background: #3e2093;
  cursor: pointer;
  transition: all 0.3s ease;
}

.button input[type="button"]:hover {
  background: #5029bc;
}

@media (max-width: 950px) {
  .container {
    width: 90%;
    padding: 30px 40px 40px 35px;
  }

  .container .content .right-side {
    width: 75%;
    margin-left: 55px;
  }
}

@media (max-width: 820px) {
  .container {
    margin: 40px 0;
    height: 100%;
  }

  .container .content {
    flex-direction: column-reverse;
  }

  .container .content .left-side {
    width: 100%;
    flex-direction: row;
    margin-top: 40px;
    justify-content: center;
    flex-wrap: wrap;
  }

  .container .content .left-side::before {
    display: none;
  }

  .container .content .right-side {
    width: 100%;
    margin-left: 0;
  }
}

Copy Codes .v-card {
  margin-bottom: 20px;
}
</style>