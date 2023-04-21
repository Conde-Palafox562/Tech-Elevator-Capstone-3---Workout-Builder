<template>
  <div id="login">
    <form @submit.prevent="login">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>
   

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Raleway:400,700');

*{
box-sizing: border-box;
margin: 0;
padding: 0;
font-family: Raleway, sans-serif;
}
body {
    background: linear-gradient(90deg, #C7C5F4, #776BCC);
    background-image: url('https://i.imgur.com/VJJZe6A.png');
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-size: 100% 100%;
    height: 100vh;
    width: 100vw; 
    position: fixed;
    

}

#login {
margin-left: center;
display: flex;
flex-direction: column;
align-items: center;
justify-content: center;
min-height: 100vh;
}

.form-input-group {
display: flex;
flex-direction: column;
margin-bottom: 1rem;
}

label {
font-weight: 700;
margin-bottom: 0.5rem;
}

input[type="text"],
input[type="password"] {
border: none;
border-bottom: 2px solid #D1D1D4;
background: none;
padding: 10px;
padding-left: 24px;
font-weight: 700;
width: 100%;
transition: .2s;
}

input[type="text"]:active,
input[type="text"]:focus,
input[type="text"]:hover,
input[type="password"]:active,
input[type="password"]:focus,
input[type="password"]:hover {
outline: none;
border-bottom-color: #6A679E;
}

button[type="submit"] {
background: #fff;
font-size: 14px;
margin-top: 30px;
padding: 16px 20px;
border-radius: 26px;
border: 1px solid #D4D3E8;
text-transform: uppercase;
font-weight: 700;
display: flex;
align-items: center;
width: 100%;
color: #4C489D;
box-shadow: 0px 2px 2px #5C5696;
cursor: pointer;
transition: .2s;
}

button[type="submit"]:active,
button[type="submit"]:focus,
button[type="submit"]:hover {
border-color: #6A679E;
outline: none;
}

#login h1 {
text-align: center;
font-size: 1.5rem;
margin-bottom: 1rem;
color: #6A679E;
}

#login form {
background: #fff;
padding: 2rem;
border-radius: 10px;
box-shadow: 0px 0px 24px #5C5696;
width: 360px;
position: fixed;


}

#login div[role="alert"] {
background-color: #FEE2E2;
border: 1px solid #FCA5A5;
color: #9B2C2C;
padding: 0.75rem 1.25rem;
margin-bottom: 1rem;
}

#login div[role="alert"]:first-child {
margin-top: 1rem;
}

#login div[role="alert"] a {
color: #9B2C2C;
font-weight: 700;
text-decoration: none;
}

#login div[role="alert"] a:hover {
text-decoration: underline;
}

#login p {
text-align: center;
margin-top: 1rem;
}

#login p a {
color: #4C489D;
}
</style>