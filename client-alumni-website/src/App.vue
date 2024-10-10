<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
// import BackGround from './components/BackGround.vue'

export default {
  name: 'App',
  components: {
    // BackGround
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
}

* {
  padding: 0;
  margin: 0;
}

a {
  text-decoration: none;
}

li{
  list-style: none;
}

i {
  font-style: normal;
}

@font-face {
  font-family: 'icomoon';
  src:  url('./assets/fonts/icomoon.eot?2adm3n');
  src:  url('./assets/fonts/icomoon.eot?2adm3n#iefix') format('embedded-opentype'),
    url('./assets/fonts/icomoon.ttf?2adm3n') format('truetype'),
    url('./assets/fonts/icomoon.woff?2adm3n') format('woff'),
    url('./assets/fonts/icomoon.svg?2adm3n#icomoon') format('svg');
  font-weight: normal;
  font-style: normal;
  font-display: block;
}
</style>
