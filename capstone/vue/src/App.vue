<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    
    </div>
    <router-view />
  </div>
</template>


<style scoped>
/*tablet
@media  screen and (max-width: 1024px) {
    #main-grid {
        grid-template-columns: 1fr 1fr;
        grid-template-areas:
        "headline headline"
        "student-quote student-picture"
        "student-quote student-contact"
        "student-profile student-profile"
        "experience-grid experience-grid";

        row-gap: 20px;
        column-gap: 35px;


    }

    #experience-grid {
        grid-template-columns: 1fr 1fr;
        grid-template-areas:
        "code web"
        "database design";

        row-gap: 20px;
        column-gap: 20px;
    }
    

phone
@media  screen and (max-width:450px) {
    #main-grid {
        grid-template-columns: 1fr;
        grid-template-areas:
        "headline"
        "student-picture"
        "student-contact"
        "student-quote"
        "student-profile"
        "experience-grid";
    

        row-gap: 20px;
        column-gap: 35px;
    } */
</style>
