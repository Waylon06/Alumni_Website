<template>
  <div>
    <router-link to="/" class="back"
      ><i class="el-icon-caret-left"></i> 回首页</router-link
    >
    <div class="user">
      <i class="icon-u"></i
      ><input type="text" id="user" placeholder="账号" v-model="username" />
    </div>
    <div class="password">
      <i class="icon-p"></i>
      <input
        type="password"
        placeholder="密码"
        id="password"
        v-model="password"
      />
    </div>
    <button class="login" @click="clickToLogin">登录</button>
    <div class="remember">
      <el-switch
        v-model="value"
        active-color="rgba(58, 98, 215, 1)"
        inactive-color="rgba(255, 255, 255, 0.25)"
      >
      </el-switch
      ><span class="active-text">记住密码</span>
    </div>
    <div class="more">
      <a
        class="register"
        @click="changePage(item)"
        v-for="item in menu"
        :key="item.name"
        >创建一个账号</a
      >
      <a class="forget">忘记密码?</a>
    </div>
    <Vcode :show="isShow" @success="onSuccess" @close="isShow = false" />
  </div>
</template>

<script>
import { clickToSignInAPI } from "@/api/data";
import { mapMutations } from "vuex";
import Vcode from "vue-puzzle-vcode";
export default {
  name: "LoginPage",
  components: {
    Vcode,
  },
  data() {
    return {
      username: "",
      password: "",
      value: false,
      isShow: false,
      menu: [
        {
          path: "/register",
          name: "register",
        },
      ],
    };
  },
  methods: {
    ...mapMutations(["changeUserData", "changeIsLogined"]),
    changePage(item) {
      this.$router.push({
        name: item.name,
      });
    },
    async clickToLogin() {
      if (this.username == "" || this.password == "") {
        this.$message({
          message: "账号或者密码不能为空！",
          type: "warning",
        });
      } else {
        // let userData = await clickToSignInAPI({
        //   username: this.username,
        //   password: this.password,
        // });
        // if (userData.data.statusCode === 200) {
        //   this.$notify({
        //     title: "成功",
        //     message: "登录成功",
        //     type: "success",
        //   });

        //   if (this.value) {
        //     let loginInfo = userData.data.data;
        //     loginInfo.password = this.password;
        //     localStorage.setItem("userData", JSON.stringify(loginInfo));
        //   } else {
        //     localStorage.removeItem("userData");
        //   }
        //   sessionStorage.setItem(
        //     "userData",
        //     JSON.stringify(userData.data.data)
        //   );
        //   this.changeUserData(userData.data.data);
        //   this.changeIsLogined(true);
        //   this.$router.push({ path: "/" });
        // } else {
        //   this.$message.error("账号或者密码错误！");
        // }
        this.isShow = true;
      }
    },
     async onSuccess(msg) {
        this.isShow = false;
        let userData = await clickToSignInAPI({
          username: this.username,
          password: this.password,
        });
        if (userData.data.statusCode === 200) {
          this.$notify({
            title: "成功",
            message: "登录成功",
            type: "success",
          });

          if (this.value) {
            let loginInfo = userData.data.data;
            loginInfo.password = this.password;
            localStorage.setItem("userData", JSON.stringify(loginInfo));
          } else {
            localStorage.removeItem("userData");
          }
          sessionStorage.setItem(
            "userData",
            JSON.stringify(userData.data.data)
          );
          this.changeUserData(userData.data.data);
          this.changeIsLogined(true);
          this.$router.push({ path: "/" });
        } else {
          this.$message.error("账号或者密码错误！");
        }
      }
  },
  watch: {
    // 方式一
    // username() {
    //   this.$store.state.username = this.username
    // }
    // 方式二
    // username() {
    //   this.$store.commit("changeUsername", this.username);
    // },
  },
  mounted() {
    if (localStorage.getItem("userData")) {
      let loginInfo = JSON.parse(localStorage.getItem("userData"));
      console.log(loginInfo)
      this.username = loginInfo.username;
      this.password = loginInfo.password;
      this.value = true;
    }
    if (this.$route.query.value == 'false') {
      sessionStorage.removeItem("userData");
      this.changeUserData({
        username: "",
        password: "",
        email: "",
        userId: "",
        avatar: "",
        admin: "",
      });
      this.changeIsLogined(false);
    }
  },
};
</script>

<style scoped>
.user,
.password {
  height: 60px;
  width: 440px;
  border-radius: 30px;
  opacity: 0.25;
  margin: 20px auto;
  background-color: rgba(255, 255, 255, 1);
  border: 1px solid rgba(255, 255, 255, 1);
}

#user,
#password {
  width: 370px;
  height: 60px;
  opacity: 0.25;
  margin-left: 70px;
  border-top-right-radius: 30px;
  border-bottom-right-radius: 30px;
  border: none;
  outline: none;
}

.user {
  margin-top: 80px;
  position: relative;
}

.icon-u::before {
  font-family: "icomoon";
  content: "\e971";
  position: absolute;
  top: 18px;
  left: 35px;
  font-size: 20px;
}

.password {
  margin-bottom: 40px;
  position: relative;
}

.icon-p::before {
  font-family: "icomoon";
  content: "\e98f";
  position: absolute;
  top: 18px;
  left: 35px;
  font-size: 20px;
}

input::-webkit-input-placeholder {
  /* color: rgba(211, 211, 211, 1); */
  font-size: 16px;
  font-family: SourceHanSansSC-regular;
  opacity: 1;
}

.remember {
  text-align: right;
}

.login {
  height: 60px;
  width: 440px;
  border-radius: 30px;
  margin-bottom: 30px;
  font-size: 16px;
  background-color: rgba(58, 98, 215, 1);
  color: rgba(255, 255, 255, 1);
  border: 1px solid rgba(58, 98, 215, 1);
}

.active-text {
  margin-left: 20px;
  color: rgba(211, 211, 211, 1);
  font-size: 16px;
}

.more {
  margin-top: 30px;
  display: flex;
  justify-content: space-between;
  font-size: 18px;
  color: rgba(211, 211, 211, 1);
}

.register,
.forget {
  cursor: pointer;
}
.back {
  position: absolute;
  top: 30px;
  left: 30px;
  font-size: 20px;
  color: rgba(255, 255, 255, 1);
}
.el-icon-caret-left {
  font-size: 20px;
}
</style>