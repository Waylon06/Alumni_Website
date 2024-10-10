<template>
  <div class="top">
    <div class="w">
      <div
        class="toServe"
        @click="clickToServer"
        v-show="this.userData.admin == 1 ? true : false"
      >
        管理端<i class="el-icon-caret-right"></i>
      </div>
      <div class="login">
        <router-link to="/login" v-show="!isLogined">登录</router-link>
        <div v-show="isLogined">
          {{ userData.username }}
          <el-button
            icon="el-icon-close"
            size="mini"
            type="danger"
            circle
            @click="logout"
          ></el-button>
        </div>
      </div>
      <div class="logo">
        <img src="../assets/images/logo.png" alt="" />
      </div>
      <nav>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          router
        >
          <el-menu-item index="/home">首页</el-menu-item>
          <el-menu-item index="/party">活动</el-menu-item>
          <el-menu-item index="/notice">学校通知</el-menu-item>
          <el-menu-item index="/user"
            @click="remind">个人中心</el-menu-item
          >
          <!-- <div class="search_btn">
            <el-button
              icon="el-icon-search"
              @click="searchStudent"
              class="search"
            ></el-button>
          </div> -->
          <el-menu-item index="/student"
            @click="remind">查找校友</el-menu-item
          >
        </el-menu>
      </nav>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
    };
  },
  mounted() {
    if (sessionStorage.getItem("userData")) {
      let res = sessionStorage.getItem("userData");
      this.changeUserData(JSON.parse(res));
    }
  },
  methods: {
    ...mapMutations(["changeUserData", "changeIsLogined"]),
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    logout() {
      sessionStorage.removeItem("userData");
      this.changeUserData({
        username: "",
        password: "",
        email: "",
        uid: "",
        avatar: "",
        admin: "",
      });
      this.changeIsLogined(false);
      this.$router.push({
        path: "/login",
      });
    },
    searchStudent() {
      this.$router.push({
        path: "/student",
      });
    },
    remind() {
      if(sessionStorage.getItem("userData") == null) {
        this.$message({
          message: "您还没有登录，请先登录",
          type: "warning",
        });
      }
    },
    clickToServer() {
      if (
        this.userData.admin == 1 &&
        JSON.parse(sessionStorage.getItem("userData")).admin == 1
      ) {
        window.location.href = "http://localhost:9528/#/dashboard" + "?token="+this.userData.avatar;
      } else {
        this.$message({
          message: "您不是管理员，无法进入管理端",
          type: "warning",
        });
      }
    },
  },
  computed: {
    ...mapState(["isLogined", "userData"]),
  },
};
</script>

<style lang="less" scoped>
.top {
  //   background-color: #b01f24;
  background: rgb(65, 82, 133);
  height: 160px;
  position: relative;
}
.logo {
  height: 100px;
  width: 200px;
  margin: 0 auto;
  img {
    width: 100%;
    height: 100%;
  }
}
nav {
  height: 60px;
  display: block;
}
.el-menu {
  //   background-color: #b01f24;
  background: rgb(65, 82, 133);
  border-bottom: none !important;
  display: flex;
  .el-menu-item {
    color: #fff;
    flex: 1;
  }
}
.login {
  color: #fff;
  line-height: 40px;
  font-size: 14px;
  text-align: right;
  padding: 0 20px;
  margin-right: 10px;
  position: absolute;
  top: 20px;
  right: 280px;
}
.search_box {
  padding-right: 0;
}
.search_btn {
  position: relative;
}
.search {
  position: absolute;
  height: 42px;
  top: 10px;
}
.toServe {
  position: absolute;
  top: 30px;
  right: 40px;
  font-size: 20px;
  color: #fff;
}
</style>