<template>
  <el-container>
    <el-header>
      <nav-component />
    </el-header>
    <el-main>
        <router-view></router-view>
    </el-main>
    <el-footer>
      <footer-component/>
    </el-footer>
  </el-container>
</template>

<script>
import navComponent from "@/components/NavComponent.vue";
import footerComponent from "@/components/FooterComponent.vue";
export default {
  name: "Main",
  components: {
    navComponent,
    footerComponent
  },
};
</script>

<style scoped>
.el-header {
  padding: 0;
  height: 160px !important;
}
.el-main {
  padding: 0;

}
.el-footer {
  padding: 0;
}
</style>