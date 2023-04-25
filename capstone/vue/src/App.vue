<template>
  <div id="app">
    <nav>
      <ul>
        <router-link v-bind:to="{ name: 'home' }" v-bind:class="{ active: currentRoute === '/' }">Home</router-link>&nbsp;&nbsp;
        <li><router-link to="/about" v-bind:class="{ active: currentRoute === '/about' }">About</router-link></li>
        <li><router-link to="/faq" v-bind:class="{ active: currentRoute === '/faq' }">FAQ</router-link></li>
        <li><router-link to="/contact" v-bind:class="{ active: currentRoute === '/contact' }">Contact Us</router-link></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <li><router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''" v-bind:class="{ active: currentRoute === '/login' }">Login</router-link></li>
        <li><router-link v-bind:to="{ name: 'page'}" @click.native="handleClick($event, 'page')" v-if="$store.state.token != ''" v-bind:class="{ active: currentRoute === 'page' }">Dashboard</router-link></li>
        <li><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" v-bind:class="{ active: currentRoute === '/logout' }">Logout</router-link></li>
      </ul>
    </nav>
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentRoute: this.$route.path,
    };
  },
  watch: {
    $route() {
      this.currentRoute = this.$route.path;
    },
  },
  methods: {
    handleClick(event, name) {
      event.preventDefault() // prevent default navigation behavior
      console.log(name) // output the name of the link clicked
      const id = this.$store.state.user.id
       if (this.$store.state.user.authorities[0].name === "ROLE_TRAINER") {
              this.$router.replace({ name: "trainer" ,params:{id: id} });
              this.dashboardName = "trainer";
              this.dashboardParam = {id: id};
            } else if (this.$store.state.user.authorities[0].name === "ROLE_USER") {
              this.$router.replace({ name: "user", params:{id: id} });
              this.dashboardName = "user";
              this.dashboardParam = {id: id};
            } else {
              this.$router.push({ name: "login" });
            }
    },
    getUser(){
      const id = this.$store.state.user.id
       if (this.$store.state.user.authorities[0].name === "ROLE_TRAINER") {
              this.$router.replace({ name: "trainer" ,params:{id: id} });
              this.dashboardName = "trainer";
              this.dashboardParam = {id: id};
            } else if (this.$store.state.user.authorities[0].name === "ROLE_USER") {
              this.$router.replace({ name: "user", params:{id: id} });
              this.dashboardName = "user";
              this.dashboardParam = {id: id};
            } else {
              this.$router.push({ name: "login" });
            }
          }
      
    
  }
};
</script>

<style>
nav {
  display: flex;
  justify-content: center;
  background-color: #333;
  font-family: Arial, sans-serif;
}

nav ul {
  display: flex;
  margin: 0;
  padding: 0;
}

nav li {
  list-style-type: none;
}

nav a {
  display: block;
  padding: 10px;
  color: #fff;
  text-decoration: none;
  transition: background-color 0.5s ease;
}

nav a:hover {
  background-color: #555;
}

nav a.active {
  background-color: #4CAF50;
}

</style>
