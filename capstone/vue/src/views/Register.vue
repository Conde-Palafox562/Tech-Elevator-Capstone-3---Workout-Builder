<template>
  <div
    class="bg-image"
    style="
      background-image: url('https://i.imgur.com/O9ppbYH.png');
      background-repeat: no-repeat;
      background-attachment: fixed;
      background-size: 100% 100%;
      height: 100vh;
      width: 100vw;
    "
  >
    <div id="register" class="text-center">
      <form @submit.prevent="register">
        <h1>Create Account</h1>
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input
          class="inputBox"
            type="text"
            id="username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input
          class="inputBox"
            type="password"
            id="password"
            v-model="user.password"
            required
          />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password</label>
          <input
          class="inputBox"
            type="password"
            id="confirmPassword"
            v-model="user.confirmPassword"
            required
          />
        </div>
        <div class="form-input-group" style="margin-bottom: 60px;">
          <label> Role </label>
          <select v-model="user.role" style="text-align: center; border-style: double;     -webkit-appearance: auto;">
            <option value="user">User</option>
            <option value="trainer">Trainer</option>
          </select>
        </div>
        <div style="text-align: center;">
        <button type="submit" style="display: block;">Create Account</button>
        </div>
        
        <p style="text-align: center; margin-top: 20px;">
          <router-link :to="{ name: 'login' }"
            >Already have an account? Log in.</router-link
          >
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
.form-input-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
.inputBox {
  /* border: none; */
  border-bottom: 2px solid #D1D1D4;
  background: none;
  padding: 10px;
  padding-left: 24px;
  font-weight: 700;
  width: 100%;
  transition: .2s;
}

#register form {
  background: #fff;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0px 0px 24px #5c5696;
  width: 360px;
  align-items: center;
  justify-content: center;
}

#register {
  display: flex;
  flex-direction: column;
  /* padding-top: 60px; */
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}

#register h1 {
text-align: center;
font-size: 1.5rem;
margin-bottom: 1rem;
color: #6A679E;
}
</style>