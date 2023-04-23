<template>
  <!-- Background image -->
  <div class="bg-image">
    <div class="mask" style="background-color: rgba(0, 0, 0, 0.6)">
      <div class="d-flex justify-content-center align-items-center h-100"></div>
    </div>
    <user-dashboard />
  </div>
  <!-- Background image -->
</template>

<script>
</script>

<style scoped>
.bg-image {
  background-image: url("https://i.imgur.com/jfdvHma.png");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
  height: 100vh;
  width: 100vw;
  overflow: auto;
}
</style>